package uta.cse3310;

import java.util.Arrays;

public class Game {
    private PlayerType[][] buttons; 
    private GameStats gameStats; // Represents game statistics
    private int ingameStats; // Represents in-game stats
    private int finalGameStats; // Represents final game stats
    private String[] msg; 

    public Game() {
        this.buttons = new PlayerType[50][50]; 
        this.gameStats = new GameStats(); // Initialize game statistics
        this.ingameStats = 0; // Initialize in-game stats
        this.finalGameStats = 0; // Initialize final game stats
        this.msg = new String[1]; 
        this.msg[0] = "Welcome to the Game!"; // Set default welcome message
    }

    // Method to update the game state based on user input
    public void update(UserEvent userEvent) {
        int gameId = userEvent.getGameId();
        PlayerType player = userEvent.getPlayers();
        int buttonIndex = userEvent.getButton();

        
        buttons[gameId][buttonIndex] = player;

        // Update game statistics
        gameStats.incrementGamesPlayed();
    }

    
    public PlayerType[][] getButtons() {
        return buttons;
    }

    
    public GameStats getGameStats() {
        return gameStats;
    }

    
    public int getIngameStats() {
        return ingameStats;
    }

    public void setIngameStats(int ingameStats) {
        this.ingameStats = ingameStats;
    }

    
    public int getFinalGameStats() {
        return finalGameStats;
    }

    public void setFinalGameStats(int finalGameStats) {
        this.finalGameStats = finalGameStats;
    }

    
    public String[] getMsg() {
        return msg;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }

    // Method to reset the game state (if needed)
    public void reset() {
        buttons = new PlayerType[50][50]; 
        gameStats.reset(); // Reset game statistics
        ingameStats = 0; // Reset in-game stats
        finalGameStats = 0; // Reset final game stats
    }

    
    public static class GameStats {
        private int gamesPlayed;
        private int gamesWon;
        private int gamesLost;

        public GameStats() {
            this.gamesPlayed = 0;
            this.gamesWon = 0;
            this.gamesLost = 0;
        }

        
        public void incrementGamesPlayed() {
            gamesPlayed++;
        }

        public void incrementGamesWon() {
            gamesWon++;
        }

        public void incrementGamesLost() {
            gamesLost++;
        }

        // Method to reset game statistics
        public void reset() {
            gamesPlayed = 0;
            gamesWon = 0;
            gamesLost = 0;
        }

        
        public int getGamesPlayed() {
            return gamesPlayed;
        }

        public int getGamesWon() {
            return gamesWon;
        }

        public int getGamesLost() {
            return gamesLost;
        }
    }

    // Override toString method to represent the game state as a string
    @Override
    public String toString() {
        return "Game{" +
                "buttons=" + Arrays.deepToString(buttons) +
                ", gameStats=" + gameStats +
                ", ingameStats=" + ingameStats +
                ", finalGameStats=" + finalGameStats +
                ", msg=" + Arrays.toString(msg) +
                '}';
    }
}
