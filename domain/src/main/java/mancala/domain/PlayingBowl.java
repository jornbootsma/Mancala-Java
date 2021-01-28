package mancala.domain;

public class PlayingBowl extends Bowl {
	
	private static final int STARTING_NUMBER_OF_STONES = 4;
	
	public PlayingBowl() {
		super();
	}
	
	public PlayingBowl(Player player, Bowl firstBowl, int numbOfCreatedBowls) {
		super(player, firstBowl, numbOfCreatedBowls);
	}
	
	@Override
	protected void setNeighbour(Bowl firstBowl, int numbOfCreatedBowls) {
		if (this.NUMB_OF_CREATED_BOWLS < this.getTotalNumberOfBowlsPerPlayer()) {
			this.neighbour = new PlayingBowl(this.getPlayer(), firstBowl, numbOfCreatedBowls + 1);
		} else {
			this.neighbour = new Kalaha(this.getPlayer(), firstBowl, numbOfCreatedBowls + 1);
		}
	}
	
	@Override
	public int getNumberOfStartingStones() {
		return STARTING_NUMBER_OF_STONES;
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
		this.addOneStone();
		if (stones > 1) {
			this.getNeighbour().keepOneStoneAndPassRemaining(stones - 1);
		} else {
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
		this.NUMBER_OF_STONES = 0;
	}
	
	public void playBowl() {
		int numbOfPassingStones = this.getNumberOfStones();
		this.makeEmpty();
		this.getNeighbour().keepOneStoneAndPassRemaining(numbOfPassingStones);
		if (this.isLastBowlOfPlayer()) {
			this.checkIfGameIsOver();
		}
	}
	
	private boolean isLastBowlOfPlayer() {
		if (this.getNeighbour() instanceof Kalaha) {
			return true;
		}
		return false;
	}
	
	@Override
	public Bowl getFirstBowlOfOtherPlayer() {
		return this.getNeighbour().getFirstBowlOfOtherPlayer();
	}
	
	@Override
	public boolean allBowlsOfPlayerAreEmpty() {
		if (this.isEmpty()) {
			return this.getNeighbour().allBowlsOfPlayerAreEmpty();
		}
		return false;
	}
	
	@Override
	public int getFinalNumberOfStonesOfPlayer() {
		return this.getNumberOfStones() + this.getNeighbour().getFinalNumberOfStonesOfPlayer();
	}
}
