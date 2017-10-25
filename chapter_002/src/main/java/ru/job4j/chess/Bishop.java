package ru.job4j.chess;

import java.util.Arrays;

/**
 * Bishop figure.
 */
public class Bishop extends Figures {
    /**
     * coordinate x.
     */
    private int x;
    /**
     * coord y.
     */
    private int y;

    /**
     * Constructor.
     * @param cell - initializattor cell.
     */
    Bishop(Cell cell) {
        super(cell);
        this.x = cell.getX();
        this.y = cell.getY();
    }

    /**
     * method for real move.
     * @param dist - we will stand.
     * @return - figure with new position.
     */
    public Figures clone(Cell dist) {
        return new Bishop(dist);
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
            if (dist.getX() == x - i && dist.getY() == y - i) {
                xCount = i;
                dir = "lv";
            }
            if (dist.getX() == x - i && dist.getY() == y + i) {
                xCount = i;
                dir = "pv";
            }
            if (dist.getX() == x + i && dist.getY() == y - i) {
                xCount = i;
                dir = "ln";
            }
            if (dist.getX() == x + i && dist.getY() == y + i) {
                xCount = i;
                dir = "pn";
            }
        }

        if (dir == null) {
            return null;
        }

        if (dir.equals("lv")) {
            for (int i = 0; i < xCount; i++) {
                arr[i] = new Cell(x - i - 1, y - i - 1);
            }
        }

        if (dir.equals("pv")) {
            for (int i = 0; i < xCount; i++) {
                arr[i] = new Cell(x - i - 1, y + i + 1);
            }
        }

        if (dir.equals("ln")) {
            for (int i = 0; i < xCount; i++) {
                arr[i] = new Cell(x + i + 1, y - i - 1);
            }
        }

        if (dir.equals("pn")) {
            for (int i = 0; i < xCount; i++) {
                arr[i] = new Cell(x + i + 1, y + i + 1);
            }
        }
        return Arrays.copyOf(arr, xCount);
    }
}