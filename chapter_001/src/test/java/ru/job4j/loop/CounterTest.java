package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    /**
     * Calculates odd numbers from 1 to 10.
     */
    @Test
    public void whenFromOneToTenThen30() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
    @Test
    public void whenFromFourToFourteenThen54() {
        Counter counter = new Counter();
        int result = counter.add(4, 14);
        assertThat(result, is(54));
    }
}