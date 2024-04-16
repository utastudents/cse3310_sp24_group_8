package uta.cse3310;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public void startGame() {
        initializeGame();
        System.out.println("Game started.");
    }

    private void initializeGame() {
        gridGenerator();
        setWordsFromFile("https://raw.githubusercontent.com/utastudents/cse3310_sp24_group_8/main/src/main/java/uta/cse3310/words.txt");
        generateFillerDensity();
        System.out.println("Game initialized."); 
    }

    // Method to set words from a file hosted on a URL
    public void setWordsFromFile(String url) {
        List<String> wordsList = new ArrayList<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    wordsList.add(line.trim());
                }
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

    // Method to check if the specified word exists in the rows
    public boolean checkRows(String word) {
        for (int i = 0; i < wordGrid.length; i++) {
            String row = new String(wordGrid[i]);
            if (row.contains(word)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the specified word exists in the columns
    public boolean checkColumns(String word) {
        for (int i = 0; i < wordGrid[0].length; i++) {
            StringBuilder column = new StringBuilder();
            for (int j = 0; j < wordGrid.length; j++) {
                column.append(wordGrid[j][i]);
            }
            if (column.toString().contains(word)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the specified word exists in the diagonals
public boolean checkDiagonals(String word) {
    // Check main diagonal (top-left to bottom-right)
    StringBuilder mainDiagonal = new StringBuilder();
    for (int i = 0; i < wordGrid.length; i++) {
        mainDiagonal.append(wordGrid[i][i]);
    }
    if (mainDiagonal.toString().contains(word)) {
        return true;
    }

    // Check secondary diagonal (top-right to bottom-left)
    StringBuilder secondaryDiagonal = new StringBuilder();
    for (int i = 0; i < wordGrid.length; i++) {
        secondaryDiagonal.append(wordGrid[i][wordGrid.length - 1 - i]);
    }
    if (secondaryDiagonal.toString().contains(word)) {
        return true;
    }

    return false;
}


    // Calculate points based on word length
    public int calculatePoints(String word) {
        int length = word.length();
        switch (length) {
            case 3:
                return 50;
            case 4:
                return 60;
            case 5:
                return 70;
            case 6:
                return 80;
            case 7:
                return 90;
            case 8:
                return 100;
            case 9:
                return 110;
            case 10:
                return 120;
            case 11:
                return 130;
            case 12:
                return 140;
            case 13:
                return 150;
            case 14:
                return 160;
            case 15:
                return 170;
            default:
                return 0; // Invalid word length
        }
    }
    

    // Method to check if the highlighted word matches any word in the word list
    public int checkWord(String word) {
        for (String randomWord : randomWords) {
            if (word.equalsIgnoreCase(randomWord)) {
                // Calculate points based on word length
                int points = calculatePoints(word);
                return points;
            }
        }
        return 0;
    }
}
