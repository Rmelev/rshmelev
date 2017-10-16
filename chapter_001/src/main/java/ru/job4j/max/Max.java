package ru.job4j.max;

/**
 * class find maximum of two numbers.
 */
public class Max {
    /**
     * @param first - first number for comparison.
     * @param second - second number for comparison.
     * @return - the biggest of two numbers.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * @param first - first number for comparison.
     * @param second - second number for comparison.
     * @param third - third number for comparison.
     * @return - the biggest of two numbers.
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}