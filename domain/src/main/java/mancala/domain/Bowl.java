package mancala.domain;

public abstract class Bowl {

	public static final int BOWLS_PER_PLAYER_DEFAULT = 6;
	
	protected int numberOfStones;
	protected Bowl neighbour;
	private Player player;
	
	public Bowl() {
		this(new Player());
	}
	
	public Bowl(Player player) {
		this.player = player;
	}
	
	protected abstract void setNeighbour(Bowl firstBowl, int numberOfCreatedBowls, int startingNumberOfStones, int totalNumberOfBowls);
	
	protected void setNumberOfStartingStones(int startingNumberOfStones) {
		this.numberOfStones = startingNumberOfStones;
	}
	
	public abstract Bowl getOpposite();
    
    public abstract Bowl getKalahaOfActivePlayer();
    
	public Player getPlayer() {
		return this.player;
	}
	
	public int getNumberOfStones() {
        return this.numberOfStones;
    }
	
    public Bowl getNeighbour() {
    	return this.neighbour;
    }
    
	public boolean belongsToActivePlayer() {
		return this.getPlayer().isActivePlayer();
	}
    
    protected void addOneStone() {
    	this.numberOfStones ++;
    }
    
	public boolean isEmpty() {
		return this.getNumberOfStones() == 0;
	}
    
    protected abstract void passStonesToKalahaOfActivePlayer(int stones);

	protected abstract void passStonesToKalahaOfPlayer(Player player, int stones);
    
    protected void keepOneStoneAndPassRemaining(int stones) {
    	this.addOneStone();
		if (stones > 1) {
			this.getNeighbour().keepOneStoneAndPassRemaining(stones - 1);
		}
    }
    
    public Bowl getNthNeighbour(int n) {
    	if (n > 0) {
    		return this.getNeighbour().getNthNeighbour(n - 1);
    	}
    	return this;
    }
    
	public void checkIfGameIsOver() {
		if (this.gameIsOver()) {
			this.calculateWinner();
			this.finishGame();
		}
	}
	
	public boolean gameIsOver() {
    	boolean gameIsOver = false;
    	boolean firstPlayerIsOutOfStones = this.getFirstBowlOfPlayer(this.getPlayer()).allBowlsOfPlayerAreEmpty();
    	boolean secondPlayerIsOutOfStones = this.getFirstBowlOfPlayer(this.getOpposite().getPlayer()).allBowlsOfPlayerAreEmpty();
    	if (firstPlayerIsOutOfStones || secondPlayerIsOutOfStones) {
    		gameIsOver = true;
    	}
    	return gameIsOver;
    }
	
	private boolean allBowlsOfPlayerAreEmpty() {
		if (this instanceof Kalaha) {
			return true;
		} else if (this.isEmpty()) {
			return this.getNeighbour().allBowlsOfPlayerAreEmpty();
		}
		return false;
	}
	
	public abstract Bowl getFirstBowlOfPlayer(Player player);

	private void finishGame() {
		Bowl firstBowlOfFirstPlayer = this.getFirstBowlOfPlayer(this.getPlayer());
		Bowl firstBowlOfSecondPlayer = this.getFirstBowlOfPlayer(this.getPlayer().getOpponent());
		
		firstBowlOfFirstPlayer.passAllStonesOfPlayerToOwnKalaha();
		firstBowlOfSecondPlayer.passAllStonesOfPlayerToOwnKalaha();
	}

	protected void passAllStonesOfPlayerToOwnKalaha() {  }
	
	public int getNumberOfStartingStonesPerPlayingBowl() {
		Bowl firstBowlOfPlayer = this.getFirstBowlOfPlayer(this.getPlayer());
		int stonesInFirstBowl = firstBowlOfPlayer.getNumberOfStones();
		int totalNumberOfStones = this.getNeighbour().getTotalNumberOfStones(this.getPlayer(), stonesInFirstBowl);
		int totalNumberOfBowls = this.getTotalNumberOfPlayingBowls();
		return totalNumberOfStones / totalNumberOfBowls;
	}
	
	private int getTotalNumberOfStones(Player thisPlayer, int numberOfStones) {
		if (this instanceof Kalaha && this.getPlayer() != thisPlayer) {
			numberOfStones += this.getNumberOfStones();
			return numberOfStones;
		} else {
			numberOfStones += this.getNumberOfStones();
			return this.getNeighbour().getTotalNumberOfStones(thisPlayer, numberOfStones);
		}
	}
	
	private int getTotalNumberOfPlayingBowls() {
		Bowl firstBowlOfPlayer = this.getFirstBowlOfPlayer(this.getPlayer());
		int totalNumberOfBowlsPerPlayer = firstBowlOfPlayer.getNeighbour().getNumberOfPlayingBowlsPerPlayer(1);
		return totalNumberOfBowlsPerPlayer * 2;
	}
	
	private int getNumberOfPlayingBowlsPerPlayer(int numberOfBowls) {
		if (this instanceof Kalaha) {
			return numberOfBowls;
		} else {
			return this.getNeighbour().getNumberOfPlayingBowlsPerPlayer(numberOfBowls + 1);
		}
	}
	
	public void calculateWinner() {
		int numberOfStonesOfFirstPlayer = this.getFirstBowlOfPlayer(this.getPlayer()).getFinalNumberOfStonesOfPlayer();
		
		if (numberOfStonesOfFirstPlayer > this.getNumberOfStartingStonesPerPlayingBowl() * BOWLS_PER_PLAYER_DEFAULT) {
			this.getPlayer().setWinner();
		} else if (numberOfStonesOfFirstPlayer < this.getNumberOfStartingStonesPerPlayingBowl() * BOWLS_PER_PLAYER_DEFAULT) {
			this.getPlayer().getOpponent().setWinner();
		} else {
			this.getPlayer().setDraw();
		}
	}
	
	public Player getWinner() {
		return this.getPlayer().getWinner();
	}
	
	public abstract int getFinalNumberOfStonesOfPlayer();
}
