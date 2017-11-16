package ru.job4j.list;

/**
 * stack with methods push() and poll().
 * @param <T> - parameter of elements.
 */
public class SimpleStack<T> {
    /**
     * stack.
     */
    private SimpleLinkedList<T> lList = new SimpleLinkedList<>();

    /**
     * add element in stack.
     * @param value - element.
     */
    public void push(T value) {
        lList.add(value);
    }

    /**
     * take element from stack.
     * @return - element.
     */
    public T poll() {
        return lList.getLastForStack();
    }

}
