package mancala.domain;

// Make your own mancala implementation using your design.
// You can take this stub as an example how to make a 
// class inside a package and how to test it.
public abstract class Bowl {

	protected int NUMBER_OF_STONES;
	protected int PLAYER_ID;
	
	public Bowl(int numbOfStones, int playerID) {
		this.NUMBER_OF_STONES = numbOfStones;
		this.PLAYER_ID = playerID;
	}
	
    public int getNumberOfStones() {
        return this.NUMBER_OF_STONES;
    }
    
    public int getPlayerID() {
    	return this.PLAYER_ID;
    }
    
}