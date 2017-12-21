package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test methods of user's list.
 */
public class SimpleListTest {
    /**
     * thread for SimpleList.
     */
    private class SimpleListThread extends Thread {
        /**
         * pole.
         */
        private final SimpleList<Integer> simpleList;

        /**
         * Constructor.
         * @param simpleList - simpleList.
         */
        SimpleListThread(SimpleList<Integer> simpleList) {
            this.simpleList = simpleList;
            for (int i = 1; i < 7; i = i + 1) {
                simpleList.add(i);
            }
        }

        /**
         * run().
         */
        @Override
        public void run() {

            for (int i = 1; i < 7; i++) {
                System.out.println(simpleList.get(i));
            }
        }
    }
    /**
     * test1. For Integer.
     */
    @Test
    public void whenIntegerTestThenCorrectResultForSimpleList() {
        SimpleList<Integer> simpleList = new SimpleList<>();
        Iterator<Integer> iter = simpleList.iterator();
        simpleList.add(3);
        simpleList.add(7);
        simpleList.add(11);
        simpleList.add(45);
        simpleList.add(89);
        for (Integer temp : simpleList) {
            System.out.print(temp + " ");
        }
        assertThat(simpleList.get(1), is(7));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.next(), is(7));
        assertThat(iter.next(), is(11));
        assertThat(iter.next(), is(45));
        assertThat(iter.next(), is(89));
        assertThat(iter.hasNext(), is(false));
    }

    /**
     * test2. For String.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenStringTestThenCorrectResultForSimpleList() {
        SimpleList<String> simpleList = new SimpleList<>();
        Iterator<String> iter = simpleList.iterator();
        simpleList.add("Bangkok");
        simpleList.add("Singapore");
        simpleList.add("Jakarta");
        simpleList.add("Kuala-Lumpur");
        for (String temp : simpleList) {
            System.out.print(temp + " ");
        }
        assertThat(simpleList.get(1), is("Singapore"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Bangkok"));
        assertThat(iter.next(), is("Singapore"));
        assertThat(iter.next(), is("Jakarta"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Kuala-Lumpur"));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    /**
     * Test. Threads.
     * @throws InterruptedException - exception.
     */
    @Test
    public void whenThreeTreadsThenCorrectResult() throws InterruptedException {
        final SimpleList<Integer> simpleList = new SimpleList<>();
        Thread first = new SimpleListThread(simpleList);
        Thread second = new SimpleListThread(simpleList);
        first.start();
        second.start();
        first.join();
        second.join();
    }
}