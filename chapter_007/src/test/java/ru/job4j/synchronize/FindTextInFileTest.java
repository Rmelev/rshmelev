package ru.job4j.synchronize;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * search text in file system.
 */
public class FindTextInFileTest {
    /**
     * Test1.
     * @throws InterruptedException - InterruptedException.
     * @throws IOException - IOException.
     */
    @Test
    public void whenInputTextThenFindTextInFileTree() throws InterruptedException, IOException {
        List<String> ext = new ArrayList<>();
        String root = "/Users/romansmelev/projects/rshmelev/chapter_005/src/main";
        String text = "HashMap";
        ext.add(".txt");
        ext.add(".java");
        FindTextInFile searcher = new FindTextInFile(root, text, ext);

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                searcher.listOfFiles(root);
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    searcher.result(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        for (String elem : searcher.getListOfContainingFiles()) {
            System.out.println(elem);
        }
        assertThat(searcher.getListOfContainingFiles().get(1), is("/Users/romansmelev/projects/rshmelev/chapter_005/src/main/java/ru/job4j/example/Parser.java"));
    }
}