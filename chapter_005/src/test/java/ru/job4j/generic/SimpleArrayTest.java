package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for testing SimpleArray.
 */
public class SimpleArrayTest {
    /**
     * test1. Integer.
     */
    @Test
    public void whenIntegerElementsThenHaveAppropriateResult() {
        SimpleArray<Integer> sArray = new SimpleArray<>(7);
        sArray.add(7);
        sArray.add(5);
        sArray.add(3);
        sArray.add(6);
        sArray.add(4);
        sArray.add(1);
        sArray.delete(6);
        sArray.update(4, 5);
        sArray.add(15);
        assertThat(sArray.get(3), is(5));
        assertThat(sArray.get(5), is(15));
        //for (int i = 0; i < sArray.objects.length; i++) {
        //    System.out.print(sArray.objects[i] + "  ");
        //}
    }

    /**
     * test2. String.
     */
    @Test
    public void whenStringElementsThenHaveAppropriateResult() {
        SimpleArray<String> sArray = new SimpleArray<>(7);
        sArray.add("bo");
        sArray.add("ka");
        sArray.add("ra");
        sArray.add("ton");
        sArray.add("in");
        sArray.add("Flo");
        sArray.delete("ton");
        sArray.update("in", "on");
        sArray.add("rida");
        assertThat(sArray.get(3), is("on"));
        assertThat(sArray.get(5), is("rida"));
        //for (int i = 0; i < sArray.objects.length; i++) {
        //    System.out.print(sArray.objects[i] + "  ");
        //}
    }
}