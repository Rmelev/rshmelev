package ru.job4j.array;

public class RotateArray {
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

