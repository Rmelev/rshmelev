package ru.job4j.waitnotify;

/**
 * Work.
 */
public class Work implements Runnable {
    /**
     * number of work.
     */
    private int i;

    /**
     * Constructor.
     * @param i - number of work.
     */
    Work(int i) {
        this.i = i;
    }

    /**
     * Overrided run().
     */
    @Override
    public void run() {
        System.out.println(i + "-ую работу выполняет: " + Thread.currentThread().getName());
    }
}
