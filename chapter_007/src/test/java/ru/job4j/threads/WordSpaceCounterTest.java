package ru.job4j.threads;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Test threads.
 */
public class WordSpaceCounterTest {
    /**
     * Test1.
     */
    @Test
    public void whenHaveTextThenCountWordsAndSpaces() {
        String string = "Hello, World and Universal for all";
        new Thread(new WordSpaceCounter(string)).start();
        Thread thread = new Thread() {
            @Override
            public void run() {
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }*/
                int countWords = 1;
                char tempChar = 245;
                for (Character nextChar : string.toCharArray()) {
                    if (nextChar == 32 && tempChar != 32) {
                        countWords++;
                    }
                    tempChar = nextChar;
                }
                System.out.println("Number of words: " + countWords);
            }
        };
        thread.start();
        /*try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Test2.
     */
    @Test
    public void whenReadFromFileThenCorrectResult() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    File myFile = new File("/Users/romansmelev/projects/rshmelev/chapter_007/src/main/java/ru/job4j/threads/Oruell.txt");
                    FileReader fileReader = new FileReader(myFile);
                    BufferedReader reader = new BufferedReader(fileReader);
                    int next = reader.read();
                    int count = 0;
                    new Thread() {
                        private int tempChar = 245;
                        private int countWords = 1;
                        @Override
                        public void run() {
                            try {
                                File myFile = new File("/Users/romansmelev/projects/rshmelev/chapter_007/src/main/java/ru/job4j/threads/Oruell.txt");
                                FileReader fileReader = new FileReader(myFile);
                                BufferedReader reader = new BufferedReader(fileReader);
                                int next = reader.read();
                                while (next != -1) {
                                    if (next == 32 && tempChar != 32) {
                                        countWords++;
                                    }
                                    tempChar = next;
                                    next = reader.read();
                                }
                                System.out.println("Number of words: " + countWords);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    while (next != -1) {
                        if (next == 32) {
                            count++;
                        }
                        next = reader.read();
                    }
                    //Thread.sleep(50);
                    System.out.println("Number of spaces: " + count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            //Thread.sleep(1000);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}