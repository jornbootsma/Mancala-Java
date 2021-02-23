package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlTest {
	private Bowl bowl;
	
	@BeforeEach
	public void setUp() {
		bowl = new PlayingBowl();
	}
	
	@Test
	public void bowlBelongsToActivePlayer() {
    	boolean belongsToPlayer1 = bowl.belongsToActivePlayer();
    	assertTrue(belongsToPlayer1);
    }
	
	@Test
    public void addOneStone() {
    	bowl.addOneStone();
    	int newAmount = bowl.getNumberOfStones();
    	assertEquals(5, newAmount);
    }
	
	@Test
    public void getKalahaOfActivePlayer() {
    	Bowl kalaha = bowl.getKalahaOfActivePlayer();
    	assertTrue((kalaha.getPlayer() == bowl.getPlayer()) && (bowl.getPlayer().isActivePlayer()));
    }
	
	@Test
	public void boardHas14Bowls() {
		Bowl nthNeighbour = bowl.getNthNeighbour(14);
		assertEquals(bowl, nthNeighbour);
	}
	
	@Test
    public void gameIsOver() {
    	boolean gameIsOver = bowl.gameIsOver();
    	assertFalse(gameIsOver);
    }
}
