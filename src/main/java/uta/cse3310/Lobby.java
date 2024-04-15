package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private List<PlayerType> players;

    public Lobby() {
        this.players = new ArrayList<>();
    }

    // Method to add a player to the lobby
    public void addPlayer(PlayerType player) {
        players.add(player);
    }

    // Method to remove a player from the lobby
    public void removePlayer(PlayerType player) {
        players.remove(player);
    }

    // Method to check if the lobby is full
    public boolean isFull() {
        return players.size() >= 4; // Maximum of 4 players
    }

    // Method to get the list of players in the lobby
    public List<PlayerType> getPlayers() {
        return players;
    }

    // Method to reset the lobby
    public void reset() {
        players.clear();
    }
}
