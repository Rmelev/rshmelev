package ru.job4j.waitnotify;

import org.junit.Test;
/**
 * Thread Poll test.
 */
public class ThreadPoolTest {
    /**
     * Test1.
     */
    @Test
    public void whenHaveWorkThenWorkElseExpect() {
        ThreadPool threadPool = new ThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 30; i++) {
            threadPool.add(new Work(i));
        }
    }
}