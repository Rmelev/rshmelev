package ru.job4j.tree;

/**
 * interface for user's tree.
 * @param <E>
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Add element child in parent.
     * Parent can have list if children.
     * @param parent parent.
     * @param child child.
     * @return - true, if successful.
     */
    boolean add(E parent, E child);
}