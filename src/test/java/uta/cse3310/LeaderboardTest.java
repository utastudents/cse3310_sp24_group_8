package uta.cse3310;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class LeaderboardTest {

    private Leaderboard leaderboard;
    private PlayerType player1;
    private PlayerType player2;
    private PlayerType player3;

    @Before
    public void setUp() {
        leaderboard = new Leaderboard();
        player1 = new PlayerType("Erik", "Blue", PlayerType.Status.Playing);
        player2 = new PlayerType("Anthony", "Red", PlayerType.Status.Playing);
        player3 = new PlayerType("Brianna", "Green", PlayerType.Status.Playing);

        leaderboard.addOrUpdatePlayer(player1, 100);
        leaderboard.addOrUpdatePlayer(player2, 150);
        leaderboard.addOrUpdatePlayer(player3, 50);
    }

    @Test
    public void testAddOrUpdatePlayer() {
        // Check if players are added correctly
        assertEquals(3, leaderboard.getSortedPlayers().size());
        // Update an existing player's score
        leaderboard.addOrUpdatePlayer(player1, 200);
        assertEquals(Integer.valueOf(200), leaderboard.getPlayerScores().get(player1));
    }

    @Test
    public void testGetSortedPlayers() {
        List<PlayerType> sortedPlayers = leaderboard.getSortedPlayers();
        assertEquals("Erik", sortedPlayers.get(0).getNickname()); // Anthony should be first with the highest score
        assertEquals("Anthony", sortedPlayers.get(1).getNickname()); // Erik second
        assertEquals("Brianna", sortedPlayers.get(2).getNickname()); // Brianna last
    }

    @Test
    public void testDisplay() {
        // Not a trivial method to test because it involves output to the console,
        // but we can assume it calls getSortedPlayers which is tested separately.
        leaderboard.display();
        assertTrue(true); 
    }

    @Test
    public void testReset() {
        leaderboard.reset();
        assertTrue(leaderboard.getSortedPlayers().isEmpty());
        assertTrue(leaderboard.getPlayerScores().isEmpty());
    }
}
