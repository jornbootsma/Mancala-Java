package mancala.domain;

public abstract class Bowl {

	private static final int BOWLS_PER_PLAYER = 6;
	protected int NUMB_OF_CREATED_BOWLS;
	
	protected int NUMBER_OF_STONES;
	
	protected Bowl neighbour;
	private Bowl firstBowl;
	
	private Player player;
	
	public Bowl() {
		this(new Player(), null, 1);
	}
	
	public Bowl(Player player, Bowl firstBowl) {
		this(player, firstBowl, 1);
	}
	
	public Bowl(Player player, Bowl firstBowl, int numbOfCreatedBowls) {
		this.player = player;
		this.setNumberOfStartingStones();
		this.setFirstBowl(firstBowl);
		this.NUMB_OF_CREATED_BOWLS = numbOfCreatedBowls;
		this.setNeighbour(this.firstBowl, this.NUMB_OF_CREATED_BOWLS);
	}
	
	private void setFirstBowl(Bowl firstBowl) {
		if (firstBowl == null) {
			this.firstBowl = this;
		} else {
			this.firstBowl = firstBowl;
		}
	}
	
	protected abstract void setNeighbour(Bowl firstBowl, int numbOfCreatedBowls);
	
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
	
	public int getTotalNumberOfBowlsPerPlayer() {
		return BOWLS_PER_PLAYER;
	}
    
    public Bowl getFirstBowl() {
    	return this.firstBowl;
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
    
    public boolean hasStones() {
		return this.getNumberOfStones() > 0;
	}
	
	public boolean isEmpty() {
		return !this.hasStones();
	}
    
    protected abstract void passStonesToKalahaOfActivePlayer(int stones);
    
    protected abstract void keepOneStoneAndPassRemaining(int stones);
    
	public int getNumberOfBowls() {
		int numberOfBowls = 1;
		return this.getNumberOfBowls(numberOfBowls);
	}
	
	public int getNumberOfBowls(int numberOfBowls) {
		if (this.getNeighbour() != this.getFirstBowl()) {
			return this.getNeighbour().getNumberOfBowls(numberOfBowls + 1);
		}
		return numberOfBowls;
	}
	
	public void checkIfGameIsOver() {
		if (this.gameIsOver()) {
			this.calculateWinner();
			
		}
	}
	
	public boolean gameIsOver() {
    	boolean gameIsOver = false;
    	boolean firstPlayerIsOutOfStones = this.getFirstBowl().allBowlsOfPlayerAreEmpty();
    	boolean secondPlayerIsOutOfStones = this.getFirstBowlOfOtherPlayer().allBowlsOfPlayerAreEmpty();
    	if (firstPlayerIsOutOfStones || secondPlayerIsOutOfStones) {
    		gameIsOver = true;
    	}
    	return gameIsOver;
    }
    
	public abstract boolean allBowlsOfPlayerAreEmpty();
	
	public abstract Bowl getFirstBowlOfOtherPlayer();
	
	public void calculateWinner() {
		int numberOfStonesOfFirstPlayer = this.getFirstBowl().getFinalNumberOfStonesOfPlayer();
		
		if (numberOfStonesOfFirstPlayer > this.getNumberOfStartingStones() * BOWLS_PER_PLAYER) {
			this.getPlayer().setWinner();
		} else if (numberOfStonesOfFirstPlayer < this.getNumberOfStartingStones() * BOWLS_PER_PLAYER) {
			this.getPlayer().getOpponent().setWinner();
		} else {
			this.getPlayer().setWinner();
			this.getPlayer().getOpponent().setWinner();
		}
	}
	
	public abstract int getFinalNumberOfStonesOfPlayer();
}
