package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for test of piramide painting.
 */
public class PaintTest {
    /**
     * test1.
     */
    @Test
    public void whenHeight3ThenPyramid() {
        Paint paint = new Paint();
        String result = paint.piramid(3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("  ^%s ^^^%s^^^^^%s", line, line, line);
        assertThat(result, is(expected));
    }

    /**
     * test2.
     */
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Paint paint = new Paint();
        String result = paint.piramid(5);
        final String line = System.getProperty("line.separator");
        String expected = String.format("    ^%s   ^^^%s  ^^^^^%s ^^^^^^^%s^^^^^^^^^%s", line, line, line, line, line);
        assertThat(result, is(expected));
    }
}