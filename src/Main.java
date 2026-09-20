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
    }
}
