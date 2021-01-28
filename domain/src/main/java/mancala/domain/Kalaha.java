package mancala.domain;

public class Kalaha extends Bowl {
	
	private static final int STARTING_NUMBER_OF_STONES = 0;
	
	public Kalaha() {
		super();
	}
	
	public Kalaha(Player player, Bowl firstBowl, int numbOfCreatedBowls) {
		super(player, firstBowl, numbOfCreatedBowls);
	}
	
	@Override
	protected void setNeighbour(Bowl firstBowl, int numbOfCreatedBowls) {
		if (this.belongsToActivePlayer()) {
			this.neighbour = new PlayingBowl(this.getPlayer().getOpponent(), firstBowl);
		} else {
			this.neighbour = this.getFirstBowl();
		}
	}
	
	@Override
	public int getNumberOfStartingStones() {
		return STARTING_NUMBER_OF_STONES;
	}
	
	@Override
	public Kalaha getOpposite() {
		return this;
	}
	
	@Override
	public Bowl getKalahaOfActivePlayer() {
		if (this.belongsToActivePlayer()) {
			return this;
		} else {
			return this.getNeighbour().getKalahaOfActivePlayer();
		}
	}
	
	private void addStones(int number) {
		this.NUMBER_OF_STONES += number;
	}
	
	@Override
	protected void passStonesToKalahaOfActivePlayer(int stones) {
		if (this.belongsToActivePlayer()) {
			this.addStones(stones);
		} else {
			this.getNeighbour().passStonesToKalahaOfActivePlayer(stones);
		}
	}
	
	@Override
	protected void keepOneStoneAndPassRemaining(int stones) {
		if (this.belongsToActivePlayer()) {
			this.addOneStone();
			if (stones > 1) {
				this.getNeighbour().keepOneStoneAndPassRemaining(stones - 1);
			}
		} else {
			this.getNeighbour().keepOneStoneAndPassRemaining(stones);
		}
	}
	
	@Override
	public Bowl getFirstBowlOfOtherPlayer() {
		return this.getNeighbour();
	}
	
	@Override
	public boolean allBowlsOfPlayerAreEmpty() {
		// You can only get here if all the previous
		// balls of this player are empty.
		return true;
	}
	
	@Override
	public int getFinalNumberOfStonesOfPlayer() {
		return this.getNumberOfStones();
	}
}
