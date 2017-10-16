package ru.job4j.array;
/**
 * class for array rotate.
 */
public class RotateArray {
    /**
     *
     * @param ar - array for rotate.
     * @return rotated array.
     */
    public int[][] rotate(int[][] ar) {
        int n = ar.length;
        int[][] arRes = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arRes[j][i] = ar[n - 1 - i][j];
            }
        }
        return arRes;
    }
}

