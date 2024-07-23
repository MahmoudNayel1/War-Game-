package cwk4;
import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<InventoryItem> inventoryItems;

    public InventoryManager() {
        inventoryItems = new ArrayList<InventoryItem>();
    }

    public void addInventoryItem(InventoryItem item) {
        inventoryItems.add(item);
    }

    public void removeInventoryItem(InventoryItem item) {
        inventoryItems.remove(item);
    }

    public void displayInventory() {
        for (InventoryItem item : inventoryItems) {
            System.out.println(item);
        }
    }
}
