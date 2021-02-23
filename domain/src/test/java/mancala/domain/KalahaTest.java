package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KalahaTest {
	private Kalaha kalaha;
	private Bowl playingBowl;
	
	@BeforeEach
	public void setUp() {
		playingBowl = new PlayingBowl();
		kalaha = (Kalaha) playingBowl.getNthNeighbour(6);
	}
	
	@Test 
    public void kalahaStartsWith0Stones() {
		int numbOfStones = kalaha.getNumberOfStones();
        assertEquals(0, numbOfStones);
    }
	
	@Test
	public void getOppositeAndReturnItself() {
		Kalaha opposite = kalaha.getOpposite();
		assertEquals(kalaha, opposite);
	}
	
	@Test
	public void secondKalahaHasFirstBowlAsNeighbour() {
		Bowl playingBowl = new PlayingBowl();
		playingBowl.getPlayer().changeActivePlayer();
		Bowl kalaha = playingBowl.getKalahaOfActivePlayer();
		assertEquals(playingBowl.getFirstBowlOfPlayer(playingBowl.getPlayer()), kalaha.getNeighbour());
	}
	
	@Test
	public void skipKalahaOfOpponent() {
		PlayingBowl playingBowl = new PlayingBowl();
		playingBowl.numberOfStones = 13;
		playingBowl.playBowl();
		Bowl kalaha = playingBowl.getKalahaOfActivePlayer();
		assertEquals(0, kalaha.getNumberOfStones());
	}
}
