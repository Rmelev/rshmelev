package ru.job4j.chess;

//import ru.job4j.chess.exceptions.*;

abstract class Figures {
    abstract boolean specify(Cell cell1, Cell cell2);

    /**
     * превращает значение ячейки в 1-мерном массиве в значение ячейки в 2-мерном массиве.
     * @param step
     * @return
     */
    public int[] trans(int step) {
        int count = 0;
        int[][] rangeSq = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rangeSq[i][j] = count++;
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (rangeSq[i][j] == step) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }


}