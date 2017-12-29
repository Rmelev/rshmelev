package ru.job4j.waitnotify;

/**
 * Myself implementation of lock algorithm.
 */
public class MyLock {
    /**
     * lock flag; true, if this object is locked.
     */
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
