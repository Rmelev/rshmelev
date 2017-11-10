package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for two-size array.
 */
public class TwoSizeArrayIterator implements Iterator {
    /**
     * counter of array in [][]array.
     */
    private int indexOut = 0;
    /**
     * counter of elements in inner array.
     */
    private int indexInn = 0;
    /**
     * array for usage.
     */
    private int[][] arr;
    /**
     * helper variable, one element of inner array.
     */
    private int elem;

    /**
     * Constructor.
     * @param arr - input array.
     */
    public TwoSizeArrayIterator(int[][] arr) {
        this.arr = arr;
    }

    /**
     * @return - current element, and step on next element.
     */
    @Override
    public Object next() {
        if (indexOut == arr.length) {
            throw new NoSuchElementException();
        }
        for (int i = indexOut; i < arr.length;) {
            for (int j = indexInn; j < arr[i].length;) {
                elem = arr[indexOut][indexInn++];
                if (indexInn == arr[i].length) {
                    indexOut++;
                    indexInn = 0;
                }
                return elem;
            }
        }
        return elem;
    }

    /**
     * @return - true, if ArrayList has next element.
     */
    @Override
    public boolean hasNext() {
        boolean flag = false;
        if (indexOut < arr.length) {
            flag = true;
        }
        return flag;
    }
}
