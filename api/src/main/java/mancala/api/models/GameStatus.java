package mancala.api.models;

public class GameStatus {
    boolean endOfGame;
    public boolean getEndOfGame() { return endOfGame; }
    
    String winner;
    public String getWinner() { return winner; }

    public GameStatus(mancala.domain.PlayingBowl firstBowl, 
            String namePlayer1, String namePlayer2) {

        this.endOfGame = firstBowl.getPlayer().gameIsEnded();
        mancala.domain.Player winner = firstBowl.getWinner();

        if (winner == null && !this.endOfGame) {
            this.winner = null;
        } else {
            if (winner.getName().equals(firstBowl.getPlayer().getName())) {
                this.winner = firstBowl.getPlayer().getName();
            } else if (winner.getName().equals(firstBowl.getPlayer().getOpponent().getName())) {
                this.winner = firstBowl.getPlayer().getOpponent().getName();
            } else {
                this.winner = null;
            }
        }
    }
}