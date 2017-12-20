package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * count class.
 */
@ThreadSafe
public class Count {
    /**
     * counter.
     */
    @GuardedBy("this")
    private int counter;

    /**
     * increment.
     * @return - incremented counter.
     */
    public synchronized int incremant() {
        return counter++;
    }
}