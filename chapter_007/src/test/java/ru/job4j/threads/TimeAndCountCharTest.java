package ru.job4j.threads;

import org.junit.Test;

/**
 * Test of limit time to calculate.
 */
public class TimeAndCountCharTest {
    /**
     * we waiting for 35ms and interrupt thread (in class Time).
     * if we set 30ms limit, chars will not calculated.
     */
    @Test
    public void whenTimeIsLimitedThenWeHaveNotResult() {
        Thread threadTime = new Thread(new Time());
        threadTime.start();
        try {
            threadTime.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}