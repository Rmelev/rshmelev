package ru.job4j.array;
/**
 * class for array rotate, second edirion.
 */
public class RotateArray2 {
    /**
     * Realization with helper array.
     * @param ar - array for rotation (clockwise).
     * @return - rotated array.
     */
    public int[][] rotate(int[][] ar) {
        int n = ar.length;
        int[] arHelp = new int[n];
        int[][] arRes = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arHelp[j] = ar[n - 1 - j][i];
            }
            arRes[0] = arHelp;
        }
        return arRes;
    }
}
