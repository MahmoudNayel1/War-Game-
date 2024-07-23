package cwk4;

public class HealthItem extends Item {
    private int healthBoost;

    public HealthItem(int itemID, String itemName, String itemDescription, Item_Type itemType, Location itemLocation, Item_Stats itemStats, int healthBoost) {
        super(itemID, itemName, itemDescription, itemType, itemLocation, itemStats);
        this.healthBoost = healthBoost;
    }

    public int getHealthBoost() {
        return healthBoost;
    }

    public void setHealthBoost(int healthBoost) {
        this.healthBoost = healthBoost;
    }
}
