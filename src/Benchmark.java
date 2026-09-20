// Compares the ArrayList and LinkedList versions of each component
// on the workload described in the assignment.
public class Benchmark {

    // The log is populated with far more than the 8 sample items so the
    // lookup loop does enough work to be measurable above timer noise.
    private static final int LOG_SIZE = 10000;
    private static final int LOOKUPS = 1000;
    private static final int FRONT_ADDS = 1000;

    // Supplementary scale. 1,000 front-insertions finish in well under a
    // millisecond, which is too fast to measure reliably. Repeating the same
    // workload at a larger size shows the trend the small run cannot.
    private static final int BIG_FRONT_ADDS = 50000;

    public static void main(String[] args) {

        // ================= PURCHASE LOG =================
        PurchaseLog arrayLog = new PurchaseLog();
        PurchaseLogLinkedList linkedLog = new PurchaseLogLinkedList();

        for (int i = 0; i < LOG_SIZE; i++) {
            arrayLog.addItem(new PurchaseItem("Item" + i, i * 0.01));
            linkedLog.addItem(new PurchaseItem("Item" + i, i * 0.01));
        }

        // Search for the LAST item so every lookup has to scan the whole
        // structure. Searching for an early item would exit immediately and
        // measure nothing.
        String target = "Item" + (LOG_SIZE - 1);

        // Warm-up pass. The JVM interprets code before compiling it to native,
        // so the first run of any loop is much slower. Discard these results.
        timeArrayLogLookups(arrayLog, target);
        timeLinkedLogLookups(linkedLog, target);

        long arrayLogNs = timeArrayLogLookups(arrayLog, target);
        long linkedLogNs = timeLinkedLogLookups(linkedLog, target);

        // Both versions compare the same number of names, so the operation
        // counter is identical and only the clock separates them.
        long logComparisons = (long) LOOKUPS * LOG_SIZE;

        // ================= CHECKOUT LINE =================
        // Warm-up pass, same reason as above.
        timeArrayLineFrontAdds(FRONT_ADDS);
        timeLinkedLineFrontAdds(FRONT_ADDS);

        long arrayLineNs = timeArrayLineFrontAdds(FRONT_ADDS);
        long linkedLineNs = timeLinkedLineFrontAdds(FRONT_ADDS);

        // Same workload, larger size. No extra warm-up needed; the methods
        // were already compiled to native code by the runs above.
        long arrayBigNs = timeArrayLineFrontAdds(BIG_FRONT_ADDS);
        long linkedBigNs = timeLinkedLineFrontAdds(BIG_FRONT_ADDS);

        // ================= RESULTS =================
        System.out.println("Purchase Log: " + LOOKUPS + " findItemByName lookups over "
                + LOG_SIZE + " items");
        printRow("Version", "Time (ms)", "Comparisons");
        printRow("ArrayList", ms(arrayLogNs), logComparisons);
        printRow("LinkedList", ms(linkedLogNs), logComparisons);

        System.out.println();
        System.out.println("Checkout Line: " + FRONT_ADDS + " addToFront calls");
        printRow("Version", "Time (ms)", "Shifts");
        printRow("ArrayList", ms(arrayLineNs), shiftsFor(FRONT_ADDS));
        printRow("LinkedList", ms(linkedLineNs), 0);

        System.out.println();
        System.out.println("Checkout Line: " + BIG_FRONT_ADDS + " addToFront calls (scale-up)");
        printRow("Version", "Time (ms)", "Shifts");
        printRow("ArrayList", ms(arrayBigNs), shiftsFor(BIG_FRONT_ADDS));
        printRow("LinkedList", ms(linkedBigNs), 0);
    }

    // Manual shift counter. Inserting at the front of an array list moves every
    // element already present, so insert number i costs i moves. A linked list
    // repoints references and moves nothing, so its count is always zero.
    private static long shiftsFor(int inserts) {
        long shifts = 0;
        for (int i = 0; i < inserts; i++) {
            shifts += i;
        }
        return shifts;
    }

    private static String ms(long nanos) {
        return String.format("%.3f", nanos / 1_000_000.0);
    }

    private static void printRow(String version, String time, long count) {
        System.out.printf("  %-12s %12s %16d%n", version, time, count);
    }

    private static void printRow(String version, String time, String count) {
        System.out.printf("  %-12s %12s %16s%n", version, time, count);
    }

    private static long timeArrayLogLookups(PurchaseLog log, String target) {
        long start = System.nanoTime();
        for (int i = 0; i < LOOKUPS; i++) {
            log.findItemByName(target);
        }
        return System.nanoTime() - start;
    }

    private static long timeLinkedLogLookups(PurchaseLogLinkedList log, String target) {
        long start = System.nanoTime();
        for (int i = 0; i < LOOKUPS; i++) {
            log.findItemByName(target);
        }
        return System.nanoTime() - start;
    }

    private static long timeArrayLineFrontAdds(int inserts) {
        CheckoutLineArrayList line = new CheckoutLineArrayList();
        Customer c = new Customer("Shopper", 1);
        long start = System.nanoTime();
        for (int i = 0; i < inserts; i++) {
            line.addToFront(c);
        }
        return System.nanoTime() - start;
    }

    private static long timeLinkedLineFrontAdds(int inserts) {
        CheckoutLine line = new CheckoutLine();
        Customer c = new Customer("Shopper", 1);
        long start = System.nanoTime();
        for (int i = 0; i < inserts; i++) {
            line.addToFront(c);
        }
        return System.nanoTime() - start;
    }
}
