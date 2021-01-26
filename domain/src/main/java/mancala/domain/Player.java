package mancala.domain;

public class Player {
	
	private final static String firstPlayerName = "A";
	private final static String secondPlayerName = "B";
	
	private String name;
	private Player opponent;
	private boolean activePlayer;
	
	public Player() {
		this(firstPlayerName, secondPlayerName);
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(String thisPlayername, String opponentName) {
		this.name = thisPlayername;
		this.setOpponent(new Player(opponentName));
		this.setActivePlayer();
	}
	
	protected void setOpponent(Player opponent) {
		this.opponent = opponent;
		opponent.opponent = this;
	}
	
	private void setActivePlayer() {
		this.activePlayer = true;
		this.opponent.activePlayer = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Player getOpponent() {
		return this.opponent;
	}
	
	public String getActivePlayer() {
		if (this.isActivePlayer()) {
			return this.name;
		} else {
			return this.opponent.name;
		}
	}
	
	public boolean isActivePlayer() {
		return this.activePlayer;
	}
	
	protected void changeActivePlayer() {
		this.activePlayer = !this.activePlayer;
		this.opponent.activePlayer = !this.opponent.activePlayer;
	}

}
