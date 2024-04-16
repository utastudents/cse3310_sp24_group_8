package uta.cse3310;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    private PlayerType players;

    private int gameId;
    private char[][] wordGrid;
    private String[] randomWords;
    private double fillerDensity;

    public GameLogic(PlayerType players) {
        this.players = players;
        this.wordGrid = new char[50][50]; 
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

    public char[][] getWordGrid() {
        return wordGrid;
    }

    // Method to read words from a file
    public void setWordsFromFile(String filePath) {
        List<String> wordsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordsList.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        randomWords = wordsList.toArray(new String[0]);
    }

    // Method to generate random words
    public void generateRandomWords(int wordCount) {
        randomWords = new String[wordCount];
        Random random = new Random();
        for (int i = 0; i < wordCount; i++) {
            int index = random.nextInt(randomWords.length);
            randomWords[i] = randomWords[index];
        }
    }

    public String[] getRandomWords() {
        return randomWords;
    }

    // Method to generate random filler density
    public void generateFillerDensity() {
        Random random = new Random();
        fillerDensity = random.nextDouble();
    }

    public double getFillerDensity() {
        return fillerDensity;
    }

    // Logic to generate the game grid based on parameters
    public void gridGenerator() {
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
        int wordCells = (int) (totalCells * fillerDensity);
        int fillerCells = totalCells - wordCells;
        return (double) fillerCells / totalCells * 100.0;
    }

    // Logic to check if the selected word is valid for the player
    public boolean isValidWord(String word) {
        return word.startsWith(players.getColor());
    }
}
