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
		this.setNumberOfStartingStones();
	}
	
	public Bowl(Player player) {
		this.player = player;
		this.setNeighbour();
		this.setNumberOfStartingStones();
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
    
    public static void main(String[] args) {
    	PlayingBowl bowl1 = new PlayingBowl();
//    	bowl1.playBowl();
    	
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
    	
    	((PlayingBowl) bowl1).playBowl();
    	
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
    }

}