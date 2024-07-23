package cwk4;
public class InventoryItem extends Item {
    private int quantity;

    public InventoryItem(int itemID, String itemName, String itemDescription, Item_Type itemType, Location itemLocation, Item_Stats itemStats, int quantity) {
        super(itemID, itemName, itemDescription, itemType, itemLocation, itemStats);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return super.toString() + "\nQuantity: " + quantity;
    }
}
