package ru.job4j.waitnotify;

import org.junit.Test;

/**
 * test class.
 */
public class MarketTest {
    /**
     * Test1.
     */
    @Test
    public void whenPutAndGetThenGoodDealMarket() {
        Market market = new Market();
        Thread thread1 = new Thread(new Producer(market));
        Thread thread2 = new Thread(new Consumer(market));
        thread1.start();
        thread2.start();
        try {
            thread1.join(100);
            thread2.join(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}