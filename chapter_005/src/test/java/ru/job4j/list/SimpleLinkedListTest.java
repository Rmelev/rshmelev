package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test class for user's linked list.
 */
public class SimpleLinkedListTest {
    /**
     * test1. For Integer.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenLinkedListWithArrayTestInputTestThenTheSameResults() {
        SimpleLinkedList<Integer> simpleLinkList = new SimpleLinkedList<>();
        Iterator<Integer> iter = simpleLinkList.iterator();
        simpleLinkList.add(3);
        simpleLinkList.add(7);
        simpleLinkList.add(11);
        simpleLinkList.add(45);
        simpleLinkList.add(89);
        simpleLinkList.add(15);
        System.out.println(simpleLinkList.get(2));

        for (Integer temp : simpleLinkList) {
            System.out.print(temp + " ");
        }

        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.next(), is(7));
        assertThat(iter.next(), is(11));
        assertThat(iter.next(), is(45));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(89));
        assertThat(iter.hasNext(), is(true));
        iter.next();
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    /**
     * test2. String.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenStringInputTestThenTheSameResults() {
        SimpleLinkedList<String> simpleLinkList = new SimpleLinkedList<>();
        Iterator<String> iter = simpleLinkList.iterator();
        simpleLinkList.add("NY");
        simpleLinkList.add("SF");
        simpleLinkList.add("HU");
        simpleLinkList.add("LA");
        simpleLinkList.add("DN");
        simpleLinkList.add("WA");
        System.out.println(simpleLinkList.get(2));

        for (String temp : simpleLinkList) {
            System.out.print(temp + " ");
        }

        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("NY"));
        assertThat(iter.next(), is("SF"));
        assertThat(iter.next(), is("HU"));
        assertThat(iter.next(), is("LA"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("DN"));
        assertThat(iter.hasNext(), is(true));
        iter.next();
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }
}