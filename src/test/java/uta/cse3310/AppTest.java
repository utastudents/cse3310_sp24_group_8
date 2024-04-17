package uta.cse3310;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
    private App app;

    @Before
    public void setUp() {
        app = new App(8080); // Assuming 8080 is the port for WebSocket server
    }

    @Test
    public void testConstructor() {
        assertNotNull(app);
    }

    @Test
    public void testGetGameId() {
        assertEquals(1, app.getGameId());
    }

    @Test
    public void testSetGameId() {
        app.setGameId(2);
        assertEquals(2, app.getGameId());
    }

    @Test
    public void testGetGame() {
        Assert.assertNotNull(null, app.resetGame());
    }

    private void assertNotNull(Object resetGame) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertNotNull'");
    }

    @Test
    public void testSetGame() {
        Game game = new Game();
        app.setGameId(game);
        assertEquals(game, app.resetGame());
    }

    @Test
    public void testGetLobby() {
        assertNotNull(app.getLobby());
    }

    @Test
    public void testSetLobby() {
        Lobby lobby = new Lobby();
        app.setLobby(lobby);
        assertEquals(lobby, app.getLobby());
    }

    @Test
    public void testGetConnections() {
        assertNotNull(app.getConnections());
    }
}
