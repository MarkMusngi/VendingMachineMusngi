import java.util.ArrayList;

/**
 * The ItemSlot class represents a slot in the vending machine that can hold
 * items.
 */
public class ItemSlot {
    private ArrayList<Item> items;

    /**
     * Constructor to create an empty ItemSlot object.
     * Initializes the items collection as an empty ArrayList with an initial
     * capacity of 10.
     */
    public ItemSlot() {
        this.items = new ArrayList<>(10);
    }

    /**
     * Constructor to create an ItemSlot object with a list of items.
     * Initializes the items collection with the provided itemList.
     *
     * @param itemList The ArrayList of Item objects to be stored in the ItemSlot.
     */
    public ItemSlot(ArrayList<Item> itemList) {
        this.items = new ArrayList<>(10);
        this.items.addAll(itemList);
    }

    /**
     * Retrieves the list of items stored in the ItemSlot.
     *
     * @return ArrayList<Item> The list of items stored in the ItemSlot.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Sets the list of items stored in the ItemSlot.
     *
     * @param items The ArrayList of Item objects to be stored in the ItemSlot.
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Adds an item to the ItemSlot.
     *
     * @param item The Item object to be added to the ItemSlot.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Dispenses an item from the ItemSlot based on the selected slot index.
     * If the ItemSlot is not empty, it removes the last item in the list.
     *
     * @param selectedItemSlot The index of the selected slot in the vending
     *                         machine.
     */
    public void dispenseItem(int selectedItemSlot) {
        if (!items.isEmpty()) {
            items.remove(items.size() - 1);
        }
    }
}