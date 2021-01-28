package mancala.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	Player player = new Player("1", "2");
	
	@Test
	public void getName() {
		assertEquals("1", player.getName());
	}
	
	@Test
	public void hasOpponent() {
		Player opponent = player.getOpponent();
		assertEquals("2", opponent.getName());
	}
	
	@Test
	public void isActivePlayer() {
		boolean isActivePlayer = player.isActivePlayer();
		assertTrue(isActivePlayer);
	}
	
	@Test
	public void changeActivePlayer() {
		player.changeActivePlayer();
		boolean opponentIsActivePlayer = player.getOpponent().isActivePlayer();
		assertTrue(opponentIsActivePlayer);
	}
	
	@Test
	public void getActivePlayer() {
		Player activePlayer = player.getActivePlayer();
		assertEquals("1", activePlayer.getName());
	}
	
	@Test
	public void isWinner() {
		assertFalse(player.isWinner());
	}
	
	@Test
	public void setWinner() {
		player.setWinner();
		assertTrue(player.isWinner());
	}
}
