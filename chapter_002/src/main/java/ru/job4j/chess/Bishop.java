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
        int xCount = Math.abs(x - dist.getX());
        boolean dir = false;
        int k1 = 1;
        int k2 = 1;
        for (int i = 1; i < 8; i++) {
            if (dist.getX() == x - i && dist.getY() == y - i) {
                dir = true;
                break;
            }

            if (dist.getX() == x - i && dist.getY() == y + i) {
                k1 = 1;
                k2 = -1;
                dir = true;
                break;
            }

            if (dist.getX() == x + i && dist.getY() == y - i) {
                k1 = -1;
                k2 = 1;
                dir = true;
                break;
            }

            if (dist.getX() == x + i && dist.getY() == y + i) {
                k1 = -1;
                k2 = -1;
                dir = true;
                break;
            }
        }

        if (!dir) {
            return null;
        }

        for (int i = 0; i < xCount; i++) {
            arr[i] = new Cell(dist.getX() + i * k1, dist.getY() + i * k2);
        }

        return Arrays.copyOf(arr, xCount);
    }
}