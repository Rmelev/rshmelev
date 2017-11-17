package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
//import java.util.NoSuchElementException;

/**
 * Testing set on array base.
 */
public class SimpleSetTest {
    /**
     * test1.
     */
    @Test//(expected = NoSuchElementException.class)
    public void whenAddAndIterateSetThenCorrectIntegerResult() {
        SimpleSet<Integer> set = new SimpleSet<>(10);
        set.add(7);
        set.add(45);
        set.add(12);
        set.add(12);
        set.add(12);
        set.add(37);
        set.add(1);
        set.add(1);
        for (Object elem : set) {
            System.out.print(elem + "  ");
        }
        System.out.println();
        Iterator<Integer> iter = set.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(7));
        assertThat(iter.next(), is(45));
        assertThat(iter.next(), is(12));
        assertThat(iter.next(), is(37));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(false));
        //iter.next();
    }
    /**
     * test2.
     */
    @Test//(expected = NoSuchElementException.class)
    public void whenAddAndIterateSetThenCorrectStringResult() {
        SimpleSet<String> set = new SimpleSet<>(10);
        set.add("Boston");
        set.add("Philadelphia");
        set.add("Portland");
        set.add("Portland");
        set.add("Portland");
        set.add("Sacramento");
        set.add("Tijuana");
        set.add("Tijuana");
        for (Object elem : set) {
            System.out.print(elem + "  ");
        }
        System.out.println();
        Iterator<Integer> iter = set.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Boston"));
        assertThat(iter.next(), is("Philadelphia"));
        assertThat(iter.next(), is("Portland"));
        assertThat(iter.next(), is("Sacramento"));
        assertThat(iter.next(), is("Tijuana"));
        assertThat(iter.hasNext(), is(false));
        //iter.next();
    }
}