package ru.job4j.list;

import org.junit.Test;

//import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * testing queue & stack.
 */
public class StackQueueTest {
    /**
     * test1.
     */
    @Test//(expected = NoSuchElementException.class)
    public void whenThen() {
        SimpleStack<Integer> simpleStack = new SimpleStack<>();
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        simpleStack.push(7);
        simpleStack.push(8);
        simpleStack.push(15);
        simpleQueue.push("aa");
        simpleQueue.push("bb");
        simpleQueue.push("cc");
        assertThat(simpleStack.poll(), is(15));
        assertThat(simpleStack.poll(), is(8));
        assertThat(simpleStack.poll(), is(7));
        //simpleStack.poll();
        simpleStack.push(21);
        simpleStack.push(47);
        assertThat(simpleStack.poll(), is(47));
        assertThat(simpleQueue.poll(), is("aa"));
        assertThat(simpleQueue.poll(), is("bb"));
        assertThat(simpleQueue.poll(), is("cc"));
        //simpleQueue.poll();
        simpleQueue.push("dd");
        simpleQueue.push("ee");
        assertThat(simpleQueue.poll(), is("dd"));
        assertThat(simpleQueue.poll(), is("ee"));
    }
}
