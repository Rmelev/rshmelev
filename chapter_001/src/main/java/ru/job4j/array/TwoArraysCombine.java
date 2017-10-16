package ru.job4j.array;

/**
 * class for combine two arrays.
 */
public class TwoArraysCombine {
    /**
     * @param arrA - first array.
     * @param arrB - second array.
     * @return - combined array.
     */
    public int[] merge(int[] arrA, int[] arrB) {
        int indA = 0, indB = 0, indC = 0;
        int a = arrA.length;
        int b = arrB.length;
        int[] arrC = new int[a + b];
        while (indA < a && indB < b) {
            if (arrA[indA] < arrB[indB]) {
                arrC[indC++] = arrA[indA++];
            } else {
                arrC[indC++] = arrB[indB++];
            }
        }
        while (indA < a) {
            arrC[indC++] = arrA[indA++];
        }
        while (indB < b) {
            arrC[indC++] = arrB[indB++];
        }
        return arrC;
    }
}