package cwk4;
public class Item {
    private int itemID;
    private String itemName;
    private String itemDescription;
    private Item_Type itemType;
    private Location itemLocation;
    private Item_Stats itemStats;

    public Item(int itemID, String itemName, String itemDescription, Item_Type itemType, Location itemLocation, Item_Stats itemStats) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemType = itemType;
        this.itemLocation = itemLocation;
        this.itemStats = itemStats;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Item_Type getItemType() {
        return itemType;
    }

    public void setItemType(Item_Type itemType) {

        this.itemType = itemType;
    }

    public Location getItemLocation() {

        return itemLocation;
    }

    public void setItemLocation(Location itemLocation) {

        this.itemLocation = itemLocation;
    }

    public Item_Stats getItemStats() {

        return itemStats;
    }

    public void setItemStats(Item_Stats itemStats) {
        this.itemStats = itemStats;
    }

    @Override
    public String toString() {
        String result = "Item ID: " + itemID + "\n";
        result += "Item Name: " + itemName + "\n";
        result += "Item Description: " + itemDescription + "\n";
        result += "Item Type: " + itemType + "\n";
        result += "Item Location: " + itemLocation + "\n";
        result += "Item Stats: " + itemStats + "\n";
        return result;
    }

}
