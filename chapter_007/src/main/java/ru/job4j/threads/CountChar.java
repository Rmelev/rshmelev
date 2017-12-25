package ru.job4j.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Counter of chars in text.
 */
public class CountChar implements Runnable {
    /**
     * flag to interrupt calculate.
     */
    private boolean flag = false;

    /**
     * setter for flag.
     * @param flag - flag.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * overrided run().
     */
    @Override
    public void run() {
        try {
            File myFile = new File("/Users/romansmelev/projects/rshmelev/chapter_007/src/main/java/ru/job4j/threads/Oruell.txt");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);
            int countChars = 0;
            int next = reader.read();
            while (!Thread.currentThread().isInterrupted() && next != -1) {
                countChars++;
                next = reader.read();
            }
            System.out.println("Number of chars: " + countChars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
