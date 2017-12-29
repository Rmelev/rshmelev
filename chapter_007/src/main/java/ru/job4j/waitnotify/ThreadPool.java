package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayDeque;

/**
 * Thread Pool class.
 */
@ThreadSafe
public class ThreadPool {
    /**
     * number of threads whose ready to work.
     */
    private final int nThreads;
    /**
     * array of work expected workers.
     */
    private final PoolWorker[] threads;
    /**
     * queue of work with synchronize access.
     */
    @GuardedBy("itself")
    private final ArrayDeque<Work> queue;

    /**
     * Constructor.
     * @param nThreads - number of threads.
     */
    ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new ArrayDeque<>();
        threads = new PoolWorker[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    /**
     * Method for add work.
     * @param work - work.
     */
    void add(Work work) {
        synchronized (queue) {
            queue.addLast(work);
            queue.notify();
        }
    }

    /**
     * inner class of workers are waiting for work.
     */
    private class PoolWorker extends Thread {
        /**
         * Overrided run().
         */
        @Override
        public void run() {
            Work work;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " ожидает работу");
                            queue.wait();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                    work = queue.removeFirst();
                }

                try {
                    work.run();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
