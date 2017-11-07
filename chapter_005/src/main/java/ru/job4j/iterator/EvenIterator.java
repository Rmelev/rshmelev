package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for even numbers.
 */
public class EvenIterator implements Iterator {
    /**
     * input array.
     */
    private int[] arr;
    /**
     * helper index.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param arr - input array.
     */
    public EvenIterator(int[] arr) {
        this.arr = arr;
    }

    /**
     * @return - next even number and step on next element.
     */
    @Override
    public Object next() {
        Integer flag = null;
        for (int i = index; i < arr.length; i++) {
            if (arr[i] % 2 == 0 && arr[i] != 1 && arr[i] != 0) {
                flag = arr[i];
                index = ++i;
                break;
            }
        }
        if (flag == null) {
            throw new NoSuchElementException();
        }
        return flag;
    }

    /**
     * @return - true, if array has next even element.
     */
    @Override
    public boolean hasNext() {
        boolean flag = false;
        for (int i = index; i < arr.length; i++) {
            if (arr[i] % 2 == 0 && arr[i] != 1 && arr[i] != 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
