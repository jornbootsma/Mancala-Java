package mancala.domain;

public abstract class Bowl {

	protected static final int BOWLS_PER_PLAYER = 6;
	protected static int NUMB_OF_CREATED_BOWLS = 0;
	
	protected int NUMBER_OF_STONES;
	
	protected Bowl neighbour;
	private static Bowl firstBowl;
	
	private Player player;
	
	public Bowl() {
		this.player = new Player();
		firstBowl = this;
		this.setNeighbour();
	}
	
	public Bowl(Player player) {
		this.player = player;
		this.setNeighbour();
	}
	
	protected abstract void setNeighbour();
	
	protected abstract void setNumberOfStartingStones();
	
	public abstract Bowl getOpposite();
    
    public abstract Bowl getKalahaOfActivePlayer();
    
	public Player getPlayer() {
		return this.player;
	}
	
	public int getNumberOfStones() {
        return this.NUMBER_OF_STONES;
    }
    
    public Bowl getFirstBowl() {
    	return firstBowl;
    }
    
    public Bowl getNeighbour() {
    	return this.neighbour;
    }
    
	public boolean belongsToActivePlayer() {
		return this.getPlayer().isActivePlayer();
	}
    
    protected void addOneStone() {
    	this.NUMBER_OF_STONES ++;
    }
    
    protected abstract void passStonesToOwnKalaha(int stones);
    
    protected abstract void passStonesAndKeepOne(int stones);
}