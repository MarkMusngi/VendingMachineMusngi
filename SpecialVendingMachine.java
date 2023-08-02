import java.util.ArrayList;

/**
 * The SpecialVendingMachine class represents a specialized vending machine that
 * extends the functionality of the base VendingMachine class.
 * It allows buyers to prepare a customizeable ramen.
 * It allows owners to choose specific items that can be validated for the
 * ramen.
 * 
 */
public class SpecialVendingMachine extends VendingMachine {
    private ArrayList<ItemSlot> buyerChosenItems = new ArrayList<ItemSlot>();
    private ArrayList<ItemSlot> ownerChosenItems = new ArrayList<ItemSlot>();

    /**
     * Adds the specified item to the collection of owner-chosen items.
     *
     * @param itemName The name of the item to be added to the owner-chosen items.
     */
    public void ownerAddItems(String itemName) {
        for (ItemSlot itemSlot : inventory) {
            ArrayList<Item> items = itemSlot.getItems();
            if (!items.isEmpty() && items.get(0).getName().equals(itemName)) {
                ownerChosenItems.add(itemSlot);
                System.out.println("Item slot " + (inventory.indexOf(itemSlot) + 1) + " added to the Add-ons");
            }
        }
    }

    /**
     * Initializes the collection of owner-chosen items with the specified item name
     * related to ramen items.
     *
     * @param itemName The name of the ramen item to be added to the owner-chosen
     *                 items.
     */
    public void initializeRamenItems(String itemName) {
        for (ItemSlot itemSlot : inventory) {
            ArrayList<Item> items = itemSlot.getItems();
            if (!items.isEmpty() && items.get(0).getName().equals(itemName)) {
                ownerChosenItems.add(itemSlot);
            }
        }
    }

    /**
     * Adds the item slots containing the specified item name check
     * if related to ramen items to the buyer's chosen items collection.
     *
     * @param itemName The name of the ramen item to be added to the buyer's chosen
     *                 items collection.
     */
    public void buyerAddItems(String itemName) {
        for (ItemSlot itemSlot : inventory) {
            ArrayList<Item> items = itemSlot.getItems();
            if (!items.isEmpty() && items.get(0).getName().equals(itemName)) {
                buyerChosenItems.add(itemSlot);
                System.out.println("Item slot " + (inventory.indexOf(itemSlot) + 1) + " added to the Ramen");
            }
        }
    }

    /**
     * Checks if an item with the given name exists in the owner's chosen items
     * collection.
     *
     * @param itemName The name of the item to check for existence.
     * @return int Returns 1 if the item exists in the owner's chosen items
     *         collection, otherwise returns 0.
     */
    public int checkItemExist(String itemName) {
        for (ItemSlot itemSlot : ownerChosenItems) {
            ArrayList<Item> items = itemSlot.getItems();
            if (items.get(0).getName().equals(itemName)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Checks if an item with the given name exists as a duplicate in the owner's
     * chosen items collection.
     *
     * @param itemName The name of the item to check for duplicates.
     * @return int Returns 1 if a duplicate item exists in the owner's chosen items
     *         collection, otherwise returns 0.
     */
    public int isDuplicate(String itemName) {
        for (ItemSlot itemSlot : ownerChosenItems) {
            ArrayList<Item> items = itemSlot.getItems();
            if (items.get(0).getName().equals(itemName)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Checks if the item selected in the given slot number is a valid item for
     * purchase.
     *
     * @param nSlot The slot number of the selected item.
     * @return int Returns 1 if the selected item is valid for purchase, otherwise
     *         returns 0.
     */
    public int isValidItem(int nSlot) {
        int nSelectedItemSlot = nSlot - 1;
        if (nSelectedItemSlot >= 0 && nSelectedItemSlot < inventory.size()) {
            ItemSlot itemSlot = inventory.get(nSelectedItemSlot);
            if (!itemSlot.getItems().isEmpty()) {
                Item selectedItem = itemSlot.getItems().get(0);
                if (selectedItem.getName().contains("Garlic")
                        || selectedItem.getName().contains("Onion") || selectedItem.getName().contains("Noodles")
                        || selectedItem.getName().contains("Broth")) {
                    return 0;
                } else
                    return 1;
            }
        }
        return 0;
    }

    /**
     * Prepares a customizable ramen dish based on the buyer's chosen items from the
     * buyerChosenItems collection.
     *
     * @param paymentDenomination The denomination used for payment
     * 
     * @return Ramen Returns a Ramen object representing the prepared customizable
     *         ramen with the chosen items and total calories.
     */
    public Ramen prepareCustomizableRamen(Denomination paymentDenomination) {

        Ramen ramen = new Ramen();
        ArrayList<Item> chosenItems = new ArrayList<>();

        for (ItemSlot itemSlot : buyerChosenItems) {
            ArrayList<Item> items = itemSlot.getItems();
            if (!items.isEmpty()) {
                chosenItems.add(items.get(0));
            }
        }

        ramen.setChosenItems(chosenItems);
        int totalCalories = 0;

        for (Item item : chosenItems) {
            totalCalories += item.getCalories();
        }
        ramen.setTotalCalories(totalCalories);

        System.out.println("Blanching noodles...");
        System.out.println("Heating broth...");
        System.out.println("Putting noodles in cup...");

        ArrayList<Item> toppingsList = new ArrayList<>();

        for (Item item : chosenItems) {
            if (item.getName().equals("Noodles") || item.getName().contains("Broth")) {
                // Do nothing with "Noodles" and "Broth"
            } else {
                toppingsList.add(item);
            }
        }

        if (!toppingsList.isEmpty()) {
            System.out.print("Topping with ");
            for (int i = 0; i < toppingsList.size(); i++) {
                System.out.print(toppingsList.get(i).getName());
                if (i < toppingsList.size() - 1) {
                    System.out.print(" and ");
                }
            }
            System.out.println("...");
        }
        System.out.println("Pouring broth...");
        System.out.println("Ramen Done!");
        System.out.println("Total Calories: " + totalCalories);

        return ramen;
    }

    /**
     * Retrieves the collection of chosen items for the owner.
     *
     * @return ArrayList<ItemSlot> Returns the collection of chosen items for the
     *         owner.
     */
    public ArrayList<ItemSlot> getOwnerChosenItems() {
        return ownerChosenItems;
    }

    /**
     * Retrieves the collection of chosen items for the buyer.
     *
     * @return ArrayList<ItemSlot> Returns the collection of chosen items for the
     *         buyer.
     */
    public ArrayList<ItemSlot> getBuyChosenItems() {
        return buyerChosenItems;
    }

    /**
     * Buys an item from the vending machine.
     *
     * @param itemName            The name of the item to be purchased.
     * @param paymentDenomination The denomination used for payment.
     */
    public void buyItem(String itemName, Denomination paymentDenomination) {
        ItemSlot itemSlot = null;
        for (ItemSlot slot : inventory) {
            if (!slot.getItems().isEmpty() && slot.getItems().get(0).getName().equals(itemName)) {
                itemSlot = slot;
                break;
            }
        }

        if (itemSlot != null) {
            Item selectedItem = itemSlot.getItems().get(0);
            double itemPrice = selectedItem.getPrice();

            double totalPaid = 0.0;
            totalPaid += paymentDenomination.getDollars() * 1.00;
            totalPaid += paymentDenomination.getQuarters() * 0.25;
            totalPaid += paymentDenomination.getDimes() * 0.10;
            totalPaid += paymentDenomination.getNickels() * 0.05;
            totalPaid += paymentDenomination.getPennies() * 0.01;

            if (totalPaid >= itemPrice) {
                double change = totalPaid - itemPrice;
                if (checkChange(change)) {
                    int nSelectedItemSlot = inventory.indexOf(itemSlot);
                    itemSlot.dispenseItem(nSelectedItemSlot);
                    if (itemSlot.getItems().isEmpty()) {
                        inventory.remove(nSelectedItemSlot);
                    }

                    dMoney += itemPrice;

                    coinDenominations.addDollars(paymentDenomination.getDollars());
                    coinDenominations.addQuarters(paymentDenomination.getQuarters());
                    coinDenominations.addDimes(paymentDenomination.getDimes());
                    coinDenominations.addNickels(paymentDenomination.getNickels());
                    coinDenominations.addPennies(paymentDenomination.getPennies());

                    if (restockStartingInventory.isEmpty()) {
                        updateSalesAndRevenue(nSelectedItemSlot, 1, itemPrice, totalSales);
                    } else {
                        updateSalesAndRevenue(nSelectedItemSlot, 1, itemPrice, totalSalesRestock);
                    }

                    returnChange(change);
                    System.out.println("Item purchased successfully!");
                } else {
                    System.out.println("Vending Machine does not have enough change!");
                }
            } else {
                System.out.println("You do not have enough money!");
            }
        } else {
            System.out.println("Item not found in the inventory.");
        }
    }

    /**
     * Checks if the provided payment denomination contains enough money to meet the
     * target amount.
     *
     * @param paymentDenomination The denomination used for payment.
     * @param targetAmount        The target amount.
     * 
     * @return int Returns 1 if the payment denomination contains enough money to
     *         meet the target amount, 0 otherwise.
     */
    public int hasEnoughMoney(Denomination paymentDenomination, double targetAmount) {
        double totalAmount = paymentDenomination.getPennies() * 0.01 +
                paymentDenomination.getNickels() * 0.05 +
                paymentDenomination.getDimes() * 0.10 +
                paymentDenomination.getQuarters() * 0.25 +
                paymentDenomination.getDollars();

        if (totalAmount >= targetAmount) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Checks the price of an item with the specified name in the owner's chosen
     * items collection.
     *
     * @param itemNameAdd The name of the item to check the price for.
     * @return double Returns the price of the item if found, or -1 if the item does
     *         not exist in the collection.
     */
    public double checkItemPrice(String itemNameAdd) {

        for (ItemSlot itemSlot : ownerChosenItems) {
            ArrayList<Item> items = itemSlot.getItems();
            if (!items.isEmpty() && items.get(0).getName().equalsIgnoreCase(itemNameAdd)) {
                return items.get(0).getPrice();
            }
        }
        return -1;
    }
}
