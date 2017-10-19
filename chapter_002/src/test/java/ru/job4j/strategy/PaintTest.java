package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tests.
 */
public class PaintTest {
    /**
     * test for tiangle.
     */
    @Test
    public void whenStringThenTriangle() {
        Paint paint = new Paint();
        assertThat(paint.draw(new Triangle()), is("   #   \n  ###  \n ##### \n#######\n"));
    }

    /**
     * test for square.
     */
    @Test
    public void whenStringThenSquare() {
        Paint paint = new Paint();
        final String line = System.getProperty("line.separator");
        String expected = String.format("#######%s#######%s#######%s#######%s#######%s", line, line, line, line, line);
        assertThat(paint.draw(new Square()), is(expected));
    }
}