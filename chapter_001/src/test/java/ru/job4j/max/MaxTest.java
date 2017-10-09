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
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }
    @Test
    public void whenFirstMoreSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 2);
        assertThat(result, is(3));
    }
    @Test
    public void whenFirstLessSecondBigNumbers() {
        Max maxim = new Max();
        int result = maxim.max(7843723, 78437231);
        assertThat(result, is(78437231));
    }
}