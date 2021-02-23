package mancala.domain;

public class PlayingBowl extends Bowl {
	
	private static final int STARTING_NUMBER_OF_STONES_DEFAULT = 4;
	
	public PlayingBowl() {
		super();
		this.setNumberOfStartingStones(STARTING_NUMBER_OF_STONES_DEFAULT);
		this.setNeighbour(this, 1, STARTING_NUMBER_OF_STONES_DEFAULT, BOWLS_PER_PLAYER_DEFAULT);
	}
	
	public PlayingBowl(int startingNumberOfStones, int totalNumberOfBowls) {
		super();
		this.setNumberOfStartingStones(startingNumberOfStones);
		this.setNeighbour(this, 1, startingNumberOfStones, totalNumberOfBowls);
	}
	
	public PlayingBowl(Player player, Bowl firstBowl, int numbOfCreatedBowls, int startingNumberOfStones, int totalNumberOfBowls) {
		super(player);
		this.setNumberOfStartingStones(startingNumberOfStones);
		this.setNeighbour(firstBowl, numbOfCreatedBowls, startingNumberOfStones, totalNumberOfBowls);
	}
	
	@Override
	protected void setNeighbour(Bowl firstBowl, int numberOfCreatedBowls, int startingNumberOfStones, int totalNumberOfBowls) {
		if (numberOfCreatedBowls < totalNumberOfBowls) {
			this.neighbour = new PlayingBowl(this.getPlayer(), firstBowl, numberOfCreatedBowls + 1, startingNumberOfStones, totalNumberOfBowls);
		} else {
			this.neighbour = new Kalaha(this.getPlayer(), firstBowl, numberOfCreatedBowls + 1, startingNumberOfStones, totalNumberOfBowls);
		}
	}
	
	@Override
	public PlayingBowl getOpposite() {
		return (PlayingBowl) this.getNeighbour().getOpposite().getNeighbour();
	}
	
	@Override
	public Bowl getKalahaOfActivePlayer() {
		return this.getNeighbour().getKalahaOfActivePlayer();
	}
	
	@Override
	protected void passStonesToKalahaOfActivePlayer(int stones) {
		this.getNeighbour().passStonesToKalahaOfActivePlayer(stones);
	}
	
	private void emptyBowlAndPassAllStonesToKalahaOfActivePlayer() {
		this.passStonesToKalahaOfActivePlayer(this.getNumberOfStones());
		this.makeEmpty();
	}
	
	@Override
	protected void keepOneStoneAndPassRemaining(int stones) {
		if (stones > 1) {
			super.keepOneStoneAndPassRemaining(stones);
		} else {
			this.addOneStone();
			if (this.canStealFromOppositeBowl()) {
				this.stealFromOppositeBowl();
			}
			this.getPlayer().changeActivePlayer();
		}
	}
	
	private boolean canStealFromOppositeBowl() {
		return this.belongsToActivePlayer() && this.getNumberOfStones() == 1;
	}
	
	private void stealFromOppositeBowl() {
		PlayingBowl opposite = this.getOpposite();
		if (opposite.getNumberOfStones() > 0) {
			this.emptyBowlAndPassAllStonesToKalahaOfActivePlayer();
			opposite.emptyBowlAndPassAllStonesToKalahaOfActivePlayer();
			this.checkIfGameIsOver();
		}
	}
	
	private void makeEmpty() {
		this.numberOfStones = 0;
	}
	
	public void playBowl() {
		int numbOfPassingStones = this.getNumberOfStones();
		if (numbOfPassingStones > 0) {
			this.makeEmpty();
			this.getNeighbour().keepOneStoneAndPassRemaining(numbOfPassingStones);
			if (this.isLastBowlOfPlayer()) {
				this.checkIfGameIsOver();
			}
		}
	}
	
	private boolean isLastBowlOfPlayer() {
		if (this.getNeighbour() instanceof Kalaha) {
			return true;
		}
		return false;
	}
	
	@Override
	public Bowl getFirstBowlOfPlayer(Player player) {
		return this.getNeighbour().getFirstBowlOfPlayer(player);
	}
	
	@Override
	public int getFinalNumberOfStonesOfPlayer() {
		return this.getNumberOfStones() + this.getNeighbour().getFinalNumberOfStonesOfPlayer();
	}
}
