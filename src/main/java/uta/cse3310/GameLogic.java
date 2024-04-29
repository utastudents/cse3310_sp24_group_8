package uta.cse3310;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameLogic {
    private List<PlayerType> players;
    private Broadcast broadcaster;
    List<String> validWords;
    private List<String> allWords;
    

    private int gameId;
    private char[][] wordGrid;
    private String[] randomWords;
    private double fillerDensity;

    public GameLogic(List<PlayerType> players, Broadcast broadcaster) {
        this.players = players;
        this.wordGrid = new char[35][35];
        this.broadcaster = broadcaster;
        this.validWords = new ArrayList<>();
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setRandomWords(String[] words) {
        this.randomWords = words;
    }

    public char[][] getWordGrid() {
        return wordGrid;
    }

    public String[] getRandomWords() {
        return randomWords;
    }

    public JSONArray serializePlayers() {
        JSONArray playersArray = new JSONArray();
        for (PlayerType player : players) {
            JSONObject playerObject = new JSONObject();
            playerObject.put("nickname", player.getNickname());
            playerObject.put("isReady", player.getStatus() == PlayerType.Status.Playing);
            playerObject.put("score", player.getScore());
            playersArray.put(playerObject);
        }
        System.out.println("Serializing players: " + playersArray.toString()); // Log the serialized output
        return playersArray;
    }


    public void startGame() {
        initializeGame();
        selectRandomWords(20); // Ensure this actually selects words
        System.out.println("Random words selected: " + Arrays.toString(randomWords)); // Log selected words
        broadcaster.broadcast("Game starts now!");
        JSONObject startGameMessage = new JSONObject();
        try {
            JSONArray gridArray = new JSONArray();
            for (char[] row : wordGrid) {
                gridArray.put(new String(row));
            }
            JSONArray playersJson = serializePlayers(); // Check player serialization
            System.out.println("Serialized players: " + playersJson.toString()); // Log player data
    
            startGameMessage.put("action", "startGame");
            startGameMessage.put("grid", gridArray);
            startGameMessage.put("players", playersJson);
            broadcaster.broadcast(startGameMessage.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initializeGame() {
        gridGenerator();
        setWordsFromFile("https://raw.githubusercontent.com/utastudents/cse3310_sp24_group_8/main/src/main/java/uta/cse3310/words.txt");
        generateFillerDensity();
        System.out.println("Game initialized."); 
    }

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

        this.allWords = wordsList;
        System.out.println("Fetched " + wordsList.size() + " words from file.");
    }


    public void setWordGrid(List<String> list) {
        int rows = list.size();
        if (rows == 0) {
            this.wordGrid = new char[0][0]; // Handle the case where the list is empty
            return;
        }

        int cols = list.get(0).length(); // Assumes all strings are the same length

        this.wordGrid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String rowString = list.get(i);
            for (int j = 0; j < cols; j++) {
                if (j < rowString.length()) { // Ensure there's a character at position j
                    this.wordGrid[i][j] = rowString.charAt(j);
                } else {
                    this.wordGrid[i][j] = ' '; // Optionally fill shorter strings with spaces
                }
            }
        }
    }


    public void selectRandomWords(int numberOfWords) {
        Random random = new Random();
        Set<String> selectedWords = new HashSet<>();
        while (selectedWords.size() < numberOfWords && selectedWords.size() < allWords.size()) {
            selectedWords.add(allWords.get(random.nextInt(allWords.size())));
        }
        randomWords = selectedWords.toArray(new String[0]);
        System.out.println("Selected " + randomWords.length + " random words for the game.");
    }

    public List<String> getCurrentWordList() {
        return Arrays.asList(randomWords);
    }

    public void updatePlayerScore(String nickname, int scoreToAdd) {
        for (PlayerType player : players) {
            if (player.getNickname().equals(nickname)) {
                player.setScore(player.getScore() + scoreToAdd);
                break;
            }
        }
    }

    public JSONObject getCurrentScores() throws JSONException {
        JSONObject scores = new JSONObject();
        JSONArray scoresArray = new JSONArray();
        for (PlayerType player : players) {
            JSONObject scoreDetail = new JSONObject();
            scoreDetail.put("nickname", player.getNickname());
            scoreDetail.put("score", player.getScore());
            scoresArray.put(scoreDetail);
        }
        scores.put("scores", scoresArray);
        return scores;
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

    public boolean isValidWord(String word) {
        return validWords.contains(word);
    }
}
