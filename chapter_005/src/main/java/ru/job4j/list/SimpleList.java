package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * generic class, user's list with generic elements.
 * @param <E> - tipe of elements in array.
 */
public class SimpleList<E> implements SimpleContainer<E> {
    /**
     * array of elements.
     */
    private Object[] container;
    /**
     * counter of elements.
     */
    private int index = 0;

    /**
     * Constructor.
     */
    public SimpleList() {
        this.container = new Object[3];
    }

    /**
     * add elements in list.
     * @param e - element.
     */
    public void add(E e) {
        if (index == container.length - 1) {
            this.grow();
        }
        container[index++] = e;
    }

    /**
     * get elements from list.
     * @param index - index of element in array.
     * @return - element with required index.
     */
    public E get(int index) {
        return (E) container[index];
    }

    /**
     * iterator for user's list.
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int nextElem = 0;
            @Override
            public boolean hasNext() {
                boolean flag = false;
                if (nextElem < index) {
                    flag = true;
                }
                return flag;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return (E) container[nextElem++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * increase size of array if necessary.
     */
    private void grow() {
        int oldCapacity = container.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        container = Arrays.copyOf(container, newCapacity);
    }
}
