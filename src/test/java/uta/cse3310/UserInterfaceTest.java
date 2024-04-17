package uta.cse3310;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserInterfaceTest {

    @Test
    public void testGetReadyUp() {
        // Create a UserInterface object with readyUp set to true
        UserInterface userInterface = new UserInterface(true);
        
        // Verify that getReadyUp returns true
        assertTrue(userInterface.getReadyUp()); // Checks if readyUp is true

        userInterface.setReadyUp(false);
        assertFalse(userInterface.getReadyUp()); 
    }

    @Test
    public void testSetReadyUp() {
        // Create a UserInterface object with readyUp set to false
        UserInterface userInterface = new UserInterface(false);

        // Set readyUp to true
        userInterface.setReadyUp(true);
        
        // Verify that getReadyUp returns true after setting it to true
        assertTrue(userInterface.getReadyUp());
    }

    @Test
    public void testUnReadyUp(){
        // Create a UserInterface object with readyUp set to true
        UserInterface userInterface = new UserInterface(true);

        // Set readyUp to false
        userInterface.setReadyUp(false);

        // Verify that getReadyUp returns false after setting it to false
        assertFalse(userInterface.getReadyUp());
    }

    // Add more test methods as needed to cover other functionalities of the UserInterface class
}

