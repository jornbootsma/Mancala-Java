package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	Player playerA = new Player("A");
	Player playerB = new Player("B");
	Player player = new Player("A", "B");
	
	@Test
	public void nameOfPlayer() {
		assertEquals("A", playerA.getName());
	}
	
	@Test
	public void setOpponent() {
		playerA.setOpponent(playerB);
		assertEquals(playerA.getOpponent().getName(), playerB.getName());
	}
	
	@Test
	public void hasOpponent() {
		Player opponent = player.getOpponent();
		assertNotEquals(opponent, null);
	}
	
	@Test
	public void isActivePlayer() {
		boolean isActivePlayer = player.isActivePlayer();
		assertTrue(isActivePlayer);
	}
	
	@Test
	public void changeActivePlayer() {
		player.changeActivePlayer();
		boolean opponenIsActivePlayer = player.getOpponent().isActivePlayer();
		assertTrue(opponenIsActivePlayer);
	}
	
	@Test
	public void getActivePlayer() {
		String activePlayer = player.getActivePlayer();
		assertEquals(activePlayer, "A");
	}
}
