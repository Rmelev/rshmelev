package ru.job4j.tree;

/**
 * interface for user's tree.
 * @param <E>
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return - true, if successful.
     */
    boolean add(E parent, E child);
}