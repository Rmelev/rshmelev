package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * set on array base.
 * @param <E> - type parameter of elements.
 */
public class SimpleSet<E> implements Iterable {
    /**
     * array of elements.
     */
    private Object[] objects;
    /**
     * counter of elements in set.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param size - size of array.
     */
    public SimpleSet(int size) {
        this.objects = new Object[size];
    }

    /**
     * add element, if there are no equals elements in set.
     * @param elem - added element.
     */
    public void add(E elem) {
        for (int i = 0; i < index; i++) {
            if (elem.equals(objects[i])) {
                System.out.println("Equals element(" + objects[i] + ") is already in set");
                return;
            }
        }
        this.objects[index++] = elem;
    }

    /**
     * iterator for set.
     * @return - interator.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int nextElem = 0;
            @Override
            public boolean hasNext() {
                return nextElem < index;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) objects[nextElem++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}