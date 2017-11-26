package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    Node<E> root;
    Node<E> tempNode;

    boolean rootWasAdded = false;

    Set<E> totalListVal = new TreeSet<>();



    public Tree(E value) {
        root = new Node<E>(value);
        tempNode = root;
    }

    public class Node<E>{
        List<Node<E>> children;
        E value;
        Node(E value) {
            this.children = new LinkedList<>();
            this.value = value;
        }

        @Override
        public String toString() {
            return Integer.toString((Integer) value);
        }

    }


    @Override
    public boolean add(E parent, E child) {
        if (root.value.equals(parent)) {
            root.children.add(new Node<>(child));
            return true;
        }
        return findToAdd(root, parent).children.add(new Node<>(child));
    }

    // По сути он может вернуть элемент, в который останется только добавить child.
    public Node<E> findToAdd (Node<E> node, E parent) {
        Node<E> foundNode = null;
        for (Node<E> tempNode : node.children) {
            if (tempNode.value.equals(parent)) {
                return tempNode;
            }
        }
        if (foundNode == null) {
            for (Node<E> tempNode : node.children) {
                foundNode = findToAdd(tempNode, parent);
                if (foundNode != null) {
                    break;
                }
            }
        }
        return foundNode;
    }

    public List<Node<E>> toList (Node<E> node) {
        List<Node<E>> totalList = new LinkedList<>();
        if (!rootWasAdded) {
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
        for (Node<E> tempNode : totalList) {
            totalListVal.add(tempNode.value);
        }

        return totalList;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            List<E> finalList = new ArrayList<>(totalListVal);
            int counter = 0;

            @Override
            public boolean hasNext() {
                return totalListVal.size() > counter;
            }

            @Override
            public E next() {
                return finalList.get(counter++);
            }
        };
    }
}