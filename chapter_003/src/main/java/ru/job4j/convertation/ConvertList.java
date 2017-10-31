package ru.job4j.convertation;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

/**
 * Convert array[][] to List and back.
 */
public class ConvertList {
    List<Integer> list = new ArrayList<>();

    /**
     * Convert array[][] to List.
     * @param array - array.
     * @return - list.
     */
    public List<Integer> toList (int[][] array) {
        for (int[] temp1 : array) {
            for (int temp2 : temp1) {
                list.add(temp2);
            }
        }
        System.out.println(list);
        return list;
    }

    /**
     * Convert List to array[][].
     * @param list - list.
     * @param rows - rows of returned array.
     * @return - array[][].
     */
    public int[][] toArray (List<Integer> list, int rows) {
        int count = 0;
        int countInRow;
        if (list.size() % rows != 0) {
            countInRow = list.size() / rows + 1;
        } else {
            countInRow = list.size() / rows;
        }

        Integer[] arrLine = new Integer[rows * countInRow];
        arrLine = list.toArray(arrLine);
        for (int i = 0; i < arrLine.length; i++) {
            if (arrLine[i] == null) {
                arrLine[i] = 0;
            }
        }

        int[][] arrSquare = new int[rows][countInRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < countInRow; j++) {
                arrSquare[i][j] = arrLine[count++];
            }
        }
        return arrSquare;
    }
}
/*
public class ConvertList {
    public static void main(String[] args) {
        Convert convert = new Convert();
        int[][] arrAdd = {{1, 2, 3}, {4, 5, 6}, {7}};
        convert.list = convert.toList(arrAdd);
        int[][] arr = convert.toArray(convert.list, 3);
        for (int tempArr[] : arr) {
            System.out.println(Arrays.toString(tempArr));
        }
    }
}
*/