package ru.job4j.changes;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for test change algorithm.
 */
public class ChangeTest {
    /**
     * Test1.
     */
    @Test
    public void whenGive100ThenGetChange() {
        Change change = new Change();
        int[] arrChange = {10, 10, 10, 10, 10, 10, 5, 2, 2};
        int[] changeResult = change.changes(100, 31);
        assertThat(changeResult, is(arrChange));
    }
}