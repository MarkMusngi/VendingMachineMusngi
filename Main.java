import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        VendingMachine vendingMachine = new VendingMachine();
        SpecialVendingMachine specialVendingMachine = new SpecialVendingMachine();

        while (!exit) {
            displayMainMenu();
            try {
                int mainMenuChoice = scanner.nextInt();
                switch (mainMenuChoice) {
                    case 1:
                        displayCreateMenu();
                        int nCreateChoice = scanner.nextInt();
                        switch (nCreateChoice) {
                            case 1:
                                vendingMachine = createRegularVendingMachine();
                                System.out.println("Regular Vending Machine created!");
                                break;
                            case 2:
                                specialVendingMachine = createSpecialVendingMachine();
                                System.out.println("Special Vending Machine created");
                                break;
                            case 3:
                                System.out.println("Exit to Main Menu");
                                break;
                            default:
                                System.out.println("Invalid Choice");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Which machine do you want to test?");
                        displayAvailableMachine(vendingMachine, specialVendingMachine);
                        int nChoiceMaintenance = scanner.nextInt();
                        switch (nChoiceMaintenance) {
                            case 1:
                                if (vendingMachine.getInventory().isEmpty()) {
                                    System.out.println("Not available");
                                } else {
                                    runVendingMachine(vendingMachine);
                                }
                                break;
                            case 2:
                                if (specialVendingMachine.getInventory().isEmpty()) {
                                    System.out.println("Not available");
                                } else {
                                    runSpecialVendingMachine(specialVendingMachine);
                                }
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Something went wrong! Enter any key to go back to Main Menu.");
                scanner.next();
            }
        }
        scanner.close();
    }

    public static void runVendingMachine(VendingMachine test) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            displayMenu();
            int nChoice = scanner.nextInt();

            switch (nChoice) {
                case 1:
                    boolean againTest = false;
                    while (!againTest) {
                        displayTestMenu();
                        int nTestChoice = scanner.nextInt();
                        switch (nTestChoice) {
                            case 1:
                                System.out.println("Available Items");
                                test.displayItems(test.getInventory());
                                break;
                            case 2:
                                System.out.println("Enter the slot number to buy the item: ");
                                int slotNumber = scanner.nextInt();
                                Denomination paymentDenomination = new Denomination();
                                System.out.println("Enter the number of denomination you want to add: ");
                                System.out.println("1. Dollar");
                                System.out.println("2. Quarter");
                                System.out.println("3. Dime");
                                System.out.println("4. Nickel");
                                System.out.println("4. Penny");

                                boolean doneEntering = false;
                                while (!doneEntering) {
                                    int denominationChoice = scanner.nextInt();
                                    switch (denominationChoice) {
                                        case 1:
                                            System.out.print("Dollars: ");
                                            int dollars = scanner.nextInt();
                                            paymentDenomination.addDollars(dollars);
                                            break;
                                        case 2:
                                            System.out.print("Quarters: ");
                                            int quarters = scanner.nextInt();
                                            paymentDenomination.addQuarters(quarters);
                                            break;
                                        case 3:
                                            System.out.print("Dimes: ");
                                            int dimes = scanner.nextInt();
                                            paymentDenomination.addDimes(dimes);
                                            break;
                                        case 4:
                                            System.out.print("Nickels: ");
                                            int nickels = scanner.nextInt();
                                            paymentDenomination.addNickels(nickels);
                                            break;
                                        case 5:
                                            System.out.print("Pennies: ");
                                            int pennies = scanner.nextInt();
                                            paymentDenomination.addPennies(pennies);
                                            break;
                                        default:
                                            System.out.println("Invalid denomination choice.");
                                            break;
                                    }
                                    System.out.println("Do you want to input more denominations? (Y/N)");
                                    String moreInput = scanner.next();
                                    if (moreInput.equalsIgnoreCase("N")) {
                                        doneEntering = true;
                                    } else {
                                        System.out.println("Enter your payment in different denominations:");
                                    }
                                }
                                test.buyItem(slotNumber, paymentDenomination);
                                break;

                            case 3:
                                System.out.println("Enter the amount of Dollars to produce change: ");
                                double changeAmount = scanner.nextDouble();
                                test.returnChange(changeAmount);
                                break;
                            case 4:
                                againTest = true;
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;
                case 2:
                    boolean againMaintenance = false;
                    while (!againMaintenance) {
                        displayMaintenanceMenu();
                        int nMaintenanceChoice = scanner.nextInt();
                        switch (nMaintenanceChoice) {
                            case 1:
                                displayCoinsSlot();
                                int nSlot = scanner.nextInt();
                                System.out.println("Quantity");
                                int nAmount = scanner.nextInt();
                                test.inputMoney(nAmount, nSlot);
                                test.printTotalDenominationQuantity();
                                break;
                            case 2:
                                System.out.println(
                                        "Please enter any number to proceed with adding a new item, or enter -1'to exit:");
                                int nAddItem = scanner.nextInt();
                                scanner.nextLine();
                                if (nAddItem == -1) {
                                    // User wants to exit the restocking process
                                    againMaintenance = true;
                                    break;
                                } else {
                                    System.out.println("Item Name: ");
                                    String strName = scanner.nextLine();
                                    System.out.println("Price: ");
                                    double dPrice = scanner.nextDouble();
                                    System.out.println("Calories: ");
                                    int nCalories = scanner.nextInt();
                                    Item itemAdd = new Item(strName, dPrice, nCalories);
                                    test.addItem(itemAdd);
                                    break;
                                }
                            case 3:
                                test.displayItems(test.getInventory());
                                System.out.println("Which Item Slot do you want to restock? (Enter -1 to exit)");
                                int nItemSlot = scanner.nextInt();
                                if (nItemSlot == -1) {
                                    // User wants to exit the restocking process
                                    againMaintenance = true;
                                    break;
                                }
                                if (nItemSlot < 1 || nItemSlot > test.getInventory().size()) {
                                    System.out.println("Invalid item slot number. Please try again.");
                                    break;
                                }

                                ItemSlot itemSlot = test.getInventory().get(nItemSlot - 1);
                                Item item = itemSlot.getItems().get(0);

                                System.out.println("How many items do you want to restock?");
                                int nAmountItem = scanner.nextInt();
                                test.restockItem(item, nAmountItem);
                                if (!test.getRestockStartingInventory().isEmpty()) {
                                    test.updateStartingInventory();
                                    test.updateEndingInventory();
                                } else {
                                    test.updateRestockStartingInventory();
                                    test.updateRestockEndingInventory();
                                }
                                break;
                            case 4:
                                System.out.println("Which Item Slot do you want to change price?");
                                int nItemSetSlot = scanner.nextInt();
                                ItemSlot itemSetSlot = test.getInventory().get(nItemSetSlot - 1);
                                String itemType = itemSetSlot.getItems().get(0).getName();
                                System.out.println("Enter the new price for " + itemType + ": ");
                                double newPrice = scanner.nextDouble();

                                for (ItemSlot slot : test.getInventory()) {
                                    for (Item itemSet : slot.getItems()) {
                                        if (itemSet.getName().equals(itemType)) {
                                            itemSet.setPrice(newPrice);
                                        }
                                    }
                                }
                                System.out.println("Price for " + itemType + " changed to $" + newPrice + ".");
                                break;
                            case 5:
                                System.out.println("Collected a total of " + test.collectMoney());
                                break;
                            case 6:
                                test.printTotalDenominationQuantity();
                                break;
                            case 7:
                                if (test.getRestockStartingInventory().isEmpty()) {
                                    test.updateEndingInventory();
                                } else {
                                    test.updateRestockEndingInventory();
                                }

                                test.printTransactionSummary();
                                break;
                            case 8:
                                againMaintenance = true;
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void runSpecialVendingMachine(SpecialVendingMachine test) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            displayMenu();
            int nChoice = scanner.nextInt();

            switch (nChoice) {
                case 1:
                    boolean againTest = false;
                    while (!againTest) {
                        displaySpecialTestMenu();
                        int nTestChoice = scanner.nextInt();
                        switch (nTestChoice) {
                            case 1:
                                System.out.println("Available Items");
                                test.displayItems(test.getInventory());
                                break;
                            case 2:
                                test.displayItems(test.getInventory());
                                System.out.println("Enter the slot number to buy the item: ");
                                int slotNumber = scanner.nextInt();
                                System.out.println("Enter the number of denomination you want to add: ");
                                System.out.println("1. Dollar");
                                System.out.println("2. Quarter");
                                System.out.println("3. Dime");
                                System.out.println("4. Nickel");
                                System.out.println("4. Penny");

                                Denomination paymentDenomination = new Denomination();

                                boolean doneEntering = false;
                                while (!doneEntering) {
                                    int denominationChoice = scanner.nextInt();
                                    switch (denominationChoice) {
                                        case 1:
                                            System.out.print("Dollars: ");
                                            int dollars = scanner.nextInt();
                                            paymentDenomination.addDollars(dollars);
                                            break;
                                        case 2:
                                            System.out.print("Quarters: ");
                                            int quarters = scanner.nextInt();
                                            paymentDenomination.addQuarters(quarters);
                                            break;
                                        case 3:
                                            System.out.print("Dimes: ");
                                            int dimes = scanner.nextInt();
                                            paymentDenomination.addDimes(dimes);
                                            break;
                                        case 4:
                                            System.out.print("Nickels: ");
                                            int nickels = scanner.nextInt();
                                            paymentDenomination.addNickels(nickels);
                                            break;
                                        case 5:
                                            System.out.print("Pennies: ");
                                            int pennies = scanner.nextInt();
                                            paymentDenomination.addPennies(pennies);
                                            break;
                                        default:
                                            System.out.println("Invalid denomination choice.");
                                            break;
                                    }
                                    System.out.println("Do you want to input more denominations? (Y/N)");
                                    String moreInput = scanner.next();
                                    if (moreInput.equalsIgnoreCase("N")) {
                                        doneEntering = true;
                                    } else {
                                        System.out.println("Enter your payment in different denominations:");
                                    }
                                }
                                int isValidItemChecker = test.isValidItem(slotNumber);
                                if (isValidItemChecker == 0) {
                                    System.out.println(
                                            "Cannot dispense as item is add ons. Please pick a stand-alone item");
                                    break;
                                } else {
                                    test.buyItem(slotNumber, paymentDenomination);
                                    break;
                                }
                            case 3:
                                Denomination paymentDenominationOrder = new Denomination();
                                boolean doneEnteringOrder = false;
                                boolean doneEnteringRamen = false;
                                boolean noodleChecker = false;
                                boolean brothChecker = false;

                                while (!doneEnteringOrder || !doneEnteringRamen) {
                                    paymentDenominationOrder = new Denomination();
                                    System.out.println("Enter the number of denomination you want to add: ");
                                    System.out.println("1. Dollar");
                                    System.out.println("2. Quarter");
                                    System.out.println("3. Dime");
                                    System.out.println("4. Nickel");
                                    System.out.println("4. Penny");
                                    if (!doneEnteringOrder) {
                                        do {
                                            int denominationChoice = scanner.nextInt();
                                            switch (denominationChoice) {
                                                case 1:
                                                    System.out.print("Dollars: ");
                                                    int dollars = scanner.nextInt();
                                                    paymentDenominationOrder.addDollars(dollars);
                                                    break;
                                                case 2:
                                                    System.out.print("Quarters: ");
                                                    int quarters = scanner.nextInt();
                                                    paymentDenominationOrder.addQuarters(quarters);
                                                    break;
                                                case 3:
                                                    System.out.print("Dimes: ");
                                                    int dimes = scanner.nextInt();
                                                    paymentDenominationOrder.addDimes(dimes);
                                                    break;
                                                case 4:
                                                    System.out.print("Nickels: ");
                                                    int nickels = scanner.nextInt();
                                                    paymentDenominationOrder.addNickels(nickels);
                                                    break;
                                                case 5:
                                                    System.out.print("Pennies: ");
                                                    int pennies = scanner.nextInt();
                                                    paymentDenominationOrder.addPennies(pennies);
                                                    break;
                                                default:
                                                    System.out.println("Invalid denomination choice.");
                                                    break;
                                            }
                                            System.out.println("Do you want to input more denominations? (Y/N)");
                                            String moreInput = scanner.next();
                                            scanner.nextLine();
                                            if (moreInput.equals("N")) {
                                                doneEnteringOrder = true;
                                            } else {
                                                System.out.println("Inputting coins:");
                                                doneEnteringOrder = false;
                                            }
                                        } while (!doneEnteringOrder);
                                    }
                                    if (!doneEnteringRamen) {
                                        test.displayItems(test.getOwnerChosenItems());
                                        System.out.println("Enter the name of the item you want to add to the Ramen");
                                        String itemNameAdd = scanner.nextLine();
                                        int checkResult = test.checkItemExist(itemNameAdd);
                                        if (checkResult == 1) {
                                            if (itemNameAdd.contains("Broth")) {
                                                brothChecker = true;
                                            }
                                            if (itemNameAdd.contains("Noodles")) {
                                                noodleChecker = true;
                                            }
                                            if (test.hasEnoughMoney(paymentDenominationOrder,
                                                    test.checkItemPrice(itemNameAdd)) == 1) {
                                                test.buyerAddItems(itemNameAdd);
                                                test.buyItem(itemNameAdd, paymentDenominationOrder);
                                                System.out.println(itemNameAdd + " successfully added to the list");
                                                doneEnteringRamen = true;
                                            } else
                                                System.out.println("Insufficient money! Please try again.");
                                            doneEnteringRamen = true;
                                        } else {
                                            System.out.println("Item not available as add-ons");
                                        }
                                    }
                                    System.out.println("If you are done with the ramen just enter 'stop' ");
                                    String strStopper = scanner.next();
                                    if (strStopper.equals("stop")) {
                                        if ((brothChecker == true) && (noodleChecker == true)) {
                                            doneEnteringRamen = true;
                                            doneEnteringOrder = true;
                                        } else {
                                            doneEnteringRamen = false;
                                            doneEnteringOrder = false;
                                            System.out.println("No noodles or broth. Please complete the order!");
                                        }
                                    } else {
                                        doneEnteringRamen = false;
                                        doneEnteringOrder = false;
                                    }
                                }
                                test.prepareCustomizableRamen(paymentDenominationOrder);
                                test.getBuyChosenItems().clear();
                                break;
                            case 4:
                                System.out.println("Enter the amount of Dollars to produce change: ");
                                double changeAmount = scanner.nextDouble();
                                test.returnChange(changeAmount);
                                break;
                            case 5:
                                againTest = true;
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;
                case 2:
                    boolean againMaintenance = false;
                    while (!againMaintenance) {
                        displaySpecialMaintenanceMenu();
                        int nMaintenanceChoice = scanner.nextInt();
                        switch (nMaintenanceChoice) {
                            case 1:
                                displayCoinsSlot();
                                int nSlot = scanner.nextInt();
                                System.out.println("Quantity");
                                int nAmount = scanner.nextInt();
                                test.inputMoney(nAmount, nSlot);
                                test.printTotalDenominationQuantity();
                                break;
                            case 2:
                                System.out.println("Do you want to add a new item? (Enter Y/N)");
                                String strChoice = scanner.nextLine();
                                if (strChoice.equals("N")) {
                                    againMaintenance = true;
                                    break;
                                }
                                System.out.println("Item Name: ");
                                String strName = scanner.nextLine();
                                System.out.println("Price: ");
                                double dPrice = scanner.nextDouble();
                                System.out.println("Calories: ");
                                int nCalories = scanner.nextInt();
                                Item itemAdd = new Item(strName, dPrice, nCalories);
                                test.addItem(itemAdd);
                                break;
                            case 3:
                                test.displayItems(test.getInventory());
                                System.out.println("Which Item Slot do you want to restock? (Enter -1 to exit)");
                                int nItemSlot = scanner.nextInt();
                                if (nItemSlot == -1) {
                                    // User wants to exit the restocking process
                                    againMaintenance = true;
                                    break;
                                }
                                if (nItemSlot < 1 || nItemSlot > test.getInventory().size()) {
                                    System.out.println("Invalid item slot number. Please try again.");
                                    break;
                                }

                                ItemSlot itemSlot = test.getInventory().get(nItemSlot - 1);
                                Item item = itemSlot.getItems().get(0);

                                System.out.println("How many items do you want to restock?");
                                int nAmountItem = scanner.nextInt();
                                test.restockItem(item, nAmountItem);
                                if (!test.getRestockStartingInventory().isEmpty()) {
                                    test.updateStartingInventory();
                                    test.updateEndingInventory();
                                } else {
                                    test.updateRestockStartingInventory();
                                    test.updateRestockEndingInventory();
                                }
                                break;
                            case 4:
                                System.out.println(
                                        "Which item do you want to put on add ons? (Enter the name of the Item) ");
                                test.displayItems(test.getInventory());
                                scanner.nextLine();
                                String itemName = scanner.nextLine();
                                int isDuplicate = test.isDuplicate(itemName);
                                if (isDuplicate == 0) {
                                    test.ownerAddItems(itemName);
                                } else
                                    System.out.println("Duplicate item, try again!");
                                break;
                            case 5:
                                System.out.println("Which Item Slot do you want to change price?");
                                int nItemSetSlot = scanner.nextInt();
                                ItemSlot itemSetSlot = test.getInventory().get(nItemSetSlot - 1);
                                String itemType = itemSetSlot.getItems().get(0).getName();
                                System.out.println("Enter the new price for " + itemType + ": ");
                                double newPrice = scanner.nextDouble();

                                for (ItemSlot slot : test.getInventory()) {
                                    for (Item itemSet : slot.getItems()) {
                                        if (itemSet.getName().equals(itemType)) {
                                            itemSet.setPrice(newPrice);
                                        }
                                    }
                                }

                                for (ItemSlot slot : test.getOwnerChosenItems()) {
                                    for (Item itemSet : slot.getItems()) {
                                        if (itemSet.getName().equals(itemType)) {
                                            itemSet.setPrice(newPrice);
                                        }
                                    }
                                }

                                System.out.println("Price for " + itemType + " changed to $" + newPrice + ".");
                                break;
                            case 6:
                                System.out.println("Collected a total of " + test.collectMoney());
                                break;
                            case 7:
                                test.printTotalDenominationQuantity();
                                break;
                            case 8:
                                if (test.getRestockStartingInventory().isEmpty()) {
                                    test.updateEndingInventory();
                                } else {
                                    test.updateRestockEndingInventory();
                                }

                                test.printTransactionSummary();
                                break;
                            case 9:
                                againMaintenance = true;
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static VendingMachine createRegularVendingMachine() {
        // Regular items (non-ramen items)
        Item coke = new Item("Coke", 0.63, 140);
        Item tamago = new Item("Tamago", 0.78, 80);
        Item gyoza = new Item("Gyoza", 1.25, 180); // Japanese dumplings
        Item onigiri = new Item("Onigiri", 1.0, 250);

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addItem(coke);
        vendingMachine.restockItem(coke, 10);
        vendingMachine.addItem(tamago);
        vendingMachine.restockItem(tamago, 8);
        vendingMachine.addItem(gyoza);
        vendingMachine.restockItem(gyoza, 15);
        vendingMachine.addItem(onigiri);
        vendingMachine.restockItem(onigiri, 12);

        vendingMachine.updateStartingInventory();
        vendingMachine.updateEndingInventory();

        return vendingMachine;
    }

    public static SpecialVendingMachine createSpecialVendingMachine() {
        // Regular items (non-ramen items)
        Item coke = new Item("Coke", 0.63, 140);
        Item tamago = new Item("Tamago", 0.78, 80);
        Item gyoza = new Item("Gyoza", 1.25, 180); // Japanese dumplings
        Item onigiri = new Item("Onigiri", 1.0, 250); // Rice ball with filling

        // Ramen ingredients
        Item noodles = new Item("Noodles", 1.0, 350); // Assumed 350 calories for a serving of noodles
        Item egg = new Item("Egg", 0.5, 70); // Boiled egg
        Item chashuPork = new Item("Chashu Pork", 1.2, 200); // Sliced and seasoned pork
        Item friedTofu = new Item("Fried Tofu", 0.8, 120); // Abura-age, fried tofu pouch
        Item negi = new Item("Negi", 0.3, 10); // Green onions, scallions
        Item tonkotsuBroth = new Item("Tonkotsu Broth", 1.5, 100); // Pork bone broth
        Item misoBroth = new Item("Miso Broth", 1.2, 80); // Soybean paste broth
        Item shioBroth = new Item("Shio Broth", 1.1, 70); // Salt-based clear broth
        Item menma = new Item("Menma", 0.6, 40); // Bamboo shoots

        SpecialVendingMachine specialVendingMachine = new SpecialVendingMachine();
        specialVendingMachine.inputMoney(100, 1);
        specialVendingMachine.inputMoney(10, 2);
        specialVendingMachine.inputMoney(4, 3);
        specialVendingMachine.inputMoney(2, 4);
        specialVendingMachine.addItem(coke);
        specialVendingMachine.restockItem(coke, 10);
        specialVendingMachine.addItem(tamago);
        specialVendingMachine.restockItem(tamago, 8);
        specialVendingMachine.addItem(gyoza);
        specialVendingMachine.restockItem(gyoza, 15);
        specialVendingMachine.addItem(onigiri);
        specialVendingMachine.restockItem(onigiri, 12);

        specialVendingMachine.addItem(noodles);
        specialVendingMachine.restockItem(noodles, 20);
        specialVendingMachine.addItem(egg);
        specialVendingMachine.restockItem(egg, 30);
        specialVendingMachine.addItem(chashuPork);
        specialVendingMachine.restockItem(chashuPork, 25);
        specialVendingMachine.addItem(friedTofu);
        specialVendingMachine.restockItem(friedTofu, 20);
        specialVendingMachine.addItem(negi);
        specialVendingMachine.restockItem(negi, 15);
        specialVendingMachine.addItem(tonkotsuBroth);
        specialVendingMachine.restockItem(tonkotsuBroth, 10);
        specialVendingMachine.addItem(misoBroth);
        specialVendingMachine.restockItem(misoBroth, 10);
        specialVendingMachine.addItem(shioBroth);
        specialVendingMachine.restockItem(shioBroth, 10);
        specialVendingMachine.addItem(menma);
        specialVendingMachine.restockItem(menma, 15);

        specialVendingMachine.initializeRamenItems("Noodles");
        specialVendingMachine.initializeRamenItems("Tonkotsu Broth");
        specialVendingMachine.initializeRamenItems("Shio Broth");
        specialVendingMachine.initializeRamenItems("Miso Broth");

        specialVendingMachine.updateStartingInventory();
        specialVendingMachine.updateEndingInventory();

        return specialVendingMachine;
    }

    public static void displayMenu() {
        System.out.println("1. Test the Vending Machine");
        System.out.println("2. Maintenance the Vending Machine");
        System.out.println("3. Exit");
    }

    public static void displayTestMenu() {
        System.out.println("1. Display Items");
        System.out.println("2. Buy Items");
        System.out.println("3. Produce change");
        System.out.println("4. Exit to display Menu");
    }

    public static void displaySpecialTestMenu() {
        System.out.println("1. Display Items");
        System.out.println("2. Buy Items");
        System.out.println("3. Order Ramen");
        System.out.println("4. Produce change");
        System.out.println("5. Exit to display Menu");
    }

    public static void displayMaintenanceMenu() {
        System.out.println("1. Input Money into the Machine");
        System.out.println("2. Add a new item");
        System.out.println("3. Restock Items");
        System.out.println("4. Set Item Price");
        System.out.println("5. Collect money earned");
        System.out.println("6. View amount of coins");
        System.out.println("7. Print Transaction Summary");
        System.out.println("8. Exit to display Menu");
    }

    public static void displaySpecialMaintenanceMenu() {
        System.out.println("1. Input Money into the Machine");
        System.out.println("2. Add a new item");
        System.out.println("3. Restock Items");
        System.out.println("4. Edit add-ons for Ramen");
        System.out.println("5. Set Item Price");
        System.out.println("6. Collect money earned");
        System.out.println("7. View amount of coins");
        System.out.println("8. Print Transaction Summary");
        System.out.println("9. Exit to display Menu");
    }

    public static void displayCoinsSlot() {
        System.out.println("Insert Coins");
        System.out.println("1 : Pennies");
        System.out.println("2 : Nickels");
        System.out.println("3 : Dimes");
        System.out.println("4 : Quarter");
        System.out.println("5 : Dollars");
    }

    public static void displayMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Create a Vending Machine");
        System.out.println("2. Test a Vending Machine");
        System.out.println("3. Exit");
    }

    public static void displayCreateMenu() {
        System.out.println("Create Menu");
        System.out.println("1. Regular Vending Machine");
        System.out.println("2. Special Vending Machine");
        System.out.println("3. Exit");
    }

    public static void displayAvailableMachine(VendingMachine vendingMachine,
            SpecialVendingMachine specialVendingMachine) {
        System.out.println("Available Machines:");
        if (vendingMachine != null && !vendingMachine.getInventory().isEmpty()) {
            System.out.println("1: Regular Vending Machine");
        }
        if (specialVendingMachine != null && !specialVendingMachine.getInventory().isEmpty()) {
            System.out.println("2: Special Vending Machine");
        }
    }
}
