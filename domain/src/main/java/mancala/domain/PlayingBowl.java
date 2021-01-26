package mancala.domain;

public class PlayingBowl extends Bowl {
	
	private static final int STARTING_NUMBER_OF_STONES = 4;
	
	public PlayingBowl() {
		super();
	}
	
	public PlayingBowl(Player player) {
		super(player);
	}
	
	@Override
	protected void setNumberOfStartingStones() {
		this.NUMBER_OF_STONES = STARTING_NUMBER_OF_STONES;
	}
	
	@Override
	protected void setNeighbour() {
		NUMB_OF_CREATED_BOWLS ++;
		if (NUMB_OF_CREATED_BOWLS < BOWLS_PER_PLAYER) {
			this.neighbour = new PlayingBowl(this.getPlayer());
		} else {
			this.neighbour = new Kalaha(this.getPlayer());
		}
	}
	
	public boolean hasStones() {
		return this.getNumberOfStones() > 0;
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
	protected void passStonesToOwnKalaha(int stones) {
		this.getNeighbour().passStonesToOwnKalaha(stones);
	}
	
	protected void emptyBowlAndPassAllStonesToKalaha() {
		this.passStonesToOwnKalaha(this.getNumberOfStones());
		this.makeEmpty();
	}
	
	@Override
	protected void passStonesAndKeepOne(int stones) {
		this.addOneStone();
		if (stones > 1) {
			this.getNeighbour().passStonesAndKeepOne(stones - 1);
		} else {
			if (this.belongsToActivePlayer() && this.getNumberOfStones() == 1) {
				PlayingBowl opposite = this.getOpposite();
				if (opposite.getNumberOfStones() > 0) {
					opposite.emptyBowlAndPassAllStonesToKalaha();
				}
			}
			this.getPlayer().changeActivePlayer();
		}
	}
	
	protected void makeEmpty() {
		this.NUMBER_OF_STONES = 0;
	}
	
	public void playBowl() {
		int numbOfPassingStones = this.getNumberOfStones();
		this.makeEmpty();
		this.getNeighbour().passStonesAndKeepOne(numbOfPassingStones);
	}
}
