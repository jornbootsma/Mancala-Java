package mancala.domain;

// Your test class should be in the same 
// package as the class you're testing.
// Usually the test directory mirrors the
// main directory 1:1. So for each class in src/main,
// there is a class in src/test.

// Import our test dependencies. We import the Test-attribute
// and a set of assertions.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayingBowlTest {
    // Define a test starting with @Test. The test is like
    // a small main method - you need to setup everything
    // and you can write any arbitrary Java code in it.
    @Test 
    public void aNormalBorlStartsWith4Stones() {
        PlayingBowl bowl = new PlayingBowl(1);
        assertEquals(4, bowl.getNumberOfStones());
    }
    
    @Test
    public void bowlBelongsToPlayer1() {
    	PlayingBowl bowl = new PlayingBowl(1);
    	assertEquals(1, bowl.getPlayerID());
    }
    
    @Test
    public void bowlBelongsToPlayer2() {
    	PlayingBowl bowl = new PlayingBowl(2);
    	assertEquals(2, bowl.getPlayerID());
    }
    
    
    
}