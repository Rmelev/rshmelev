package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * user's linked list.
 * @param <E> - type parameter.
 */
@ThreadSafe
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * counter of list size.
     */
    private int size = 0;
    /**
     * first element of list.
     */
    @GuardedBy("this")
    private Node<E> first;

    /**
     * getter.
     * @return - first.
     */
    public synchronized Node<E> getFirst() {
        return first;
    }

    /**
     * get first.
     * @return - the first.
     */
    public synchronized E getFirstForQueue() {
        if (size == 0) {
            throw new NoSuchElementException("There are no more elements in queue.");
        }
        if (size == 1) {
            last = null;  //in order to filling queue from start position. See line 102.
        }
        E temp = first.item;
        first = first.next;
        size--;
        return temp;
    }

    /**
     * last element of list.
     */
    @GuardedBy("this")
    private Node<E> last;

    /**
     * get last.
     * @return - the last.
     */
    public synchronized E getLastForStack() {
        if (size == 0) {
            throw new NoSuchElementException("There are no more elements in stack.");
        }
        E temp = last.item;
        last = last.prev;
        size--;
        return temp;
    }

    /**
     * current position of itertor.
     */
    private Node<E> countIter;

    /**
     * inner class of list elements.
     * @param <E> - type parameter.
     */
    public static class Node<E> {
        /**
         * meaning of element.
         */
        private E item;

        /**
         * getter.
         * @return - item of Node.
         */
        public E getItem() {
            return this.item;
        }

        /**
         * next elem.
         */
        private Node<E> next;

        /**
         * getter.
         * @return - next of Node.
         */
        public Node<E> getNext() {
            return this.next;
        }

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
    public synchronized void add(E e) {
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
    public synchronized E get(int index) {
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
    synchronized Node<E> node(int index) {
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
                    countIter = getFirst();
                    throw new NoSuchElementException();
                }
                nextElem++;
                if (countIter.next != null) {
                    countIter = countIter.next;
                    return countIter.prev.item;
                } else {
                    Node<E> temp = countIter;
                    countIter = getFirst();
                    return temp.item;
                }
            }
        };
    }
}
