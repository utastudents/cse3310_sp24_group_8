package uta.cse3310;

import org.junit.Test;
import static org.junit.Assert.*;
//import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PlayerTypeTest {

    @Test
    public void CheckNickAndColor(){
        try{
            new PlayerType(null, "Green", PlayerType.Status.Waiting);
            fail("Expected IllegalArgumentException for null nickname");
        }catch(IllegalArgumentException e){
            assertEquals("Nickname cannot be null.", e.getMessage());
        }
    }
    
}
