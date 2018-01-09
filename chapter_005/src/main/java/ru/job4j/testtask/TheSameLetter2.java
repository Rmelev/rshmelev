package ru.job4j.testtask;

import java.util.HashMap;
import java.util.Map;

/**
 * compare two words.
 */
public class TheSameLetter2 {
    /**
     * compare to two maps, which we got from two words.
     * @param w1 - first word.
     * @param w2 - second word.
     * @return - true, if words consist of the same letters.
     */
    boolean comparator(String w1, String w2) {
        boolean flag = true;
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        for (Character nextChar : w1.toCharArray()) {
            m1.merge(nextChar, 1, (a, b) -> a + b);
        }
        for (Character nextChar : w2.toCharArray()) {
            m2.merge(nextChar, 1, (a, b) -> a + b);
        }
        if (m1.size() != m2.size()) {
            flag = false;
        }
        for (Character nextChar : m1.keySet()) {
            if (m1.get(nextChar) != m2.get(nextChar)) {
                flag = false;
                break;
            }
        }
        System.out.println(m1);
        System.out.println(m2);
        return flag;
    }

    /**
     * main().
     * @param args - args.
     */
    public static void main(String[] args) {
        TheSameLetter2 test = new TheSameLetter2();
        System.out.println(test.comparator("авалон", "нолава"));
    }
}
