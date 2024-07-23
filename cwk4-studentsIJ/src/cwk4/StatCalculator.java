package cwk4;
public class StatCalculator {
    // Other methods and variables

    public int calculateTotalHealth(Player player) {
        int baseHealth = player.getPlayerStats().getMaxHealth();
        int itemHealth = 0;
        for (Item item : player.getInventory()) {
            if (item instanceof HealthItem) {
                HealthItem healthItem = (HealthItem) item;
                itemHealth += healthItem.getHealthBoost();
            }
        }
        // Add any other factors that may affect the player's health, such as buffs or debuffs.
        int totalHealth = baseHealth + itemHealth;
        return totalHealth;
    }
}

