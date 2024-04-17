package uta.cse3310;

    import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
    
    import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


public class App extends WebSocketServer implements Broadcast {
    private List<PlayerType> players = new ArrayList<>();
    private int gameId;
    private GameLogic gameLogic;
    private Game game; // Instance of the Game class
    private Lobby lobby;
    private Map<WebSocket, PlayerType> connectionPlayerMap = new HashMap<>();
    

    public App(int port) {
        super(new InetSocketAddress(port));
        this.lobby = new Lobby();
        this.gameId = 1; // Initialize gameId
        this.game = new Game(); // Initialize the game instance
        this.gameLogic = new GameLogic(players, this);
    }

    // WebSocketServer methods
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // Your implementation for handling WebSocket connections
        System.out.println("WebSocket connection opened from: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // Remove the player when their connection closes
        PlayerType player = connectionPlayerMap.remove(conn);
        if (player != null) {
            lobby.removePlayer(player);
            broadcastLobbyUpdate();
        }
        System.out.println("WebSocket connection closed: " + conn.getRemoteSocketAddress() + " (Reason: " + reason + ")");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message received from " + conn.getRemoteSocketAddress() + ": " + message);
        try {
            JSONObject msg = new JSONObject(message);
            String action = msg.getString("action");
            
            switch (action) {
                case "join":
                    handleJoin(conn, msg);
                    break;
                case "ready":
                    handleReady(conn, msg);
                    break;
                default:
                    System.out.println("Unknown action: " + action);
                    break;
            }
        } catch (JSONException e) {
            System.err.println("Error parsing message: " + message);
            e.printStackTrace();
        }
    }

    private void handleJoin(WebSocket conn, JSONObject msg) throws JSONException {
        String username = msg.getString("username");
        String color = msg.has("color") ? msg.getString("color") : "defaultColor";
        PlayerType player = new PlayerType(username, color, PlayerType.Status.Waiting);
        if (lobby.addPlayer(player)) {
            connectionPlayerMap.put(conn, player); // Link the connection to the player
            System.out.println(username + " added to the lobby.");
            broadcastLobbyUpdate();
        } else {
            conn.send(new JSONObject().put("action", "lobbyFull").toString());
        }
    }

    private void handleReady(WebSocket conn, JSONObject msg) throws JSONException {
        PlayerType player = connectionPlayerMap.get(conn);
        if (player != null) {
            player.setStatus(PlayerType.Status.Playing);
            broadcastLobbyUpdate();
            checkAllPlayersReady();
        }
    }



    private void checkAllPlayersReady() {
        System.out.println("Checking if gameLogic is null: " + (gameLogic == null));
        if (gameLogic != null && lobby.areAllPlayersReady()) {
            gameLogic.startGame();
        } else {
            System.out.println("GameLogic not initialized or not all players are ready.");
        }
    }


    @Override
    public void broadcast(String message) {
        for (WebSocket conn : getConnections()) {
            if (conn != null && conn.isOpen()) {
                conn.send(message);
            }
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
                JSONObject playerInfo = new JSONObject();
                playerInfo.put("nickname", player.getNickname());
                playerInfo.put("isReady", player.getStatus() == PlayerType.Status.Playing); // Assume Status.Playing means the player is ready
                playersArray.put(playerInfo);
            }
            response.put("players", playersArray);
            broadcast(response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
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

    public Object getLobby() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLobby'");
    }

    public Object getGameId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameId'");
    }

    public void setGameId(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGameId'");
    }
}
