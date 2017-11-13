package ru.job4j.list;

/**
 * abstract methods for list.
 * @param <E>
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * add elem.
     * @param e - added elem.
     */
    void add(E e);

    /**
     * get elem.
     * @param index - index.
     * @return - elem.
     */
    E get(int index);
}