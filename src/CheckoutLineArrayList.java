import java.util.ArrayList;

// Comparison version of CheckoutLine, backed by ArrayList instead of LinkedList.
public class CheckoutLineArrayList {

    // TODO: declare the field that stores your Customer records.
    // Decide: ArrayList<Customer> or LinkedList<Customer>?
    private ArrayList<Customer> line = new ArrayList<>();

    public void addToBack(Customer c) {
        // TODO
        line.add(c);
    }

    public void addToFront(Customer c) {
        // TODO
        // Inserting at index 0 shifts every existing element right by one.
        line.add(0, c);
    }

    public Customer removeFromFront() {
        // TODO
        // Removing index 0 shifts every remaining element left by one.
        if (line.isEmpty()) {
            return null;
        }
        return line.remove(0);
    }

    public Customer removeFromBack() {
        // TODO
        if (line.isEmpty()) {
            return null;
        }
        return line.remove(line.size() - 1);
    }

    public int size() {
        // TODO
        return line.size();
    }
}
