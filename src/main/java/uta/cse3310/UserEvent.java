package uta.cse3310;
// User events are sent from the webpage to the server

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

}
