package ru.job4j.convertation;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

/**
 * Convert array[][] to List and back.
 */
class ConvertList {
    /**
     * list to add elems from int[][] array.
     */
    private List<Integer> list = new ArrayList<>();
    //List<int[]> listIntArr = new ArrayList<>();

    /**
     * getter for list.
     * @return - list.
     */
    public List<Integer> getList() {
        return list;
    }

    /**
     * Convert array[][] to List.
     * @param array - array.
     * @return - list.
     */
    public List<Integer> toList(int[][] array) {
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
    public int[][] toArray(List<Integer> list, int rows) {
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

    /**
     * Convert List<int[]> to List<Integer>.
     * @param list - List<int[]>.
     * @return - List<Integer>.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] intArr : list) {
            for (int intEl : intArr) {
                result.add(intEl);
            }
        }
        return result;
    }
}
/*
public class ConvertList {
    public static void main(String[] args) {
        Convert convertOb = new Convert();
        int[][] arrAdd = {{1, 2, 3}, {4, 5, 6}, {7}};
        convertOb.list = convertOb.toList(arrAdd);
        int[][] arr = convertOb.toArray(convertOb.list, 3);
        for (int tempArr[] : arr) {
            System.out.println(Arrays.toString(tempArr));
        }
        convertOb.listIntArr.add(new int[]{1, 2});
        convertOb.listIntArr.add(new int[]{3, 4, 5, 6});
        convertOb.listIntArr.add(new int[]{7, 8, 9, 10, 11, 12});
        List<Integer> result = convertOb.convert(convertOb.listIntArr);
        System.out.println(result);
    }
}
*/