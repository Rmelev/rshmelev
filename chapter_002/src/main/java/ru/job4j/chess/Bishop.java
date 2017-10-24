package ru.job4j.chess;

import java.util.Arrays;

/**
 * Bishop figure.
 */
public class Bishop extends Figures {
    /**
     * Constractor.
     * @param position - position of figure on start.
     */
    Bishop(Cell position) {
        this.position = position;
    }
    /**
     * pick cells for way..
     * @param dist - where want to step.
     * @return - array of cells for the way.
     */
    public Cell[] way(Cell dist) {
        Cell[] arr = new Cell[7];
        int xCount = -1;
        String dir = null;
        for (int i = 1; i < 8; i++) {
            if (dist.x == (this.position.x - i) && dist.y == (this.position.y - i)) {
                xCount = i;
                dir = "lv";
            }
            if (dist.x == (this.position.x - i) && dist.y == (this.position.y + i)) {
                xCount = i;
                dir = "pv";
            }
            if (dist.x == (this.position.x + i) && dist.y == (this.position.y - i)) {
                xCount = i;
                dir = "ln";
            }
            if (dist.x == (this.position.x + i) && dist.y == (this.position.y + i)) {
                xCount = i;
                dir = "pn";
            }
        }

        if (dir == null) {
            return null;
        }

        if (dir.equals("lv")) {
            for (int i = 0; i < xCount; i++) {
                arr[i] = new Cell(position.x - i - 1, position.y - i - 1);
            }
        }
        if (dir.equals("pv")) {
            for (int i = 0; i < xCount; i++) {
                arr[i] = new Cell(position.x - i - 1, position.y + i + 1);
            }
        }
        if (dir.equals("ln")) {
            for (int i = 0; i < xCount; i++) {
                arr[i] = new Cell(position.x + i + 1, position.y - i - 1);
            }
        }
        if (dir.equals("pn")) {
            for (int i = 0; i < xCount; i++) {
                arr[i] = new Cell(position.x + i + 1, position.y + i + 1);
            }
        }
        return Arrays.copyOf(arr, xCount);
    }
}