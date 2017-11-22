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
        if (!findDupl(e)) {
            super.add(e);
        } else {
            System.out.println("Equals element(" + e + ") is already in set");
        }

    }

    /**
     * find duplicate element before add to set.
     * @param e - checking element.
     * @return - true, if found.
     */
    public boolean findDupl(E e) {
        boolean flag = false;
        Node<E> temp = getFirst();
        while (temp != null) {
            if (temp.getItem().equals(e)) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        return flag;
    }
}
