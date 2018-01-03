package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Myself implementation of lock algorithm.
 */
@ThreadSafe
public class MyLock {
    /**
     * lock flag; true, if this object is locked.
     */
    @GuardedBy("this")
    private boolean isLocked = false;

    /**
     * lock changes in locked object.
     */
    synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isLocked = true;
        }
    }

    /**
     * free locked object.
     */
    synchronized void unlock() {
        isLocked = false;
        notifyAll();
    }
}
