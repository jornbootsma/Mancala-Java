package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayingBowlTest {
	private PlayingBowl playingBowl;
	
	@BeforeEach
	public void setUp() {
		playingBowl = new PlayingBowl();
	}
	
    @Test
    public void playingBowlStartsFourStones() {
        int numbOfStones = playingBowl.getNumberOfStones();
        assertEquals(4, numbOfStones);
    }
    
//    @Test
//    public void boardHas14Bowls() {
//    	
//    }
    
    @Test
    public void hasStones() {
    	boolean hasStones = playingBowl.hasStones();
    	assertTrue(hasStones);
    }
    
//    @Test
//    public void getOpposite() {
//    	Bowl opposite = playingBowl.getOpposite();
//    	
//    }
    
    @Test
    public void makeBowlEmpty() {
    	playingBowl.makeEmpty();
    	assertFalse(playingBowl.hasStones());
    }
    
    @Test
    public void passAllStonesToKalaha() {
    	int initialAmount = playingBowl.getKalahaOfActivePlayer().getNumberOfStones();
    	playingBowl.passStonesToOwnKalaha(3);
    	int newAmount = playingBowl.getKalahaOfActivePlayer().getNumberOfStones();
    	assertEquals(initialAmount, newAmount - 3);
    }
    
    @Test
    public void emptyBowlAndPassAllStonesToKalaha() {
    	int initialAmountOfKalaha = playingBowl.getKalahaOfActivePlayer().getNumberOfStones();
    	int initialAmountOfPlayingBowl = playingBowl.getNumberOfStones();
    	playingBowl.emptyBowlAndPassAllStonesToKalaha();
    	int newAmountOfKalaha = playingBowl.getKalahaOfActivePlayer().getNumberOfStones();
    	assertFalse(playingBowl.hasStones());
    	assertEquals(newAmountOfKalaha, initialAmountOfKalaha + initialAmountOfPlayingBowl);
    }
    
    @Test
    public void performMoveAndBowlIsEmpty() {
    	playingBowl.playBowl();
    	assertFalse(playingBowl.hasStones());
    }
    
    @Test
    public void passStonesAndKeepOne_BowlHasOneExtraStone() {
    	int initialAmount = playingBowl.getNumberOfStones();
    	playingBowl.passStonesAndKeepOne(3);
    	int newAmount = playingBowl.getNumberOfStones();
    	assertEquals(initialAmount, newAmount - 1);
    }
    
    @Test
    public void performMoveAndNeighbourHasOneExtraStone() {
    	int initialAmount = playingBowl.getNeighbour().getNumberOfStones();
    	playingBowl.playBowl();
    	int newAmount = playingBowl.getNeighbour().getNumberOfStones();
    	assertEquals(initialAmount, newAmount - 1);
    }
}