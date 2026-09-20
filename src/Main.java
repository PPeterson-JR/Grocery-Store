import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // ---- Sample data to load into the Purchase Log ----
        PurchaseItem[] sampleItems = {
                new PurchaseItem("Bread", 3.49),
                new PurchaseItem("Milk", 2.99),
                new PurchaseItem("Eggs", 4.29),
                new PurchaseItem("Coffee", 8.99),
                new PurchaseItem("Bananas", 1.29),
                new PurchaseItem("Cereal", 4.79),
                new PurchaseItem("Chicken Breast", 9.99),
                new PurchaseItem("Paper Towels", 6.49)
        };

        PurchaseLog log = new PurchaseLog();

        // TODO: write a loop that adds every item in sampleItems to log using addItem(). clear
        for (PurchaseItem item : sampleItems) {
            log.addItem(item);
        }

        System.out.println("Purchase Log loaded with " + log.itemCount() + " items.");

        // Example test call — findItemByName. Do the same for every other required method.
        PurchaseItem found = log.findItemByName("Coffee");
        System.out.println("Looked up 'Coffee', found: " +
                (found != null ? found.getName() + " $" + found.getPrice() : "NOT FOUND"));

        // TODO: test updatePrice() — update a price, then look it up again and print the new value. clear
        log.updatePrice("Coffee", 7.49);
        PurchaseItem updated = log.findItemByName("Coffee");
        System.out.println("Coffee is now $" + (updated != null ? updated.getPrice() : "NOT FOUND"));

        // TODO: test printDailyReport() — call it and confirm the totals look correct against sampleItems. clear
        log.printDailyReport();


        // ---- Sample data to load into the Checkout Line ----
        Customer[] sampleCustomers = {
                new Customer("Alvarez", 12),
                new Customer("Chen", 3),
                new Customer("Patel", 27),
                new Customer("O'Brien", 1)
        };

        CheckoutLine line = new CheckoutLine();

        // TODO: write a loop that adds every customer in sampleCustomers to line using addToBack(). clear
        for (Customer c : sampleCustomers) {
            line.addToBack(c);
        }

        System.out.println("Checkout Line loaded, size = " + line.size());

        // Example test call — addToFront (O'Brien has 1 item, gets waved to the front).
        Customer express = new Customer("Nguyen", 1);
        System.out.println("Waving " + express.getName() + " to the front...");
        line.addToFront(express);

        // TODO: test removeFromFront() — remove and print who gets served first. Should it be Nguyen? clear
        Customer servedFirst = line.removeFromFront();
        System.out.println("Now serving: " +
                (servedFirst != null ? servedFirst.getName() : "line is empty"));

        // TODO: test removeFromBack() — remove and print who leaves from the back of the line. clear
        Customer leftLine = line.removeFromBack();
        System.out.println((leftLine != null ? leftLine.getName() : "Nobody") + " left from the back.");

        // TODO: after your test calls above, print line.size() again and confirm it changed correctly. clear
        System.out.println("Line size is now " + line.size());


        // ---- Interactive register: scan items in, look them up, reprint the report ----
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("1) Scan a new item");
            System.out.println("2) Look up an item");
            System.out.println("3) Print daily report");
            System.out.println("4) Quit");
            System.out.print("Choose: ");

            // Stop cleanly if input runs out instead of crashing.
            if (!input.hasNextLine()) {
                break;
            }

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    scanNewItem(input, log);
                    break;
                case "2":
                    lookUpItem(input, log);
                    break;
                case "3":
                    log.printDailyReport();
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Please pick 1, 2, 3, or 4.");
            }
        }

        input.close();
    }

    // Reads a name and a price from the keyboard and adds the item to the log.
    private static void scanNewItem(Scanner input, PurchaseLog log) {
        System.out.print("Item name: ");
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("No name entered. Nothing was added.");
            return;
        }

        System.out.print("Price: $");
        String priceText = input.nextLine().trim();

        // Read the price as text and convert it, so a typo cannot crash the
        // program or leave a stray newline sitting in the scanner.
        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            System.out.println("'" + priceText + "' is not a number. Nothing was added.");
            return;
        }

        if (price < 0) {
            System.out.println("A price cannot be negative. Nothing was added.");
            return;
        }

        log.addItem(new PurchaseItem(name, price));
        System.out.printf("Added %s at $%.2f. The log now holds %d items.%n",
                name, price, log.itemCount());
    }

    // Reads a name from the keyboard and reports whether it is in the log.
    private static void lookUpItem(Scanner input, PurchaseLog log) {
        System.out.print("Item name: ");
        String query = input.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("No name entered.");
            return;
        }

        PurchaseItem result = log.findItemByName(query);
        if (result != null) {
            System.out.printf("Found %s at $%.2f%n", result.getName(), result.getPrice());
        } else {
            System.out.println("No item named '" + query + "' is in the log.");
        }
    }
}
