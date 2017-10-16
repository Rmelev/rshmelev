package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for calculate of equation.
 */
public class PointTest {
    /**
     * Equation: y(x) = ax + b.
     * x = 2, y = 7, a = 3, b = 1.
     */
    @Test
    public void aThreebOnexTwoySeven() {
        Point point = new Point(2, 7);
        boolean result = point.is(3, 1);
        assertThat(result, is(true));
    }

    /**
     * Equation: y(x) = ax + b.
     * x = 3, y = 8, a = 4, b = 2.
     */
    @Test
    public void aFourbTwoxThreeyEight() {
        Point point = new Point(3, 8);
        boolean result = point.is(4, 2);
        assertThat(result, is(false));
    }

    /**
     * Equation: y(x) = ax + b.
     * Changed y = 14.
     * x = 3, y = 14, a = 4, b = 2.
     */
    @Test
    public void aFourbTwoxThreeyFourteen() {
        Point point = new Point(3, 14);
        boolean result = point.is(4, 2);
        assertThat(result, is(true));
    }
}