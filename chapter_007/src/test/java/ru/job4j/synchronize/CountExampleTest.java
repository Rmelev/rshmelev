package ru.job4j.synchronize;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 */
public class CountExampleTest {
    /**
     * class for multithreading calculate.
     */
    private class ThreadCount extends Thread {
        /**
         * count.
         */
        private final CountExample count;

        /**
         * Constructor.
         * @param count - count.
         */
        private ThreadCount(final CountExample count) {
            this.count = count;
        }

        /**
         * run().
         */
        @Override
        public void run() {
            this.count.increment();
        }
    }

    /**
     * Test1.
     * @throws InterruptedException - exception.
     */
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final CountExample count = new CountExample();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));

    }
}