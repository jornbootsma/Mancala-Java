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
    
    @Test
    public void passStonesToKalahaOfActivePlayer() {
    	playingBowl.passStonesToKalahaOfActivePlayer(2);
    	int newAmount = playingBowl.getKalahaOfActivePlayer().getNumberOfStones();
    	assertEquals(2, newAmount);
    }
    
    @Test
    public void performMoveAndBowlIsEmpty() {
    	playingBowl.playBowl();
    	assertTrue(playingBowl.isEmpty());
    }
    
    @Test
    public void performMoveAndNeighbourHasOneExtraStone() {
    	playingBowl.playBowl();
    	int newAmount = playingBowl.getNeighbour().getNumberOfStones();
    	assertEquals(5, newAmount);
    }
}