package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.*;
//import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PlayerTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkNullNickname() {
        new PlayerType(null, "Green", PlayerType.Status.Waiting);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNullColor() {
        new PlayerType("Anthony", null, PlayerType.Status.Waiting);
    }

    @Test
    public void checkValidNicknameAndColor() {
        PlayerType player = new PlayerType("Anthony", "Green", PlayerType.Status.Waiting);
        assertEquals("Anthony", player.getNickname());
        assertEquals("Green", player.getColor());
        assertEquals(PlayerType.Status.Waiting, player.getStatus());
    }
    
}
