package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if ((array[j].equals(array[i])) && (!array[j].equals("del"))) {
                    array[j] = "del";
                    counter++;
                }
            }
        }
        for (int i = 0; i < array.length - counter; i++) {
            for (int j = 1; j < array.length - 1 - i; j++) {
                if (array[j].equals("del")) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return Arrays.copyOf(array, array.length - counter);
    }
}