package uta.cse3310;

    import java.net.InetSocketAddress;
    import java.util.Collections;
    import java.util.Vector;
    
    import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


public class App extends WebSocketServer {
    private int gameId;
    private Game game; // Instance of the Game class
    private Lobby lobby;

    public App(int port) {
        super(new InetSocketAddress(port));
        this.lobby = new Lobby();
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
        System.out.println("Message received from " + conn.getRemoteSocketAddress() + ": " + message);
    try {
        JSONObject msg = new JSONObject(message);
        String action = msg.getString("action");
        String username = msg.getString("username");

        // Check if the color key exists
        if (msg.has("color")) {
            String color = msg.getString("color");
            // Process color if needed
        }

        switch (action) {
            case "join":
                // Assume default color if none provided
                String userColor = msg.has("color") ? msg.getString("color") : "defaultColor";
                PlayerType player = new PlayerType(username, userColor, null); // Assuming PlayerType constructor takes username and color
                lobby.addPlayer(player);
                System.out.println(username + " added to the lobby.");
                broadcastLobbyUpdate();  // Broadcast the updated lobby state
                break;
            default:
                System.out.println("Unknown action: " + action);
        }
    } catch (JSONException e) {
        System.err.println("Error parsing message: " + message);
        e.printStackTrace();
    }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        if (conn != null) {
            System.err.println("An error occurred on WebSocket connection: " + conn.getRemoteSocketAddress());
        } else {
            System.err.println("An error occurred on a WebSocket connection, but no specific connection is associated.");
        }
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

    private void broadcastLobbyUpdate() {
        JSONObject response = new JSONObject();
        try {
            response.put("action", "updateLobby");
            JSONArray playersArray = new JSONArray();
            for (PlayerType player : lobby.getPlayers()) {
                playersArray.put(player.getNickname()); // Replace with correct method to get username
            }
            response.put("players", playersArray);
            broadcast(response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    public void broadcast(String message) {
        for (WebSocket conn : getConnections()) {
            if (conn != null && conn.isOpen()) {
                conn.send(message);
            }
        }
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
