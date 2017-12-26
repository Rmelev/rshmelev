package ru.job4j.waitnotify;

import java.util.ArrayDeque;

/**
 * Queue for put & get.
 */
public class Market {
    /**
     * Max size of queue.
     */
    private static final int CATCH = 12;
    /**
     * queue.
     */
    private ArrayDeque<Integer> boundedQueue = new ArrayDeque<>(CATCH);

    /**
     * getter.
     * @return - boundedQueue.
     */
    public ArrayDeque<Integer> getBoundedQueue() {
        return this.boundedQueue;
    }

    /**
     * put element in queue.
     * @param i - volume of putted element.
     * @return - volume of putted element.
     */
    synchronized int put(int i) {
        while (boundedQueue.size() > CATCH - 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boundedQueue.add(i);
        notify();
        return i;
    }

    /**
     * get element from queue.
     * @return - volume of first element in queue.
     */
    synchronized int get() {
        while (boundedQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notify();
        return boundedQueue.pollFirst();
    }
}
