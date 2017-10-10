package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Roman Shmelev (mailto:roman.shmelev@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {
    /**
     * Test max().
     */
    @Test
    public void whenFirstLessSecondThenSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }
    @Test
    public void whenFirstMoreSecondThenFirst() {
        Max maxim = new Max();
        int result = maxim.max(3, 2);
        assertThat(result, is(3));
    }
    @Test
    public void whenFirstLessSecondBigNumbersThenSecond() {
        Max maxim = new Max();
        int result = maxim.max(7843723, 78437231);
        assertThat(result, is(78437231));
    }
    @Test
    public void whenFirstIsBiggestOfThreeNumbersThenFirst() {
        Max maxim = new Max();
        int result = maxim.max(7843723, 7843, 12345);
        assertThat(result, is(7843723));
    }
    @Test
    public void whenSecondIsBiggestOfThreeNumbersThenSecond() {
        Max maxim = new Max();
        int result = maxim.max(784, 7843, 123);
        assertThat(result, is(7843));
    }
}