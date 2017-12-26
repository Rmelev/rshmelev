package ru.job4j.waitnotify;
/**
 * class for get elements in queue.
 */
public class Consumer implements Runnable {
    /**
     * queue with operations put & get.
     */
    private final Market market;
    /**
     * Constructor.
     * @param market - market.
     */
    Consumer(Market market) {
        this.market = market;
    }
    /**
     * Overrided run().
     */
    @Override
    public void run() {
        while (true) {
            System.out.println("Получил: " + market.get());
        }
    }
}
