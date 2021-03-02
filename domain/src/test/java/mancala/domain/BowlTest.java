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
    public void gameIsNotOverAtStart() {
    	boolean gameIsOver = bowl.gameIsOver();
    	assertFalse(gameIsOver);
    }

	@Test
	public void playerHasNoStonesAndGameIsOver() {
		bowl = new PlayingBowl(4, 6);
		for (int i = 0; i < 6; i++) {
			bowl.getNthNeighbour(i).numberOfStones = 0;
		}
		bowl.checkIfGameIsOver();
		assertTrue(bowl.gameIsOver());
	}

	@Test
	public void noWinnerAtStart() {
		assertEquals(null, bowl.getWinner());
	}

	@Test
	public void calculateWinnerReturnsNull() {
		bowl.calculateWinner();
		assertEquals(null, bowl.getWinner());
	}

	@Test
	public void firstPlayerWins() {
		Player player = new Player("Mario", "Luigi");
		Bowl bowl = new PlayingBowl(player);
		for (int i = 0; i < 6; i++) {
			bowl.getNthNeighbour(i).numberOfStones = 0;
		}
		bowl.calculateWinner();
		assertEquals("Luigi", bowl.getWinner().getName());
	}

	@Test
	public void gameIsOverAndAllStonesArePassedToKalaha() {
		bowl = new PlayingBowl(4, 6);
		for (int i = 0; i < 6; i++) {
			bowl.getNthNeighbour(i).numberOfStones = 0;
		}
		bowl.checkIfGameIsOver();
		Bowl kalahaOfOpponent = bowl.getNthNeighbour(13);
		assertEquals(24, kalahaOfOpponent.getNumberOfStones());
	}
}
