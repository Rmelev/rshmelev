package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    /**
     * Factorial if n = 5.
     */
    @Test
    public void whenN5ThenFactorial120() {
        Factorial fact = new Factorial();
        int result = fact.calc(5);
        assertThat(result, is(120));
    }

    /**
     * Factorial if n = 5.
     */
    @Test
    public void whenN0ThenFactorial1() {
        Factorial fact = new Factorial();
        int result = fact.calc(0);
        assertThat(result, is(1));
    }
    @Test
    public void whenN5ThenFactorial120Recursion() {
        Factorial fact = new Factorial();
        int result = fact.calcRec(5);
        assertThat(result, is(120));
    }
}