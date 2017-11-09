package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter of iterator.
 */
public class Converter {
    /**
     * helper variable, inner iterator.
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
            public void perehod() {
                if (!iterInt.hasNext() && it.hasNext()) {
                    iterInt = it.next();
                }
            }

            @Override
            public Integer next() {
                perehod();
                if (!it.hasNext() && !iterInt.hasNext()) {
                    throw new NoSuchElementException();
                }

                return iterInt.next();
            }

            @Override
            public boolean hasNext() {
                boolean flag = false;
                perehod();
                if (iterInt.hasNext()) {
                    flag = true;
                }

                return flag;
            }
        };
    }
}

