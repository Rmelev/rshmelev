package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for two-size array.
 */
public class TwoSizeArrayIterator implements Iterator {
    /**
     * for linear representation of two-size array.
     */
    private ArrayList<Integer> arrList = new ArrayList<>();
    /**
     * counter of elements.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param arr - input two-size array.
     */
    public TwoSizeArrayIterator(final int[][] arr) {
        this.arrList = initializer(arr);
    }
/*
    private int[] initializer(int[][] arr) { // with transformation in one-size array.
        int i = 0;
        int sum = 0;
        int count = 0;
        while (i < arr.length) {
            sum += arr[i].length;
            i++;
        }
        int[] arrOneSize = new int[sum];
        for (int[] tempArr : arr) {
            for (int temp : tempArr) {
                 arrOneSize[count++] = temp;
            }
        }
        return arrOneSize;
    } */

    /**
     * with transformation from two-size array to ArrayList.
     * @param arr - two-size array.
     * @return - ArrayList.
     */
    private ArrayList<Integer> initializer(int[][] arr) { // with transformation in ArrayList.
        for (int[] tempArr : arr) {
            for (Integer temp : tempArr) {
                arrList.add(temp);
            }
        }
        return arrList;
    }

    /**
     * @return - current element, and step on next element.
     */
    @Override
    public Object next() {
        if (index < arrList.size()) {
            return arrList.get(index++);
        } else {
            throw new NoSuchElementException();
        }
        //if (index < arr.length) {
        //    return arr[index++];
        //} else {
        //    throw new NoSuchElementException();
        //}
    }

    /**
     * @return - true, if ArrayList has next element.
     */
    @Override
    public boolean hasNext() {
        return arrList.size() > index;
        //return arr.length > index;
    }
}
