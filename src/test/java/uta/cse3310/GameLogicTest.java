package uta.cse3310;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
    }

    @Test
    public void testIsValidWord() {
        // Assuming isValidWord checks something about the first player
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
        gameLogic.setWordsFromFile("https://raw.githubusercontent.com/utastudents/cse3310_sp24_group_8/main/src/main/java/uta/cse3310/words.txt");
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
        char[][] wordGrid = gameLogic.getWordGrid();
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
        gameLogic.gridGenerator(); // Generate a random word grid
        assertFalse(gameLogic.checkColumns("XYZ")); // Assuming "XYZ" does not exist in any column
    }

    @Test
    public void testCheckDiagonals() {
        gameLogic.gridGenerator(); // Generate a random word grid
        assertFalse(gameLogic.checkDiagonals("XYZ")); // Assuming "XYZ" does not exist in any diagonal
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
        gameLogic.setWordsFromFile("https://raw.githubusercontent.com/utastudents/cse3310_sp24_group_8/main/src/main/java/uta/cse3310/words.txt");
        assertEquals(70, gameLogic.checkWord("APPLE")); // Assuming "APPLE" is in the word list
        assertEquals(0, gameLogic.checkWord("XYZ")); // Assuming "XYZ" is not in the word list
    }
}
