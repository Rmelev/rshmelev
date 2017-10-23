package ru.job4j.chess;

//import ru.job4j.chess.exceptions.*;

abstract class Figures {
    abstract boolean specify(Cell cell1, Cell cell2);

    /**
     * положение в массиве (линейный от 1 до 64).
     */
    int x;

    boolean y;

    /**
     * Constructor.
     * @param x - place on the board.
     */
    public Figures(int x, boolean y) {
        this.x = x;
        this.y = y;
    }

    /**
     * default Constructor.
     */
    public Figures() {
    }

    /**
     * превращает значение ячейки в 1-мерном массиве в значение ячейки в 2-мерном массиве.
     * @param step - step.
     * @return - array.
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