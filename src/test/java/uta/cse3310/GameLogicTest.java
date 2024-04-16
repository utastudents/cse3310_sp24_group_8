package uta.cse3310;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameLogicTest {

    private GameLogic gameLogic;

    @Before
    public void setUp() {
        PlayerType player = new PlayerType("Player1", "Red", PlayerType.Status.Waiting);
        gameLogic = new GameLogic(player);
    }

    @Test
    public void testSetWordsFromFile() {
        gameLogic.setWordsFromFile("words.txt");
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
        assertEquals(50, wordGrid.length);
        assertEquals(50, wordGrid[0].length);
    }

    @Test
    public void testCalculateFillerPercentage() {
        gameLogic.generateFillerDensity();
        double fillerPercentage = gameLogic.calculateFillerPercentage();
        assertTrue(fillerPercentage >= 0 && fillerPercentage <= 100);
    }

    @Test
    public void testIsValidWord() {
        PlayerType player = new PlayerType("Player1", "Red", PlayerType.Status.Waiting);
        gameLogic = new GameLogic(player);
        assertTrue(gameLogic.isValidWord("RedWord"));
        assertFalse(gameLogic.isValidWord("BlueWord"));
    }
}
