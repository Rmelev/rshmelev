package ru.job4j.changes;

import java.util.Arrays;

/**
 * class for change delivery.
 */
public class Change {

    /**
     * count of elements in returning array.
     */
    private int count = 0;
    /**
     * flag of the end of picking coins.
     */
    private boolean flag = false;

    /**
     * work with primary variables (value & price).
     * @param value - given banknote.
     * @param price - price of product.
     * @return - array of change coins.
     */
    int[] changes(int value, int price) {
        int[] arrReturn = new int[10];
        int[] arrChange = {10, 5, 2, 1};
        int remainder = value - price;
        return Arrays.copyOf(picker(remainder, arrChange, arrReturn), count);
    }

    /**
     *recursive work with remainder.
     * @param remainder - remainder after operation.
     * @param arrChange - array change coin choice.
     * @param arrReturn - cumulative array of change.
     * @return - array of change.
     */
    public int[] picker(int remainder, int[] arrChange, int[] arrReturn) {
        for (int i = 0; i < arrChange.length; i++) {
            if (remainder == arrChange[i]) {
                arrReturn[count++] = remainder;
                flag = true;
                break;
            }
        }
        for (int i = 0; i < arrChange.length; i++) {
            if (flag) {
                return arrReturn;
            }
            if (remainder > arrChange[i]) {
                arrReturn[count++] = arrChange[i];
                remainder = remainder - arrChange[i];
                picker(remainder, arrChange, arrReturn);
            }
        }
        return arrReturn;
    }
}