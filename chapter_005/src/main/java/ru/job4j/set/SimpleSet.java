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
        if (!findDupl(elem)) {
            this.objects[index++] = elem;
        } else {
            System.out.println("Equals element(" + elem + ") is already in set");
        }
    }

    /**
     * find duplicate element before add to set.
     * @param elem - checking element.
     * @return - true, if found.
     */
    public boolean findDupl(E elem) {
        boolean flag = false;
        for (int i = 0; i < index; i++) {
            if (elem.equals(objects[i])) {
                flag = true;
                return flag;
            }
        }
        return flag;
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