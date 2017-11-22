package ru.job4j.tree;

import java.util.Iterator;
import java.util.List;

class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    class Node<E> {
        List<Node<E>> childen;
        E value;
    }

    @Override
    public boolean add(E parent, E child) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}