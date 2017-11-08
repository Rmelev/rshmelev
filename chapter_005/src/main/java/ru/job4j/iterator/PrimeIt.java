package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for primes.
 */
public class PrimeIt implements Iterator {
    /**
     * array of numbers.
     */
    private final int[] arr;

    /**
     * counter of position in array.
     */
    private int count = 0;

    /**
     * Constructor.
     * @param arr - array.
     */
    public PrimeIt(int[] arr) {
        this.arr = arr;
    }

    /**
     * @return - next prime and step on next element.
     */
    @Override
    public Object next() {
        Integer simple = null;
        for (int i = count; i < arr.length; i++) {
            if (arr[i] > 1) {
                if (isThisSimple(arr[i])) {
                    count = i + 1;
                    simple = arr[i];
                    break;
                }
            }
        }
        if (simple == null) {
            throw new NoSuchElementException();
        }
        return simple;
    }

    /**
     * @return - true, if array has next prime element.
     */
    @Override
    public boolean hasNext() {
        boolean flag = false;
        for (int i = count; i < arr.length; i++) {
            if (arr[i] > 1) {
                if (isThisSimple(arr[i])) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * helper code for check this number: is it prime?.
     * @param elem - number from array to check.
     * @return - true, if number is prime.
     */
    boolean isThisSimple(int elem) {
        boolean iAmSimple = true;
        for (int j = 2; j < elem; j++) {
            if (elem % j == 0 && j != elem) {
                iAmSimple = false;
            }
        }
        return iAmSimple;
    }
}
