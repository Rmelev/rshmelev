package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * class for test calculation area of triangle.
 */
public class TriangleTest {
    /**
     * Simple rectangular triangle.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 5.9D;
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * point lies on one line. It's not triangle.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleAreaFalse() {
        Point a = new Point(1, 1);
        Point b = new Point(2, 2);
        Point c = new Point(3, 3);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = -1;
        assertThat(result, closeTo(expected, 0.1));
    }
}