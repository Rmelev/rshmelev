package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

/**
 * set on the base of linked list.
 * @param <E> - type of elements.
 */
public class SimpleSetLinked<E> extends SimpleLinkedList<E> {
    /**
     * add, overrided for don't add equals elements.
     * @param e - added elem.
     */
    @Override
    public void add(E e) {
        Node<E> temp = getFirst();
        while (temp != null) {
            if (temp.getItem().equals(e)) {
                System.out.println("Equals element(" + temp.getItem() + ") is already in set");
                return;
            }
            temp = temp.getNext();
        }
        super.add(e);
    }
}
