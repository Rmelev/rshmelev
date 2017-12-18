package ru.job4j.threads;

/**
 * Read spaces and words from text.
 */
public class WordSpaceCounter implements Runnable {
    /**
     * Char array to analysis text by char.
     */
    private char[] charArr;

    /**
     * Constructor.
     * @param stringToAnalysis - string to analysis.
     */
    public WordSpaceCounter(String stringToAnalysis) {
        charArr = stringToAnalysis.toCharArray();
    }

    /**
     * run().
     */
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        int count = 0;
        for (Character nextChar : charArr) {
            if (nextChar == 32) {
                count++;
            }
        }
        System.out.println("Number of spaces: " + count);
    }
}
