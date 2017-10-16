package ru.job4j.array;

/**
 * class for array turn.
 */
public class Turn {
    /**
     * @param array - array of int values for reverse.
     * @return - reversed array.
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}