import java.util.ArrayList;
import java.util.LinkedList;

public class PurchaseLogLinkedList {

    // TODO: declare the field that stores your PurchaseItem records.
    // Decide: ArrayList<PurchaseItem> or LinkedList<PurchaseItem>?
    private LinkedList<PurchaseItem> items = new LinkedList<>();

    public void addItem(PurchaseItem item) {
        // TODO clear
        items.add(item);
    }

    public PurchaseItem findItemByName(String name) {
        // TODO clear
        for (PurchaseItem item : items){
            if (item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public void updatePrice(String name, double newPrice) {
        // TODO clear
        PurchaseItem item = findItemByName(name);
        if (item != null){
            item.setPrice(newPrice);
        }
    }

    public void printDailyReport() {
        // TODO: loop through every item — total count, total revenue, best seller clear
        System.out.println("Daily Report");
        System.out.println("Total items: " + itemCount());

        double totalRevenue = 0.0;
        PurchaseItem bestSeller = null;
        for (PurchaseItem item : items){
            totalRevenue += item.getPrice();
            if (bestSeller == null || item.getPrice() > bestSeller.getPrice()){
                bestSeller = item;
            }
        }
        System.out.printf("Total revenue: $%.2f%n", totalRevenue);

        if (bestSeller != null){
            System.out.printf("Best seller: %s at $%.2f%n",
                    bestSeller.getName(), bestSeller.getPrice());
        }
    }

    public int itemCount() {
        // TODO clear
        return items.size();
    }
}