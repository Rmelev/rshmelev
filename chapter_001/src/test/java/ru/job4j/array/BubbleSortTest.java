package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    /**
     * Method for testing array bubblesort.
     */
    @Test
    public void when5893617Then1356789ArrayBubbleSort() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = new int[] {5, 8, 9, 3, 6, 1, 7};
        int[] resultArray = new int[] {1, 3, 5, 6, 7, 8, 9};
        int[] result = bubbleSort.sort(array);
        assertThat(result, is(resultArray));
    }

    /**
     * More difficult test for bubblesort with duplicated values
     * and even number of array cells.
     */
    @Test
    public void when5893617472452Then1223456778945ArrayBubbleSort() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = new int[] {5, 8, 9, 3, 6, 1, 7, 4, 7, 2, 45, 2};
        int[] resultArray = new int[] {1, 2, 2, 3, 4, 5, 6, 7, 7, 8, 9, 45};
        int[] result = bubbleSort.sort(array);
        assertThat(result, is(resultArray));
    }
}