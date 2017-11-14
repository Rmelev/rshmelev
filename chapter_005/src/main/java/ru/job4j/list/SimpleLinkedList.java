package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * user's linked list.
 * @param <E> - type parameter.
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * counter of list size.
     */
    private int size = 0;
    /**
     * first element of list.
     */
    private Node<E> first;
    /**
     * last element of list.
     */
    private Node<E> last;
    /**
     * current position of itertor.
     */
    private Node<E> countIter;

    /**
     * inner class of list elements.
     * @param <E> - type parameter.
     */
    private static class Node<E> {
        /**
         * meaning of element.
         */
        private E item;
        /**
         * next elem.
         */
        private Node<E> next;
        /**
         * previous elem.
         */
        private Node<E> prev;

        /**
         * Node Constructor.
         * @param prev - prev elem.
         * @param element - elem.
         * @param next - next elem.
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * add element to list.
     * @param e - added elem.
     */
    @Override
    public void add(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
            countIter = first;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * get element from list with index position.
     * @param index - index.
     * @return - element.
     */
    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * control: have list appropriate index and throw exception, if haven't.
     * @param index - needful index.
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * control: have list appropriate index.
     * @param index - needful index.
     * @return - true, if it is.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * message of exception.
     * @param index - called index.
     * @return - message.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * transfer to element with needfull index.
     * @param index - needfull index.
     * @return - needfull element.
     */
    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * user's iterator.
     * @return - iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int nextElem = 0;

            @Override
            public boolean hasNext() {
                return nextElem < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    countIter = first;
                    throw new NoSuchElementException();
                }
                nextElem++;
                if (countIter.next != null) {
                    countIter = countIter.next;
                    return countIter.prev.item;
                } else {
                    Node<E> temp = countIter;
                    countIter = first;
                    return temp.item;
                }
            }
        };
    }
}
