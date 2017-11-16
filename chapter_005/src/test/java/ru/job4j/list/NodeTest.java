package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * find cycle class.
 */
public class NodeTest {
    /**
     * count elements, where we were.
     */
    private int countSteps = 0;
    /**
     * counter of created elements.
     */
    private int countObjects = 0;
    /**
     * true, if linked list have cycle.
     */
    private boolean flag = false;
    /**
     * element of linked list class.
     * @param <T> - type of elements.
     */
    public class Node<T> {
        /**
         * value of element.
         */
        private T value;
        /**
         * reference to next element in linked list.
         */
        private Node<T> next;

        /**
         * Constructor.
         * @param value - value of element.
         */
        public Node(T value) {
            this.value = value;
            countObjects++;
        }
    }

    /**
     * definite cycle in linked list.
     * @param elem - first elem of linked list.
     * @return - true if linked list have cycle.
     */
    public boolean hasCycle(Node elem) {
        countSteps++;
        if (elem == null) {
            return flag;
        }
        if (countSteps > countObjects) {
            flag = true;
            return flag;
        }
        hasCycle(elem.next);
        return flag;
    }

    /**
     * test1. Cycle point: four - > first.
     */
    @Test
    public void whenAddAllElementsThenDefiniteCycle() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(hasCycle(first
        ), is(true));
    }

    /**
     * test2. Cycle point: third - > two.
     */
    @Test
    public void whenHaveCycleThenDefiniteCycle() {
        Node first = new Node(21);
        Node two = new Node(22);
        Node third = new Node(23);
        Node four = new Node(24);
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = null;
        assertThat(hasCycle(first), is(true));
    }

    /**
     * test3. String elements. No cycle.
     */
    @Test
    public void whenStringDataThenTheSameResults() {
        Node first = new Node("aa");
        Node two = new Node("bb");
        Node third = new Node("cc");
        Node four = new Node("dd");
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertThat(hasCycle(first), is(false));
    }
}