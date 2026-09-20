import java.util.LinkedList;

public class CheckoutLine {

    // TODO: declare the field that stores your Customer records.
    // Decide: ArrayList<Customer> or LinkedList<Customer>?
    private LinkedList<Customer> line = new LinkedList<>();

    public void addToBack(Customer c) {
        // TODO
        line.addLast(c);
    }

    public void addToFront(Customer c) {
        // TODO
        line.addFirst(c);
    }

    public Customer removeFromFront() {
        // TODO
        return line.pollFirst();
    }

    public Customer removeFromBack() {
        // TODO
        return line.pollLast();
    }

    public int size() {
        // TODO
        return line.size();
    }
}