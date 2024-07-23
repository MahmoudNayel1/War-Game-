package cwk4;

import java.util.ArrayList;
import cwk4.Location_Type;


public class Player {
    private int playerID;
    private String gamertag;
    private String password;
    private String email;
    private int score;
    private ArrayList<InventoryItem> inventory;
    private Location currentLocation;
    private Player_Stats playerStats;
    private int level;

    {
        inventory = new ArrayList<InventoryItem>();
    }

    public Player() {
        Location_Type locationType = new Location_Type("Space");
        currentLocation = new Location(1, "first", "Description", locationType, 0);
        playerStats = new Player_Stats(100, 100, 100, 100);
    }

    public int getPlayerID() {

        return playerID;
    }

    public void setPlayerID(int playerID) {

        this.playerID = playerID;
    }

    public String getGamertag() {

        return gamertag;
    }

    public void setGamertag(String gamertag) {

        this.gamertag = gamertag;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public int getScore() {

        return score;
    }

    public int getLevel() {
        return level;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Player_Stats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(Player_Stats playerStats) {
        this.playerStats = playerStats;
    }

    @Override
    public String toString() {
        StringBuilder inventoryStr = new StringBuilder();
        for (InventoryItem item : inventory) {
            inventoryStr.append("- ").append(item.getItemName()).append("\n");
        }
        return "Player Info:\n" +
                "Player ID: " + playerID + "\n" +
                "Gamertag: " + gamertag + "\n" +
                "Email: " + email + "\n" +
                "Score: " + score + "\n" +
                "Level: " + level + "\n" +
                "Inventory:\n" + inventoryStr +
                "Current Location: " + currentLocation.getLocationName() + "\n" +
                "Player Stats:\n" +
                playerStats.toString();
    }
}
