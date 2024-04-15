package uta.cse3310;

    import java.net.InetSocketAddress;
    import java.util.Collections;
    import java.util.Vector;
    
    import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class App extends WebSocketServer {
    private int gameId;
    private Game game; // Instance of the Game class

    public App(int port) {
        super(new InetSocketAddress(port));
        this.gameId = 1; // Initialize gameId
        this.game = new Game(); // Initialize the game instance
    }

    // WebSocketServer methods
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // Your implementation for handling WebSocket connections
        System.out.println("WebSocket connection opened from: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // Your implementation for handling WebSocket connection closure
        System.out.println("WebSocket connection closed: " + conn.getRemoteSocketAddress() + " (Reason: " + reason + ")");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // Your implementation for handling WebSocket messages
        System.out.println("Message received from " + conn.getRemoteSocketAddress() + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        // Your implementation for handling WebSocket errors
        System.err.println("An error occurred on WebSocket connection: " + conn.getRemoteSocketAddress());
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        // Your implementation for starting the WebSocket server
        System.out.println("WebSocket Server started successfully!");
    }

    // Additional methods for handling game logic and updates
    public void handleUserEvent(UserEvent userEvent) {
        game.update(userEvent); // Update game state based on user event
    }

    public void resetGame() {
        game.reset(); // Reset the game state
    }

    public static void main(String[] args) {
        int httpPort = 9008; // HTTP server port
        int webSocketPort = 9108; // WebSocket server port

        // Start the HTTP server
        HttpServer httpServer = new HttpServer(httpPort, "./html");
        httpServer.start();
        System.out.println("HTTP Server started on port: " + httpPort);

        // Start the WebSocket server
        App app = new App(webSocketPort);
        app.start();
        System.out.println("WebSocket Server started on port: " + webSocketPort);
    }
}
