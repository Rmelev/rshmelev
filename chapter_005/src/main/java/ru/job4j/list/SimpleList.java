package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * generic class, user's list with generic elements.
 * @param <E> - type of elements in array.
 */
@ThreadSafe
public class SimpleList<E> implements SimpleContainer<E> {
    /**
     * array of elements.
     */
    @GuardedBy("this")
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
    public synchronized void add(E e) {
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
    public synchronized E get(int index) {
        return (E) container[index];
    }

    /**
     * iterator for user's list.
     * @return - iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int nextElem = 0;
            @Override
            public boolean hasNext() {
                return nextElem < index;
            }
            @Override
            public E next() {
                if (hasNext()) {
                    nextElem++;
                    return get(nextElem - 1);
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * increase size of array if necessary.
     */
    private synchronized void grow() {
        int oldCapacity = container.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        container = Arrays.copyOf(container, newCapacity);
    }
}
