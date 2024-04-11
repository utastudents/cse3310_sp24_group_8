package uta.cse3310;
import java.io.File;
import java.util.Random;

public class GameLogic {
    private PlayerType players;
    private PlayerType button;

    private int gameId;
    private char[][] wordGrid;
    private File words;
    private double wordDensity;
    private String[] randomWords;
    private double fillerDensity;

    public GameLogic(PlayerType players, PlayerType button) {
        this.players = players;
        this.button = button;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setWordGrid(char[][] wordGrid) {
        this.wordGrid = wordGrid;
    }

    public void setWords(File words) {
        this.words = words;
    }

    public void setWordDensity(double wordDensity) {
        this.wordDensity = wordDensity;
    }

    public void setRandomWords(String[] randomWords) {
        this.randomWords = randomWords;
    }

    public void setFillerDensity(double fillerDensity) {
        this.fillerDensity = fillerDensity;
    }

    // Logic to generate the game grid based on parameters
    public void gridGenerator() {
        wordGrid = new char[50][50]; // Assuming fixed grid size
        Random random = new Random();
        for (int i = 0; i < wordGrid.length; i++) {
            for (int j = 0; j < wordGrid[i].length; j++) {
                // Generate random characters (for simplicity, using ASCII values)
                wordGrid[i][j] = (char) (random.nextInt(26) + 'A');
            }
        }
    }

    // Logic to calculate filler percentage based on word density and random words
    public double calculateFillerPercentage() {
        int totalCells = wordGrid.length * wordGrid[0].length;
        int wordCells = (int) (totalCells * wordDensity);
        int fillerCells = totalCells - wordCells;
        return (double) fillerCells / totalCells * 100.0;
    }

    // Logic to check if the selected word is valid for the player
    public boolean isValidWord(String word) {
        // Example: Check if the word starts with the player's color
        return word.startsWith(players.getColor());
    }
}
