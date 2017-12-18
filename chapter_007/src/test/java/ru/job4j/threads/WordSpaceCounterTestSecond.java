package ru.job4j.threads;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Test threads.
 */
public class WordSpaceCounterTestSecond {
    /**
     * Test1.
     */
    @Test
    public void whenHaveTextThenCountWordsAndSpaces() {
        System.out.println("Program starts!");
        String string = "Hello, World and Universal for all fkj fkjlk fjklk fkkll sdjkk skdkf sdnknf skdk fnksf";
        Thread thread1 = new Thread(new WordSpaceCounter(string));
        thread1.start();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                int countWords = 1;
                char tempChar = 245;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                for (Character nextChar : string.toCharArray()) {
                    if (nextChar == 32 && tempChar != 32) {
                        countWords++;
                    }
                    tempChar = nextChar;
                }
                System.out.println("Number of words: " + countWords);
            }
        };
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of program working.");
    }
}