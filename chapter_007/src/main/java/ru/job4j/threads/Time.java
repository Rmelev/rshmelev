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
        try {
            start = System.currentTimeMillis();
            CountChar countChar = new CountChar();
            Thread threadCount = new Thread(countChar);
            threadCount.start();
            while (finish - start < 5) {
                finish = System.currentTimeMillis();
                Thread.sleep(1);

            }
            countChar.setFlag(true);
            finish = System.currentTimeMillis();
            Thread.sleep(10);
            System.out.println(threadCount.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Time of program working is: " + (finish - start));
    }
}