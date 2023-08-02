import java.util.ArrayList;

/**
 * The VendingMachine class represents a basic vending machine that contains an
 * inventory of ItemSlots and tracks sales, revenue, and available change.
 * It allows buyers to purchase items, owners to restock the machine, and
 * provides methods for managing transactions and inventory.
 * 
 */
public class VendingMachine {
    protected ArrayList<ItemSlot> inventory;
    protected double dMoney;
    protected Denomination coinDenominations;
    protected ArrayList<ItemSlot> startingInventory;
    protected ArrayList<ItemSlot> endingInventory;
    protected ArrayList<ItemSlot> restockStartingInventory;
    protected ArrayList<ItemSlot> restockEndingInventory;
    protected ArrayList<ItemSlot> soldItemsListRestock;
    protected ArrayList<ItemSlot> soldItemsList;
    protected double totalSales;
    protected double totalSalesRestock;

    /**
     * Represents a machine's inventory.
     */
    public VendingMachine() {
        inventory = new ArrayList<ItemSlot>();
        dMoney = 0;
        coinDenominations = new Denomination();
        startingInventory = new ArrayList<ItemSlot>();
        endingInventory = new ArrayList<ItemSlot>();
        soldItemsList = new ArrayList<ItemSlot>();
        soldItemsListRestock = new ArrayList<ItemSlot>();
        restockStartingInventory = new ArrayList<ItemSlot>();
        restockEndingInventory = new ArrayList<ItemSlot>();
        totalSales = 0;
        totalSalesRestock = 0;

    }

    /**
     * Get the vending machine's inventory.
     *
     * @return ArrayList<ItemSlot> The list of ItemSlots in the inventory.
     */
    public ArrayList<ItemSlot> getInventory() {
        return this.inventory;
    }

    /**
     * Get the amount of money the vending machine has.
     *
     * @return double The amount of money.
     */
    public double getdMoney() {
        return this.dMoney;
    }

    /**
     * Get the coin denominations available in the machine.
     *
     * @return Denomination The coin denominations.
     */
    public Denomination getDenomination() {
        return this.coinDenominations;
    }

    /**
     * Sets the total sales amount for the vending machine.
     *
     * @param dAmount The new total sales amount to be set.
     */
    public void setTotalSales(double dAmount) {
        this.totalSales = dAmount;
    }

    /**
     * Sets the total sales restock amount for the vending machine.
     *
     * @param dAmount The new total sales amount to be set.
     */
    public void setTotalSalesRestock(double dAmount) {
        this.totalSalesRestock = dAmount;
    }

    /**
     * Returns the ArrayList of sold items during regular operations.
     *
     * @return ArrayList<ItemSlot> The list of sold items during regular operations.
     */
    public ArrayList<ItemSlot> getSoldItemsList() {
        return this.soldItemsList;
    }

    /**
     * Returns the ArrayList of sold items during restocking operations.
     *
     * @return ArrayList<ItemSlot> The list of sold items during restocking
     *         operations.
     */

    public ArrayList<ItemSlot> getSoldItemsListRestock() {
        return this.soldItemsListRestock;
    }

    /**
     * Returns the ArrayList representing the starting inventory during restocking
     * operations.
     *
     * @return ArrayList<ItemSlot> The starting inventory during restocking
     *         operations.
     */
    public ArrayList<ItemSlot> getRestockStartingInventory() {
        return this.restockStartingInventory;
    }

    /**
     * Checks if the vending machine has enough change to give back to the buyer.
     * 
     * @param dChange The amount of change to be given back to the buyer (in
     *                dollars).
     * @return boolean Returns true if the vending machine has enough change,
     *         otherwise false.
     */
    public boolean checkChange(double dChange) {
        if (dChange >= 0) {
            // Calculate the total change available in the machine (in cents) based on
            // denominations
            int totalChangeAvailable = coinDenominations.getDollars() * 100
                    + coinDenominations.getQuarters() * 25
                    + coinDenominations.getDimes() * 10
                    + coinDenominations.getNickels() * 5
                    + coinDenominations.getPennies();

            // Convert the change to cents (to match the totalChangeAvailable)
            int changeInCents = (int) (dChange * 100);

            // Check if the machine has enough change to give back to the buyer
            if (changeInCents <= totalChangeAvailable) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns change to the buyer after a successful purchase from the vending
     * machine.
     * The change is given in various coin denominations
     *
     * @param dChange The amount of change to be given back to the buyer (in
     *                dollars).
     */
    public void returnChange(double dChange) {
        int changeInCents = (int) (dChange * 100);

        int dollarsToGiveBack = 0;
        if (coinDenominations.getDollars() > 0 && changeInCents >= 100) {
            dollarsToGiveBack = changeInCents / 100;
            if (dollarsToGiveBack > coinDenominations.getDollars()) {
                dollarsToGiveBack = coinDenominations.getDollars();
            }
            changeInCents -= dollarsToGiveBack * 100;
        }

        int quartersToGiveBack = Math.min(coinDenominations.getQuarters(), changeInCents / 25);
        changeInCents -= quartersToGiveBack * 25;

        int dimesToGiveBack = Math.min(coinDenominations.getDimes(), changeInCents / 10);
        changeInCents -= dimesToGiveBack * 10;

        int nickelsToGiveBack = Math.min(coinDenominations.getNickels(), changeInCents / 5);
        changeInCents -= nickelsToGiveBack * 5;

        int penniesToGiveBack = 0;
        if (coinDenominations.getPennies() > 0 && changeInCents >= 1) {
            penniesToGiveBack = changeInCents;
            if (penniesToGiveBack > coinDenominations.getPennies()) {
                penniesToGiveBack = coinDenominations.getPennies();
            }
            changeInCents -= penniesToGiveBack * 1;
        }

        if (changeInCents > 0) {
            System.out.println("Vending Machine does not have enough change!");
            return;
        }

        System.out.println("Change returned:");
        if (dollarsToGiveBack > 0) {
            System.out.println("Dollars: " + dollarsToGiveBack);
            coinDenominations.removeDollars(dollarsToGiveBack);
        }
        if (quartersToGiveBack > 0) {
            System.out.println("Quarters: " + quartersToGiveBack);
            coinDenominations.removeQuarters(quartersToGiveBack);
        }
        if (dimesToGiveBack > 0) {
            System.out.println("Dimes: " + dimesToGiveBack);
            coinDenominations.removeDimes(dimesToGiveBack);
        }
        if (nickelsToGiveBack > 0) {
            System.out.println("Nickels: " + nickelsToGiveBack);
            coinDenominations.removeNickels(nickelsToGiveBack);
        }
        if (penniesToGiveBack > 0) {
            System.out.println("Pennies: " + penniesToGiveBack);
            coinDenominations.removePennies(penniesToGiveBack);
        }
        if (dollarsToGiveBack == 0 && quartersToGiveBack == 0 && dimesToGiveBack == 0 && nickelsToGiveBack == 0
                && penniesToGiveBack == 0) {
            System.out.println("No change!");
        }
    }

    /**
     * Buys an item from the specified slot in the vending machine and handles the
     * payment.
     * 
     * @param nSlot               The slot number of the item to be bought.
     * @param paymentDenomination The denomination of coins and bills used for
     *                            payment.
     */
    public void buyItem(int nSlot, Denomination paymentDenomination) {
        int nSelectedItemSlot = nSlot - 1;
        if (nSelectedItemSlot >= 0 && nSelectedItemSlot < inventory.size()) {
            ItemSlot itemSlot = inventory.get(nSelectedItemSlot);
            if (!itemSlot.getItems().isEmpty()) {
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
                System.out.println("Selected slot is empty.");
            }
        } else {
            System.out.println("Invalid slot numberS.");
        }
    }

    /**
     * Collects the total amount of money present in the vending machine and clears
     * the coin denominations.
     * After calculating the total, it removes all the coins from the machine's
     * inventory.
     *
     * @return double The total amount of money collected from the vending machine.
     */
    public double collectMoney() {
        int dollars = coinDenominations.getDollars();
        int quarters = coinDenominations.getQuarters();
        int dimes = coinDenominations.getDimes();
        int nickels = coinDenominations.getNickels();
        int pennies = coinDenominations.getPennies();
        double totalPaid = 0.0;

        totalPaid += coinDenominations.getDollars() * 1.00;
        totalPaid += coinDenominations.getQuarters() * 0.25;
        totalPaid += coinDenominations.getDimes() * 0.10;
        totalPaid += coinDenominations.getNickels() * 0.05;
        totalPaid += coinDenominations.getPennies() * 0.01;

        coinDenominations.removeDollars(dollars);
        coinDenominations.removeQuarters(quarters);
        coinDenominations.removeDimes(dimes);
        coinDenominations.removeNickels(nickels);
        coinDenominations.removePennies(pennies);

        return totalPaid;
    }

    /**
     * Inputs money into the vending machine and updates the total money in the
     * machine's inventory.
     *
     * @param nAmount The number of coins or bills being inserted.
     * @param nSlot   The slot number representing the denomination of the inserted
     *                money.
     *                1: Pennies, 2: Nickels, 3: Dimes, 4: Quarters, 5: Dollars.
     */
    public void inputMoney(int nAmount, int nSlot) {
        switch (nSlot) {
            case 1:
                coinDenominations.addPennies(nAmount);
                dMoney += 0.01 * nAmount;
                break;
            case 2:
                coinDenominations.addNickels(nAmount);
                dMoney += 0.05 * nAmount;
                break;
            case 3:
                coinDenominations.addDimes(nAmount);
                dMoney += 0.10 * nAmount;
                break;
            case 4:
                coinDenominations.addQuarters(nAmount);
                dMoney += 0.25 * nAmount;
                break;
            case 5:
                coinDenominations.addDollars(nAmount);
                dMoney += 1.00 * nAmount;
                break;
        }

    }

    /**
     * Adds an item to the vending machine's inventory.
     * If a slot for the item exists, the new item is added to the existing slot.
     * If no slot for the item exists, a new slot is created and the item is added
     * to it.
     *
     * @param newItem The item to be added to the vending machine's inventory.
     */
    public void addItem(Item newItem) {
        boolean itemAdded = false;
        for (ItemSlot itemSlot : inventory) {
            if (!itemSlot.getItems().isEmpty() && itemSlot.getItems().get(0).getName().equals(newItem.getName())) {
                itemSlot.addItem(newItem);
                itemAdded = true;
            }
        }
        if (!itemAdded) {
            ItemSlot newItemSlot = new ItemSlot();
            newItemSlot.addItem(newItem);
            inventory.add(newItemSlot);
        }
    }

    /**
     * Restocks the vending machine by adding multiple quantities of the same item
     * to its inventory.
     * 
     * 
     * @param newItem   The item to be restocked in the vending machine.
     * @param nQuantity The number of items to be restocked.
     */
    public void restockItem(Item newItem, int nQuantity) {
        for (int i = 0; i < nQuantity; i++) {
            addItem(newItem);
        }
    }

    /**
     * Prints the total quantity of each coin denomination present in the vending
     * machine.
     */
    public void printTotalDenominationQuantity() {
        System.out.println("Total Denomination Quantity in Vending Machine:");
        System.out.println("Dollars: " + coinDenominations.getDollars());
        System.out.println("Quarters: " + coinDenominations.getQuarters());
        System.out.println("Dimes: " + coinDenominations.getDimes());
        System.out.println("Nickels: " + coinDenominations.getNickels());
        System.out.println("Pennies: " + coinDenominations.getPennies());
    }

    /**
     * Displays the items available in the vending machine's inventory.
     * It iterates through each item slot and displays the items available
     * along with their details such as name, quantity, price, and calories
     * if an item isn't empty.
     *
     * @param inventory The ArrayList of ItemSlots representing the vending
     *                  machine's inventory.
     */
    public void displayItems(ArrayList<ItemSlot> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            ItemSlot itemSlot = inventory.get(i);
            ArrayList<Item> items = itemSlot.getItems();

            if (!items.isEmpty()) {
                Item item = items.get(0);
                int itemCount = items.size();

                System.out
                        .println("Slot " + (i + 1) + ": " + item.getName() + " x" + itemCount + " - $" + item.getPrice()
                                + " Calories: " + item.getCalories());
            }
        }
    }

    /**
     * Prints a transaction summary of the vending machine's activities, including
     * starting inventory, sold items, ending inventory, and restocking information.
     * 
     */
    public void printTransactionSummary() {
        System.out.println("Transaction Summary:");
        System.out.println("--------------------");

        System.out.println("Starting Inventory");
        displayItems(startingInventory);

        System.out.println("\nItems Sold:");
        if (soldItemsList.isEmpty()) {
            System.out.println("No items were sold.");
        } else {
            displayItems(soldItemsList);
            System.out.println("\nTotal Sales: $" + totalSales);
        }

        System.out.println("\nEnding Inventory");
        if (endingInventory.isEmpty()) {
            System.out.println("No restock has been done");
        } else {
            displayItems(endingInventory);
        }

        if (restockStartingInventory.isEmpty() && restockEndingInventory.isEmpty()) {
            System.out.println("\nNo restocking has been done");
        } else {
            System.out.println("\nRestocked Starting Inventory");
            displayItems(restockStartingInventory);

            if (soldItemsListRestock.isEmpty()) {
                System.out.println("No items were sold.");
            } else {
                System.out.println("\nItems Sold:");
                System.out.println("\nTotal Sales: $" + totalSalesRestock);
                displayItems(soldItemsListRestock);
            }
            System.out.println("\nRestocked Ending Inventory");
            displayItems(restockEndingInventory);
        }

    }

    /**
     * Prints the starting inventory of the vending machine.
     * 
     */
    public void printStartingInventory() {
        System.out.println("Starting Inventory");
        displayItems(startingInventory);
    }

    /**
     * Prints the ending inventory of the vending machine.
     * 
     */
    public void printEndingInventory() {
        System.out.println("Ending Inventory");
        if (endingInventory.isEmpty()) {
            System.out.println("No restock has been done");
        } else
            displayItems(endingInventory);
    }

    /**
     * Updates the starting inventory of the vending machine based on the current
     * inventory.
     * 
     */
    public void updateStartingInventory() {
        startingInventory.clear(); // Clear the existing starting inventory

        for (ItemSlot slot : inventory) {
            ArrayList<Item> items = slot.getItems();
            ArrayList<Item> copiedItems = new ArrayList<>();
            for (Item item : items) {
                copiedItems.add(new Item(item.getName(), item.getPrice(), item.getCalories()));
            }

            ItemSlot copiedSlot = new ItemSlot(copiedItems);
            startingInventory.add(copiedSlot);
        }
    }

    /**
     * Updates the ending inventory of the vending machine based on the current
     * inventory.
     * 
     */
    public void updateEndingInventory() {
        endingInventory.clear();

        for (ItemSlot slot : inventory) {
            ArrayList<Item> items = slot.getItems();
            ArrayList<Item> copiedItems = new ArrayList<>();
            for (Item item : items) {
                copiedItems.add(new Item(item.getName(), item.getPrice(), item.getCalories()));
            }

            ItemSlot copiedSlot = new ItemSlot(copiedItems);
            endingInventory.add(copiedSlot);
        }
    }

    /**
     * Updates the restock starting inventory of the vending machine based on the
     * current inventory.
     * 
     */
    public void updateRestockStartingInventory() {
        restockStartingInventory.clear();

        for (ItemSlot slot : inventory) {
            ArrayList<Item> items = slot.getItems();
            ArrayList<Item> copiedItems = new ArrayList<>();
            for (Item item : items) {
                copiedItems.add(new Item(item.getName(), item.getPrice(), item.getCalories()));
            }

            ItemSlot copiedSlot = new ItemSlot(copiedItems);
            restockStartingInventory.add(copiedSlot);
        }
    }

    /**
     * Updates the restock ending inventory of the vending machine based on the
     * current inventory.
     * 
     */
    public void updateRestockEndingInventory() {
        restockEndingInventory.clear();

        for (ItemSlot slot : inventory) {
            ArrayList<Item> items = slot.getItems();
            ArrayList<Item> copiedItems = new ArrayList<>();
            for (Item item : items) {
                copiedItems.add(new Item(item.getName(), item.getPrice(), item.getCalories()));
            }

            ItemSlot copiedSlot = new ItemSlot(copiedItems);
            restockEndingInventory.add(copiedSlot);
        }
    }

    /**
     * Calculates the total count of a specific item in the vending machine's
     * inventory.
     *
     * @param iItem The item for which the count needs to be calculated.
     * @return int The total count of the specified item in the vending machine's
     *         inventory.
     */
    public int calculateItemCount(Item iItem) {
        int itemCount = 0;

        for (ItemSlot slot : inventory) {
            for (Item slotItem : slot.getItems()) {
                if (slotItem.equals(iItem)) {
                    itemCount++;
                }
            }
        }

        return itemCount;
    }

    /**
     * Updates the sales and revenue information based on a transaction for the
     * specified item.
     *
     * @param slotIndex      The index of the slot where the item was sold from.
     * @param quantitySold   The quantity of items sold in the transaction.
     * @param itemPrice      The price of each item sold.
     * @param totalSalesTemp The temporary total sales before updating the final
     *                       total sales.
     */
    public void updateSalesAndRevenue(int slotIndex, int quantitySold, double itemPrice, double totalSalesTemp) {
        totalSalesTemp += itemPrice * quantitySold;

        if (restockStartingInventory.isEmpty()) {
            totalSales = totalSalesTemp;
        } else {
            totalSalesRestock = totalSalesTemp;
        }

        if (restockStartingInventory.isEmpty()) {
            if (slotIndex >= 0 && slotIndex < inventory.size()) {
                ItemSlot itemSlot = inventory.get(slotIndex);
                ArrayList<Item> items = itemSlot.getItems();

                if (!items.isEmpty()) {
                    Item item = items.get(0);
                    String itemName = item.getName();

                    boolean found = false;
                    for (ItemSlot soldItemSlot : soldItemsList) {
                        if (!soldItemSlot.getItems().isEmpty()
                                && soldItemSlot.getItems().get(0).getName().equals(itemName)) {
                            soldItemSlot.addItem(item);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        ItemSlot newItemSlot = new ItemSlot();
                        newItemSlot.addItem(item);
                        soldItemsList.add(newItemSlot);
                    }
                }
            }
        }

        if (!restockStartingInventory.isEmpty()) {
            if (slotIndex >= 0 && slotIndex < inventory.size()) {
                ItemSlot itemSlot = inventory.get(slotIndex);
                ArrayList<Item> items = itemSlot.getItems();

                if (!items.isEmpty()) {
                    Item item = items.get(0);
                    String itemName = item.getName();

                    boolean found = false;
                    for (ItemSlot soldItemSlot : soldItemsListRestock) {
                        if (!soldItemSlot.getItems().isEmpty()
                                && soldItemSlot.getItems().get(0).getName().equals(itemName)) {
                            soldItemSlot.addItem(item);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        ItemSlot newItemSlot = new ItemSlot();
                        newItemSlot.addItem(item);
                        soldItemsListRestock.add(newItemSlot);
                    }
                }
            }
        }
    }
}