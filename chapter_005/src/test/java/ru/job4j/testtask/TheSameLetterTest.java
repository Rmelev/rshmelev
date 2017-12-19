package ru.job4j.testtask;

import java.util.HashMap;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class to compare two words. Is they consists of the same letters.
 */
public class TheSameLetterTest {
    /**
     * test.
     */
    @Test
    public void whenFirstWordAndSecondWordThenCompare() {
        HashMap<String, Integer> map = new HashMap<>();
        String firstWord = "avalon";
        String secondWord = "nolava";
        TheSameLetter word1 = new TheSameLetter(firstWord);
        TheSameLetter word2 = new TheSameLetter(secondWord);
        map.put(firstWord, word1.hashCode());
        map.put(secondWord, word2.hashCode());
        System.out.println(word1.hashCode());
        System.out.println(word2.hashCode());
        assertThat(map.get(firstWord).equals(map.get(secondWord)), is(true));
    }
}