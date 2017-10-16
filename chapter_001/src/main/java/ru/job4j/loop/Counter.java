package ru.job4j.loop;
/**
 * class for count sum of odd numbers.
 */
public class Counter {
    /**
     * adds odd numbers from first to finish.
     * @param first - the begging of interval.
     * @param finish - the end of interval.
     * @return - sum of interval odd numbers.
     */
    public int add(int first, int finish) {
        int sum = 0;
        for (int i = first; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}