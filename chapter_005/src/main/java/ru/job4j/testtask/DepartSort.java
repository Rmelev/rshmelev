package ru.job4j.testtask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Increase and decrease sort of departments.
 */
public class DepartSort {
    /**
     * list of have read from file items in String[] representation.
     */
    private List<String[]> list = new ArrayList<>();
    /**
     * set of all existing items.
     */
    private Set<String> items;

    /**
     * read data from file.
     * @return - set of have read items.
     */
    List<String[]> reader() {
        try {
            File myFile = new File("/Users/romansmelev/projects/rshmelev/chapter_005/src/main/java/ru/job4j/testtask/DepStructure.txt");
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            String line;
            String[] item;
            while ((line = reader.readLine()) != null) {
                item = line.split("/");
                list.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * increase representation.
     */
    void increaseView() {
        list = reader();
        items = new TreeSet<>();
        display(fillIfAbsent(items));
    }

    /**
     * decrease representation.
     */
    void decreaseView() {
        list = reader();
        items = new TreeSet<>((x, y) -> {
            char[] value = x.toCharArray();
            char[] anotherString = y.toCharArray();
            int len1 = value.length;
            int len2 = anotherString.length;
            int lim = Math.min(len1, len2);

            int k = 0;
            while (k < lim) {
                char c1 = value[k];
                char c2 = anotherString[k];
                if (c1 != c2) {
                    return c2 - c1;
                }
                k++;
            }
            return len1 - len2;
        });
        display(fillIfAbsent(items));
    }

    /**
     * add existing but absent in file items.
     * @param items - set of have read items.
     * @return - set of all existing items.
     */
    Set<String> fillIfAbsent(Set<String> items) {
        for (String[] tempArr : list) {
            String sum = "";
            for (String tempItem : tempArr) {
                sum = sum + tempItem + "/";
                items.add(sum);
            }
        }
        return items;
    }

    /**
     * display result.
     * @param items - set of all existing items.
     */
    void display(Set<String> items) {
        for (String tempItem : items) {
            System.out.println(tempItem);
        }
    }

    /**
     * main().
     * @param args - args.
     */
    public static void main(String[] args) {
        DepartSort dep = new DepartSort();
        dep.increaseView();
        System.out.println();
        dep.decreaseView();
    }
}
