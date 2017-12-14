package ru.job4j.tree;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * user's tree.
 * @param <E> - type parameter of elements.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * root element of tree.
     */
    private Node<E> root;

    /**
     * getter.
     * @return - root.
     */
    public Node<E> getRoot() {
        return this.root;
    }

    /**
     * variable for method: public List<Node<E>> toList (Node<E> node).
     * flag to add root.children elements.
     */
    private boolean rootWasAdded = false;
    /**
     * List of values of all nodes in Tree.
     */
    private List<E> added = new LinkedList<>();

    /**
     * getter.
     * @return - added.
     */
    public List<E> getAdded() {
        return this.added;
    }

    /**
     * Constructor of class Tree.
     * @param value - value of root element.
     */
    public Tree(E value) {
        root = new Node<>(value);
        added.add(value);
    }

    /**
     * Class for Node element.
     * @param <V> - type parameter of node value. Checked - will it work, if it isn't <E>.
     */
    public class Node<V> {
        /**
         * List of children.
         */
        private List<Node<V>> children;

        /**
         * getter.
         * @return - children.
         */
        public List<Node<V>> getChildren() {
            return this.children;
        }

        /**
         * value of node.
         */
        private V value;

        /**
         * Constructor of node element.
         * @param value - value of node element.
         */
        Node(V value) {
            this.children = new LinkedList<>();
            this.value = value;
        }

        /**
         * @return - String representation of node.
         */
        @Override
        public String toString() {
            return value.toString();
        }

    }

    /**
     * overridded add() of interface SimpleTree.
     * @param parent parent.
     * @param child child.
     * @return - true, if successful.
     */
    @Override
    public boolean add(E parent, E child) {
        Node<E> tempList;
        if (added.indexOf(child) > -1) {
            return false;
        }
        if (root.value.compareTo(parent) == 0) {
            added.add(child);
            root.children.add(new Node<>(child));
            return true;
        }
        tempList = findToAdd(root, parent, child);
        if (tempList != null) {
            added.add(child);
            return tempList.children.add(new Node<>(child));
        } else {
            return false;
        }
    }

    /**
     * Return element(parent), where we add child.
     * @param node - node for start search.
     * @param parent - parent for node  add.
     * @param child - child to add.
     * @return - parent to add child or null, if there are no parent in tree.
     */
    public Node<E> findToAdd(Node<E> node, E parent, E child) {
        Node<E> foundNode = null;

        for (Node<E> tempNode : node.children) {
            if (tempNode.value.compareTo(parent) == 0) {
                return tempNode;
            }
        }
        for (Node<E> tempNode : node.children) {
            foundNode = findToAdd(tempNode, parent, child);
            if (foundNode != null) {
                break;
            }
        }
        return foundNode;
    }

    /**
     * check: is this tree binary.
     * @return - true, if tree is binary.
     */
    public boolean isBinary() {
        rootWasAdded = false;
        boolean flag = true;
        for (Node<E> tempNode : toList(root)) {
            if (tempNode.children.size() > 2) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * pick elements from all tree in List<Node>.
     * @param node - node for beginning.
     * @return - list of nodes.
     */
    public List<Node<E>> toList(Node<E> node) {
        List<Node<E>> totalList = new LinkedList<>();

        if (!rootWasAdded) {
            totalList.add(root);
            totalList.addAll(root.children);
            rootWasAdded = true;
        }
        for (Node<E> tempNode : node.children) {
            if (tempNode.children.size() != 0) {
                totalList.addAll(tempNode.children);
                if (tempNode.children.size() != 0) {
                    totalList.addAll(toList(tempNode));
                }
            }
        }

        return totalList;
    }

    /**
     *iterator for user's tree.
     * @return - iterator.
     */
    @Override
    public Iterator<E> iterator() {
        Collections.sort(added);
        return new Iterator<E>() {
            private int counter = 0;

            @Override
            public boolean hasNext() {
                return added.size() > counter;
            }

            @Override
            public E next() {
                if (added.size() == counter) {
                    throw new NoSuchElementException();
                }
                return added.get(counter++);
            }
        };
    }
}