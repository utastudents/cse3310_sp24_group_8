package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private static final int MAX_PLAYERS = 4;
    private List<PlayerType> players;

    public Lobby() {
        this.players = new ArrayList<>();
    }

    // Method to add a player to the lobby
    public boolean addPlayer(PlayerType player) {
        return players.add(player);
    }

    // Method to remove a player from the lobby
    public void removePlayer(PlayerType player) {
        players.remove(player);
    }

    // Method to check if the lobby is full
    public boolean isFull() {
        return players.size() >= MAX_PLAYERS; // Maximum of 4 players
    }

    // Method to get the list of players in the lobby
    public List<PlayerType> getPlayers() {
        return players;
    }

    // Method to reset the lobby
    public void reset() {
        players.clear();
    }

    // Method to check if all players in the lobby are ready
    public boolean areAllPlayersReady() {
        for (PlayerType player : players) {
            if (player.getStatus() != PlayerType.Status.Playing) {
                return false;
            }
        }
        return true;
    }

   
}
