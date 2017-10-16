package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test realizations with helper array.
 */
public class RotateArray2Test {
    /**
     * test1.
     */
    @Test
    public void when123456789Then741852963() {
        RotateArray array = new RotateArray();
        int[][] arrayTest = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] result = array.rotate(arrayTest);
        int[][] resultTest = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertThat(result, is(resultTest));
    }

    /**
     * test2.
     */
    @Test
    public void when1234Then3142() {
        RotateArray array = new RotateArray();
        int[][] arrayTest = {{1, 2}, {3, 4}};
        int[][] result = array.rotate(arrayTest);
        int[][] resultTest = {{3, 1}, {4, 2}};
        assertThat(result, is(resultTest));
    }

    /**
     * test3.
     */
    @Test
    public void when4x4Then4x4() {
        RotateArray array = new RotateArray();
        int[][] arrayTest = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] result = array.rotate(arrayTest);
        int[][] resultTest = {{13, 9, 5, 1}, {14, 10, 6, 2}, {15, 11, 7, 3}, {16, 12, 8, 4}};
        assertThat(result, is(resultTest));
    }
}