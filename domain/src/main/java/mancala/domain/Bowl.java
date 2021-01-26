package mancala.domain;

public abstract class Bowl {

	private static final int BOWLS_PER_PLAYER = 6;
	protected static int NUMB_OF_CREATED_BOWLS = 0;
	
	protected int NUMBER_OF_STONES;
	
	protected Bowl neighbour;
	private static Bowl firstBowl;
	
	private Player player;
	
	public Bowl() {
		this.player = new Player();
		firstBowl = this;
		this.setNeighbour();
		this.setNumberOfStartingStones();
	}
	
	public Bowl(Player player) {
		this.player = player;
		this.setNeighbour();
		this.setNumberOfStartingStones();
	}
	
	protected abstract void setNeighbour();
	
	private void setNumberOfStartingStones() {
		this.NUMBER_OF_STONES = this.getNumberOfStartingStones();
	}
	
	public abstract int getNumberOfStartingStones();
	
	public abstract Bowl getOpposite();
    
    public abstract Bowl getKalahaOfActivePlayer();
    
	public Player getPlayer() {
		return this.player;
	}
	
	public int getNumberOfStones() {
        return this.NUMBER_OF_STONES;
    }
	
	public int getBowlsPerPlayer() {
		return BOWLS_PER_PLAYER;
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

	public int getNumbOfBowls() {
		int number = 1;
		return this.getNumbOfBowls(number);
	}
	
	public int getNumbOfBowls(int number) {
		if (this.getNeighbour() != firstBowl) {
			return this.getNeighbour().getNumbOfBowls(number + 1);
		}
		return number;
	}
}