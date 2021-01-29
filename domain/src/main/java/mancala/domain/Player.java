package mancala.domain;

public class Player {
	
	private final static String firstPlayerName = "1";
	private final static String secondPlayerName = "2";
	
	private String name;
	private Player opponent;
	private boolean activePlayer;
	
	private boolean gameEnded = false;
	private boolean isWinner = false;
	private boolean isDraw = false;
	
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
	
	private void setOpponent(Player opponent) {
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
	
	public Player getActivePlayer() {
		if (this.isActivePlayer()) {
			return this;
		} else {
			return this.opponent;
		}
	}
	
	public boolean isActivePlayer() {
		return this.activePlayer;
	}
	
	protected void changeActivePlayer() {
		this.activePlayer = !this.activePlayer;
		this.opponent.activePlayer = !this.opponent.activePlayer;
	}
	
	private void setGameEnded() {
		this.gameEnded = true;
	}
	
	protected void setWinner() {
		this.isWinner = true;
		this.setGameEnded();
	}
	
	public boolean isWinner() {
		return this.isWinner;
	}
	
	protected void setDraw() {
		this.isDraw = true;
		this.opponent.isDraw = true;
		this.setGameEnded();
	}
	
	public boolean isDraw() {
		return this.isDraw;
	}
	
	public boolean gameIsEnded() {
		return this.gameEnded;
	}
	
	public String getWinner() {
		if (this.isDraw) {
			return "Draw";
		} else if (this.isWinner()) { 
			return this.getName();
		} else if (this.getOpponent().isWinner()){
			return this.getOpponent().getName();
		}
		return "Game is not over yet";
	}
}
