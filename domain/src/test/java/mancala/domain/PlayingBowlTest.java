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
    
    @Test
    public void performMoveSwitchesTurn() {
    	playingBowl.playBowl();
    	assertFalse(playingBowl.getPlayer().isActivePlayer());
    }
    
    @Test
    public void lastStoneEndsInKalahaAndStillActivePlayer() {
    	playingBowl.numberOfStones = 6;
    	assertTrue(playingBowl.getPlayer().isActivePlayer());
    }
    
    @Test
    public void playEmptyBowlDoesNotGiveStoneToNeighbour() {
    	playingBowl.numberOfStones = 0;
    	playingBowl.playBowl();
    	assertEquals(4, playingBowl.getNeighbour().getNumberOfStones());
    }
    
    @Test
    public void endInEmptyBowlResultsInStealingFromOpposite() {
    	Bowl opposite = playingBowl.getNeighbour().getOpposite();
    	playingBowl.numberOfStones = 1;
    	playingBowl.getNeighbour().numberOfStones = 0;
    	playingBowl.playBowl();
    	assertEquals(0, opposite.getNumberOfStones());
    }
    
    @Test
    public void endInEmptyBowlResultsInKalahaReceivingStones() {
    	Bowl kalaha = playingBowl.getKalahaOfActivePlayer();
    	playingBowl.numberOfStones = 1;
    	playingBowl.getNeighbour().numberOfStones = 0;
    	playingBowl.playBowl();
    	assertEquals(5, kalaha.getNumberOfStones());
    }
}