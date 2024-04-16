package uta.cse3310;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {

    private Map<PlayerType, Integer> playerScores; // Map players to their scores

    public Leaderboard() {
        this.playerScores = new HashMap<>();
    }

    // Method to add or update the score for a player
    public void addOrUpdatePlayer(PlayerType player, int score) {
        playerScores.put(player, score); // Update the map with the new score
    }

    // Sorts players by score in descending order and returns a sorted list of players
    public List<PlayerType> getSortedPlayers() {
        List<Map.Entry<PlayerType, Integer>> sortedEntries = new ArrayList<>(playerScores.entrySet());
        sortedEntries.sort(Map.Entry.<PlayerType, Integer>comparingByValue().reversed());
    
        List<PlayerType> sortedPlayers = new ArrayList<>();
        for (Map.Entry<PlayerType, Integer> entry : sortedEntries) {
            sortedPlayers.add(entry.getKey());
        }
        return sortedPlayers;
    }

    // Displays the leaderboard
    public void display() {
        List<PlayerType> sortedPlayers = getSortedPlayers();
        System.out.println("Leaderboard:");
        for (PlayerType player : sortedPlayers) {
            System.out.println(player.getNickname() + " - Score:  " + playerScores.get(player));
        }
    }

    // Resets the leaderboard for a new game
    public void reset() {
        playerScores.clear();
    }

    public Map<PlayerType, Integer> getPlayerScores() {
        return new HashMap<>(playerScores);
    }
}
