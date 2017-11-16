package ru.job4j.list;

/**
 * queue with methods push() and poll().
 * @param <T> - parameter of elements.
 */
public class SimpleQueue<T> {
    /**
     * queue.
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
        return lList.getFirstForQueue();
    }
}
