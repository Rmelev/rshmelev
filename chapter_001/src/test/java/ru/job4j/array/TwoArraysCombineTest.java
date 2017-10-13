package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TwoArraysCombineTest {
    @Test
    public void when46789I1247121519Then124467789121519() {
        TwoArraysCombine arr = new TwoArraysCombine();
        int[] arrFirst = {4, 6, 7, 8, 9};
        int[] arrSecond = {1, 2, 4, 7, 12, 15, 19};
        int[] result = arr.merge(arrFirst, arrSecond);
        int[] expected = {1, 2, 4, 4, 6, 7, 7, 8, 9, 12, 15, 19};
        assertThat(result, is(expected));
    }
    @Test
    public void whenTwoArrsThenOneArr() {
        TwoArraysCombine arr = new TwoArraysCombine();
        int[] arrFirst = {2, 4, 5, 5, 6, 8, 10, 11, 11, 12, 13};
        int[] arrSecond = {1, 3, 5, 7, 9};
        int[] result = arr.merge(arrFirst, arrSecond);
        int[] expected = {1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9, 10, 11, 11, 12, 13};
        assertThat(result, is(expected));
    }
}