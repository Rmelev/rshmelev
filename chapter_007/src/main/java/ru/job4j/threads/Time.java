package ru.job4j.threads;
/**
 * time counter class.
 */
public class Time implements Runnable {
    /**
     * start time.
     */
    private long start;
    /**
     * finish time.
     */
    private long finish;
    /**
     * overrided run().
     */
    @Override
    public void run() {
        start = System.currentTimeMillis();
        CountChar countChar = new CountChar();
        Thread threadCount = new Thread(countChar);
        threadCount.start();
        while (finish - start < 10) {
            finish = System.currentTimeMillis();
        }
        finish = System.currentTimeMillis();
        threadCount.interrupt();
        System.out.println("Time of program working is: " + (finish - start));
    }
}