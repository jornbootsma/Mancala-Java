package mancala.api.models;

public class Player {

	public Player(mancala.domain.PlayingBowl firstBowl,
		String name, boolean isFirstPlayer) {
		this.name = name;
		type = isFirstPlayer ? "player1" : "player2";

		if (firstBowl.getPlayer().getName().equals(name)) {
			if (firstBowl.getPlayer().isActivePlayer()) {
				this.hasTurn = true;
			} else {
				this.hasTurn = false;
			}
		} else {
			if (firstBowl.getPlayer().isActivePlayer()) {
				this.hasTurn = false;
			} else {
				this.hasTurn = true;
			}
		}

		this.pits = new Pit[7];
		var firstHole = isFirstPlayer ? 0 : 7;
		for(int i = 0; i < 7; ++i) {
			this.pits[i] = new Pit(i + firstHole, firstBowl.getNthNeighbour(i + firstHole).getNumberOfStones());
		}
    }
				
    String name;
	public String getName() { return name; }
	
	String type;
	public String getType() { return type; }

	boolean hasTurn;
	public boolean getHasTurn() { return hasTurn; }

	Pit[] pits;
	public Pit[] getPits() { return pits; }
}