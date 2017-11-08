package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test class of prime iterator.
 */
public class PrimeItTest {
    /**
     * test1.
     */
    @Test
    public void whenCreateIteratorForSimpleThenSimple() {
        Iterator it = new PrimeIt(new int[] {3, 4, 5, 6, 7, 8, 9, 10, 11});
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        it.hasNext();
        it.hasNext();
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(11));
        it.hasNext();
        it.hasNext();
        assertThat(it.hasNext(), is(false));
    }

    /**
     * lelper variable for create object of iterator.
     */
    private Iterator<Integer> it;

    /**
     * input array.
     */
    @Before
    public void setUp() {
        it = new PrimeIt(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});
    }

    /**
     * test2.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnPrimeNumbersOnly() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3571));
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
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(3571));
    }

    /**
     * test4.
     */
    @Test
    public void shouldReturnFalseCauseThereIsNoAnyPrimeNumber() {
        it = new PrimeIt(new int[]{4, 6});
        assertThat(it.hasNext(), is(false));
    }
}