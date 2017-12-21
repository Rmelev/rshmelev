package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * counter.
 */
@ThreadSafe
public class CountExample {
    /**
     * counter value.
     */
    @GuardedBy("this")
    private int value;

    /**
     * increment value.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * get value.
     * @return - value.
     */
    public synchronized int get() {
        return this.value;
    }
}
