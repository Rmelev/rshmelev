package ru.job4j.waitnotify;

/**
 * class for put elements in queue.
 */
public class Producer implements Runnable {
    /**
     * queue with operations put & get.
     */
    private final Market market;
    /**
     * - volume of putted element.
     */
    private int i = 0;

    /**
     * Constructor.
     * @param market - market.
     */
    Producer(Market market) {
        this.market = market;
    }

    /**
     * Overrided run().
     */
    @Override
    public void run() {
        while (true) {
            System.out.println("Положил: " + market.put(i++));
            System.out.println("Элементов в очереди: " + market.getBoundedQueue().size());
        }
    }
}
