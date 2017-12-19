package ru.job4j.testtask;

public class TheSameLetter {
    /**
     * word to compare.
     */
    private String word;
    /**
     * sum of char values in the word.
     */
    private int sum = 0;

    /**
     * Constructor.
     * @param wordParam - word to compare.
     */
    TheSameLetter(String wordParam) {
        this.word = wordParam;
    }

    /**
     * overrided hashCode().
     * @return - integer representation of word, depending chars of word.
     */
    @Override
    public int hashCode() {
        char[] chars = word.toCharArray();
        for (Character tempChar : chars) {
            sum += tempChar;
        }
        return sum;
    }
}
