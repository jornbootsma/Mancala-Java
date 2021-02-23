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
	
	public static void main(String[] args) {
    	PlayingBowl bowl1 = new PlayingBowl(4, 6);
    	
    	Bowl bowl2 = bowl1.getNeighbour();
    	Bowl bowl3 = bowl2.getNeighbour();
    	Bowl bowl4 = bowl3.getNeighbour();
    	Bowl bowl5 = bowl4.getNeighbour();
    	Bowl bowl6 = bowl5.getNeighbour();
    	Bowl bowl7 = bowl6.getNeighbour();
    	Bowl bowl8 = bowl7.getNeighbour();
    	Bowl bowl9 = bowl8.getNeighbour();
    	Bowl bowl10 = bowl9.getNeighbour();
    	Bowl bowl11 = bowl10.getNeighbour();
    	Bowl bowl12 = bowl11.getNeighbour();
    	Bowl bowl13 = bowl12.getNeighbour();
    	Bowl bowl14 = bowl13.getNeighbour();
    	Bowl bowl15 = bowl14.getNeighbour();
    	
//    	bowl1.playBowl();
//    	bowl1.getPlayer().changeActivePlayer();
//    	((PlayingBowl) bowl2).playBowl();
//    	((PlayingBowl) bowl3).playBowl();
//    	bowl1.getPlayer().changeActivePlayer();
//    	((PlayingBowl) bowl4).playBowl();
//    	bowl1.getPlayer().changeActivePlayer();
//    	((PlayingBowl) bowl5).playBowl();
//    	bowl1.getPlayer().changeActivePlayer();
//    	System.out.println(bowl1.getPlayer().getActivePlayer());
//    	((PlayingBowl) bowl6).playBowl();
    	
    	System.out.println(bowl1.getClass() + "   " + bowl1.getNumberOfStones());
    	System.out.println(bowl2.getClass() + "   " + bowl2.getNumberOfStones());
    	System.out.println(bowl3.getClass() + "   " + bowl3.getNumberOfStones());
    	System.out.println(bowl4.getClass() + "   " + bowl4.getNumberOfStones());
    	System.out.println(bowl5.getClass() + "   " + bowl5.getNumberOfStones());
    	System.out.println(bowl6.getClass() + "   " + bowl6.getNumberOfStones());
    	System.out.println(bowl7.getClass() + "        " + bowl7.getNumberOfStones());
    	System.out.println(bowl8.getClass() + "   " + bowl8.getNumberOfStones());
    	System.out.println(bowl9.getClass() + "   " + bowl9.getNumberOfStones());
    	System.out.println(bowl10.getClass() + "   " + bowl10.getNumberOfStones());
    	System.out.println(bowl11.getClass() + "   " + bowl11.getNumberOfStones());
    	System.out.println(bowl12.getClass() + "   " + bowl12.getNumberOfStones());
    	System.out.println(bowl13.getClass() + "   " + bowl13.getNumberOfStones());
    	System.out.println(bowl14.getClass() + "        " + bowl14.getNumberOfStones());
    	System.out.println(bowl15.getClass() + "   " + bowl15.getNumberOfStones());
    	
    	System.out.println(bowl2.gameIsOver());
    	System.out.println(bowl1.getFinalNumberOfStonesOfPlayer());
    	System.out.println("Number of starting stones: " + bowl1.getNumberOfStartingStonesPerPlayingBowl());
    	System.out.println(bowl1.getPlayer().getWinner());
    }
}
