package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    /**
     * Reverse odd Array.
     */
    @Test
    public void when5789341Then1439875Odd() {
        Turn turn = new Turn();
        int[] array = new int[] {5, 7, 8, 9, 3, 4, 1};
        int[] expectArray  = new int[] {1, 4, 3, 9, 8, 7, 5};
        int[] resultArray = turn.back(array);
        assertThat(resultArray, is(expectArray));
    }

    /**
     * Reverse even Array.
     */
    @Test
    public void when3333333Then5555555Even() {
        Turn turn = new Turn();
        int[] arrayOdd = new int[] {3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 5, 5};
        int[] expectArray  = new int[] {5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 3, 3, 3, 3};
        int[] resultArray = turn.back(arrayOdd);
        assertThat(resultArray, is(expectArray));
    }
}