package uta.cse3310;


public class UserEvent {
    int GameId; // the game ID on the server
    PlayerType Players; // players in lobby
    int Button; // button number from 0 to 2499

    public int getGameId() {
        return GameId;
    }

    //getter for players
    public PlayerType getPlayers() {
        return Players;
    }

    //getter for button
    public int getButton() {
        return Button;
    }

    //setter for game ID
    public void setGameId(int gameId) {
        this.GameId = gameId;
    }

    //setter for players
    public void setPlayers(PlayerType players) {
        this.Players = players;
    }

    //setter for button
    public void setButton(int button) {
        this.Button = button;
    }
}
