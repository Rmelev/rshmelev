package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test class EvenIterator.
 */
public class EvenIteratorTest {
    /**
     * test1.
     */
    @Test
    public void whenTwoNextAndOneHasNextThenFalse() {
        Iterator it = new EvenIterator(new int[] {4, 2, 1, 1});
        it.next();
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(false));
    }

    /**
     * helper variable.
     */
    private Iterator it;

    /**
     * Before conditions.
     */
    @Before
    public void setUp() {
        it = new EvenIterator(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    /**
     * test2.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * test3.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    /**
     * test4.
     */
    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenIterator(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }
}