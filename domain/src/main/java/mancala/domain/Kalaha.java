package mancala.domain;

public class Kalaha extends Bowl {
	
	private static final int STARTING_NUMBER_OF_STONES_DEFAULT = 0;
	
	public Kalaha(Player player, Bowl firstBowl, int numberOfCreatedBowls, int startingNumberOfStonesOfPlayingBowl, int totalNumberOfBowls) {
		super(player);
		this.setNumberOfStartingStones(STARTING_NUMBER_OF_STONES_DEFAULT);
		this.setNeighbour(firstBowl, numberOfCreatedBowls, startingNumberOfStonesOfPlayingBowl, totalNumberOfBowls);
	}
	
	@Override
	protected void setNeighbour(Bowl firstBowl, int numberOfCreatedBowls, int startingNumberOfStonesOfPlayingBowl, int totalNumberOfBowls) {
		if (this.belongsToActivePlayer()) {
			this.neighbour = new PlayingBowl(this.getPlayer().getOpponent(), firstBowl, 1, startingNumberOfStonesOfPlayingBowl, totalNumberOfBowls);
		} else {
			this.neighbour = firstBowl;
		}
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
		this.numberOfStones += number;
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
	protected void passStonesToKalahaOfPlayer(Player player, int stones) {
		if (this.getPlayer() == player) {
			this.addStones(stones);
		} else {
			this.getNeighbour().passStonesToKalahaOfPlayer(player, stones);
		}
	}
	
	@Override
	protected void keepOneStoneAndPassRemaining(int stones) {
		if (this.belongsToActivePlayer()) {
			super.keepOneStoneAndPassRemaining(stones);
		} else {
			this.getNeighbour().keepOneStoneAndPassRemaining(stones);
		}
	}
	
	@Override
	public Bowl getFirstBowlOfPlayer(Player player) {
		if (this.getPlayer() != player) {
			return this.getNeighbour();
		}
		return this.getNeighbour().getFirstBowlOfPlayer(player);
	}
	
	@Override
	public int getFinalNumberOfStonesOfPlayer() {
		return this.getNumberOfStones();
	}
}
