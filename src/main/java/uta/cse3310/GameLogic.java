package uta.cse3310;
import java.io.File;

public class GameLogic {
    private PlayerType player1;
    private PlayerType player2;
    private PlayerType player3;
    private PlayerType player4;
    private PlayerType button;

    private int gameId;
    private char[][] wordGrid;
    private File words;
    private double wordDensity;
    private String[] randomWords;
    private double fillerDensity;

    public GameLogic(PlayerType player1, PlayerType player2, PlayerType player3, PlayerType player4, PlayerType button) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.button = button;
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

    public void gridGenerator(char[][] wordGrid, String[] randomWords, double wordDensity, double fillerDensity) {
        // Logic to generate the word grid based on parameters
    }

    public double fillerPercentage(int wordDensity, String[] randomWords) {
        // Logic to calculate filler percentage based on word density and random words
        return 0.0; // Placeholder return value
    }

    public boolean isValidWord(PlayerType player, String[] randomWords) {
        // Logic to check if the selected word is valid for the player
        return false; // Placeholder return value
    }
}
