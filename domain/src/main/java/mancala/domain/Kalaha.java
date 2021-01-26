package mancala.domain;

public class Kalaha extends Bowl {
	
	private static final int STARTING_NUMBER_OF_STONES = 0;
	
	public Kalaha() {
		super();
	}
	
	public Kalaha(Player player) {
		super(player);
	}
	
	@Override
	protected void setNeighbour() {
		NUMB_OF_CREATED_BOWLS = 0;
		if (this.belongsToActivePlayer()) {
			this.neighbour = new PlayingBowl(this.getPlayer().getOpponent());
		} else {
			this.neighbour = this.getFirstBowl();
		}
	}
	
	@Override
	protected void setNumberOfStartingStones() {
		this.NUMBER_OF_STONES = STARTING_NUMBER_OF_STONES;
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
	
	protected void addStones(int number) {
		this.NUMBER_OF_STONES += number;
	}
	
	@Override
	protected void passStonesToOwnKalaha(int stones) {
		if (this.belongsToActivePlayer()) {
			this.addStones(stones);
		} else {
			this.getNeighbour().passStonesToOwnKalaha(stones);
		}
	}
	
	@Override
	protected void passStonesAndKeepOne(int stones) {
		if (!this.belongsToActivePlayer()) {
			this.getNeighbour().passStonesAndKeepOne(stones);
		} else {
			this.addOneStone();
			if (stones > 1) {
				this.getNeighbour().passStonesAndKeepOne(stones - 1);
			}
		}
	}
}
