package uta.cse3310;

    import java.net.InetSocketAddress;
    import java.util.Collections;
    import java.util.Vector;
    
    import org.java_websocket.WebSocket;
    import org.java_websocket.drafts.Draft_6455;
    import org.java_websocket.handshake.ClientHandshake;
    import org.java_websocket.server.WebSocketServer;

public class App extends WebSocketServer{
        private int gameId;
        private int inGameStats;
        private int finalGameStats;
        
    
        public App(int port) {
            super(new InetSocketAddress(port));
            this.gameId = 1; // Initialize gameId
            this.inGameStats = 0; // Initialize inGameStats
            this.finalGameStats = 0; // Initialize finalGameStats
        }
    
        // Other constructors and overridden methods remain unchanged...
    
        @Override
        public void onOpen(WebSocket conn, ClientHandshake handshake) {
            // Your existing implementation for handling WebSocket connections
        }
    
        @Override
        public void onClose(WebSocket conn, int code, String reason, boolean remote) {
            // Your existing implementation for handling WebSocket connection closure
        }
    
        @Override
        public void onMessage(WebSocket conn, String message) {
            // Your existing implementation for handling WebSocket messages
        }
    
        @Override
        public void onError(WebSocket conn, Exception ex) {
            // Your existing implementation for handling WebSocket errors
        }
    
        @Override
        public void onStart() {
            // Your existing implementation for starting the WebSocket server
        }
    
        // Additional methods for handling game logic, such as updating stats, etc.
    
        public static void main(String[] args) {
            // Set up the http server
        int port = 9080;
        HttpServer H = new HttpServer(port, "./html");
        H.start();
        System.out.println("http Server started on port:" + port);

        // create and start the websocket server

        port = 9880;
        App A = new App(port);
        A.start();
        System.out.println("websocket Server started on port: " + port);
        }
    }


