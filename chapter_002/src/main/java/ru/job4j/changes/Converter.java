package ru.job4j.changes;

import java.util.Iterator;
import java.util.NoSuchElementException;

//тест на вакансию Java-стажер 6.04.
public class Converter {
    private Iterator<Integer> iterInt;

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
