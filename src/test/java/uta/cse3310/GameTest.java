package uta.cse3310;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
public void testUpdate() {
    UserEvent userEvent = new UserEvent();
    userEvent.setGameId(0);
    
    // Create a PlayerType instance with a nickname and color
    PlayerType player = new PlayerType("Player1", "Red", PlayerType.Status.Waiting);
    userEvent.setPlayers(player);
    
    userEvent.setButton(0);

    game.update(userEvent);

    PlayerType[][] buttons = game.getButtons();
    assertEquals(player, buttons[0][0]); // Check if the button is correctly updated
}


@Test
public void testReset() {
    // Update game state
    UserEvent userEvent = new UserEvent();
    userEvent.setGameId(0);
    
    // Create a PlayerType instance with a nickname and color
    PlayerType player = new PlayerType("Player1", "Red", PlayerType.Status.Waiting);
    userEvent.setPlayers(player);
    
    userEvent.setButton(1); // Assuming button index 1

    game.update(userEvent);

    // Reset game state
    game.reset();

    // Ensure game state is reset
    PlayerType[][] buttons = game.getButtons();
    assertNull(buttons[0][1]); // Check if the button is reset to null
    assertEquals(0, game.getGameStats().getGamesPlayed()); // Check if game stats are reset
    assertEquals(0, game.getIngameStats()); // Check if in-game stats are reset
    assertEquals(0, game.getFinalGameStats()); // Check if final game stats are reset
    assertEquals("Welcome to the Game!", game.getMsg()[0]); // Check if the message is reset
}


    // Add more test methods as needed to cover other functionalities of the Game class
}
