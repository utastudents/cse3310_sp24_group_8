package uta.cse3310;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameLogicTest {

    private GameLogic gameLogic;
    private List<PlayerType> players;
    private Broadcast mockBroadcast;


    class MockBroadcast implements Broadcast {
        List<String> messages = new ArrayList<>();

        @Override
        public void broadcast(String message) {
            messages.add(message);
        }
    }

    @Before
    public void setUp() {
        players = new ArrayList<>();
        players.add(new PlayerType("Player1", "Blue", PlayerType.Status.Waiting));  // Assuming PlayerType constructor requires a status
        mockBroadcast = new MockBroadcast();
        gameLogic = new GameLogic(players, mockBroadcast);
        gameLogic.validWords.add("word");
        gameLogic.setValidWords(Arrays.asList("word", "test", "example"));
    }

    @Test
    public void testIsValidWord() {
        assertTrue(gameLogic.isValidWord("word"));
        assertFalse(gameLogic.isValidWord("nonexistent"));
    }

    @Test
    public void testGameStart() {
        gameLogic.startGame();
        assertTrue(((MockBroadcast) mockBroadcast).messages.contains("Game starts now!"));
    }

    @Test
    public void testSetWordsFromURL() {
        gameLogic.setRandomWords(new String[]{"word1", "word2", "word3"});
        String[] randomWords = gameLogic.getRandomWords();
        assertNotNull(randomWords);
        assertTrue(randomWords.length > 0);
    }
    
    @Test
    public void testGenerateRandomWords() {
        gameLogic.generateRandomWords(10);
        String[] randomWords = gameLogic.getRandomWords();
        assertNotNull(randomWords);
        assertEquals(10, randomWords.length);
    }

    @Test
    public void testGenerateFillerDensity() {
        gameLogic.generateFillerDensity();
        double fillerDensity = gameLogic.getFillerDensity();
        assertTrue(fillerDensity >= 0 && fillerDensity <= 1);
    }

    @Test
    public void testGridGenerator() {
        gameLogic.gridGenerator();
        char[][] wordGrid = gameLogic.getWordGrid();  // Use the getter method here
        assertNotNull(wordGrid);
        assertEquals(35, wordGrid.length);
        assertEquals(35, wordGrid[0].length);
    }

    @Test
    public void testCalculateFillerPercentage() {
        gameLogic.generateFillerDensity();
        double fillerPercentage = gameLogic.calculateFillerPercentage();
        assertTrue(fillerPercentage >= 0 && fillerPercentage <= 100);
    }


    @Test
    public void testCheckColumns() {
        gameLogic.gridGenerator();
        String word = "XYZ";
        boolean found = false;

        for (int col = 0; col < gameLogic.getWordGrid()[0].length; col++) {
            StringBuilder columnString = new StringBuilder();
            for (int row = 0; row < gameLogic.getWordGrid().length; row++) {
                columnString.append(gameLogic.getWordGrid()[row][col]);
            }
            if (columnString.toString().contains(word)) {
                found = true;
                break;
            }
        }

        assertFalse(found);
    }


    @Test
    public void testCheckDiagonals() {
        gameLogic.gridGenerator();
        String word = "XYZ";
        StringBuilder diag1 = new StringBuilder();
        StringBuilder diag2 = new StringBuilder();

        for (int i = 0; i < gameLogic.getWordGrid().length; i++) {
            diag1.append(gameLogic.getWordGrid()[i][i]);
            diag2.append(gameLogic.getWordGrid()[i][gameLogic.getWordGrid().length - 1 - i]);
        }

        assertFalse(diag1.toString().contains(word) || diag2.toString().contains(word));
    }


    @Test
    public void testCalculatePoints() {
        assertEquals(50, gameLogic.calculatePoints("THE")); // 3-letter word
        assertEquals(60, gameLogic.calculatePoints("Four")); // 4-letter word
        assertEquals(170, gameLogic.calculatePoints("Acclimatization")); // 15-letter word
        assertEquals(0, gameLogic.calculatePoints("A")); // Invalid word length
    }

    @Test
    public void testCheckWord() {
        gameLogic.setRandomWords(new String[]{"apple", "banana", "cherry"});
        assertEquals(70, gameLogic.checkWord("apple")); 
        assertEquals(0, gameLogic.checkWord("xyz")); 
    }
}
