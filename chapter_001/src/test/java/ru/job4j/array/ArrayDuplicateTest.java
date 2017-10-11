package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenHelloPeaceHelloSuperPeaceThenHelloPeaceSuper() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] arr = {"Hello", "Peace", "Hello", "Super", "Peace"};
        String[] result = arrayDuplicate.remove(arr);
        String[] expected = {"Hello", "Peace", "Super"};
        assertThat(result, is(expected));
    }
    @Test
    public void whenHelloSuperSuperSuperPeaceSuperTestTestPeaceDedThenHelloSuperPeaceTestDed() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] arr = {"Hello", "Super","Super", "Super", "Peace", "Super", "Test", "Test", "Peace", "Ded"};
        String[] result = arrayDuplicate.remove(arr);
        String[] expected = {"Hello", "Super", "Peace", "Test", "Ded"};
        assertThat(result, is(expected));
    }
}