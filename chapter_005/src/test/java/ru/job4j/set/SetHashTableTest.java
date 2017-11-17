package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * user's set on hash table base.
 */
public class SetHashTableTest {
    /**
     * test1.  Integer elements.
     */
    @Test
    public void whenSetOnHashTableBaseThenCorrectResults() {
        SetHashTable<Integer> setHash = new SetHashTable<>(13);
        setHash.add(9);
        setHash.add(45);
        setHash.add(21);
        setHash.add(47);
        setHash.add(15);
        setHash.add(37);
        setHash.add(1);
        setHash.add(3);
        for (int i = 0; i < setHash.getArrsize(); i++) {
            System.out.print(setHash.getObjects()[i] + "  ");
        }
        System.out.println();
        assertThat(setHash.contains(15), is(true));
        assertThat(setHash.contains(16), is(false));
        assertThat(setHash.remove(37), is(true));
        assertThat(setHash.remove(3), is(true));
        for (int i = 0; i < setHash.getArrsize(); i++) {
            System.out.print(setHash.getObjects()[i] + "  ");
        }
        System.out.println();
    }
    /**
     * test2. String elements.
     */
    @Test
    public void whenSetOnHashTableBaseStringElementsThenCorrectResults() {
        SetHashTable<String> setHash = new SetHashTable<>(13);
        setHash.add("A");
        setHash.add("B");
        setHash.add("C");
        setHash.add("D");
        setHash.add("E");
        setHash.add("F");
        setHash.add("G");
        setHash.add("H");
        for (int i = 0; i < setHash.getArrsize(); i++) {
            System.out.print(setHash.getObjects()[i] + "  ");
        }
        System.out.println();
        assertThat(setHash.contains("B"), is(true));
        assertThat(setHash.contains("J"), is(false));
        assertThat(setHash.remove("F"), is(true));
        assertThat(setHash.remove("C"), is(true));
        for (int i = 0; i < setHash.getArrsize(); i++) {
            System.out.print(setHash.getObjects()[i] + "  ");
        }
        System.out.println();
    }
}