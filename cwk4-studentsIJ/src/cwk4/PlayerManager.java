package cwk4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class PlayerManager {
    private Map<Integer, Player> players;

    public PlayerManager() {
        players = new HashMap<>();
    }

    public void addPlayer(Player player) {
        players.put(player.getPlayerID(), player);
    }

    public void removePlayer(Player player) {
        players.remove(player.getPlayerID());
    }

    public Player getPlayerById(int id) {
        return players.get(id);
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players.values());
    }

    public List<Player> getPlayersByGamertag(String gamertag) {
        return players.values().stream()
                .filter(p -> p.getGamertag().equals(gamertag))
                .collect(Collectors.toList());
    }

    public int getAverageScore() {
        return (int) players.values().stream()
                .mapToInt(Player::getScore)
                .average()
                .orElse(0);
    }

    public int getTotalScore() {
        return players.values().stream()
                .mapToInt(Player::getScore)
                .sum();
    }
}
