package mancala.api.models;

// This package is a collection of DTO's (data transfer objects).
// A DTO is a simple datastructure which models the
// data your web API sends back to the client. The Java
// objects will be converted to JSON objects.
public class Mancala {

    public Mancala(mancala.domain.PlayingBowl playingBowl,
        String namePlayer1, String namePlayer2) {
        players = new Player[2];
        players[0] = new Player(playingBowl, namePlayer1, true);
        players[1] = new Player(playingBowl, namePlayer2, false);
        gameStatus = new GameStatus(playingBowl, namePlayer1, namePlayer2);
    }

    Player[] players;
    public Player[] getPlayers() { return players; }
    
    GameStatus gameStatus;
    public GameStatus getGameStatus() { return gameStatus; }
}