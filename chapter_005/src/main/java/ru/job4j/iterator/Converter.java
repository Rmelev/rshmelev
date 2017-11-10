package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter of iterator.
 */
public class Converter {
    /**
     * inner iterator.
     */
    private Iterator<Integer> iterInt;

    /**
     * convert method.
     * @param it - input iterator of iterators.
     * @return - result iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        iterInt = it.next();

        return new Iterator<Integer>() {
            public void transit() {
                if (!iterInt.hasNext() && it.hasNext()) {
                    iterInt = it.next();
                }
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    return iterInt.next();
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasNext() {
                boolean flag = false;
                transit();
                if (iterInt.hasNext()) {
                    flag = true;
                }

                return flag;
            }
        };
    }
}

