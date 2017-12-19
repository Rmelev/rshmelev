package ru.job4j.threads;

/**
 * class to show problems with multi thread.
 */
public class MultiThreadProblems implements Runnable {
    /**
     * thread.
     */
    private Thread thread;
    /**
     * counter in loop.
     */
    private static int i = 0;

    /**
     * Constructor.
     */
    MultiThreadProblems() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * overrided run().
     */
    @Override
    public void run() {
        while (i < 10) {
            i++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Прохожу цикл в " + i + "раз");
        }
    }

    /**
     * main().
     * @param args - args.
     */
    public static void main(String[] args) {
        new MultiThreadProblems();
        new MultiThreadProblems() {
            @Override
            public void run() {
                while (i < 10) {
                    i++;
                    try {
                        Thread.sleep(3743);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Прохожу цикл в " + i + "раз");
                }
            }
        };
    }
}
