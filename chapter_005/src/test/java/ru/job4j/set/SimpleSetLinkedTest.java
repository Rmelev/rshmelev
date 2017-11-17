package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test set, on linked list base.
 */
public class SimpleSetLinkedTest {
    /**
     * test1.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenSimpleSetLinkedThenAppropriateResults() {
        SimpleSetLinked<Integer> setLinked = new SimpleSetLinked<>();
        setLinked.add(7);
        setLinked.add(45);
        setLinked.add(12);
        setLinked.add(12);
        setLinked.add(12);
        setLinked.add(37);
        setLinked.add(1);
        setLinked.add(1);
        for (Object elem : setLinked) {
            System.out.print(elem + "  ");
        }
        System.out.println();
        Iterator<Integer> iter = setLinked.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(7));
        assertThat(iter.next(), is(45));
        assertThat(iter.next(), is(12));
        assertThat(iter.next(), is(37));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }
    /**
     * test2.
     */
    @Test
    public void whenSimpleSetLinkedStringDataThenAppropriateResults() {
        SimpleSetLinked<String> setLinked = new SimpleSetLinked<>();
        setLinked.add("Atlanta");
        setLinked.add("Denver");
        setLinked.add("Phoenix");
        setLinked.add("Phoenix");
        setLinked.add("Phoenix");
        setLinked.add("Las Vegas");
        setLinked.add("Kansas");
        setLinked.add("Kansas");
        for (Object elem : setLinked) {
            System.out.print(elem + "  ");
        }
        System.out.println();
        Iterator<String> iter = setLinked.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Atlanta"));
        assertThat(iter.next(), is("Denver"));
        assertThat(iter.next(), is("Phoenix"));
        assertThat(iter.next(), is("Las Vegas"));
        assertThat(iter.next(), is("Kansas"));
        assertThat(iter.hasNext(), is(false));
    }
}
