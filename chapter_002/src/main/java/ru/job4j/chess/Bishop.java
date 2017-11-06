package ru.job4j.chess;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

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
        int yCount = Math.abs(y - dist.getY());
        if (x - dist.getX() > 0 && y - dist.getY() > 0 && xCount == yCount) {
            arr = wayHelp(dist, arr, xCount, 1, 1);
        } else if (x - dist.getX() > 0 && y - dist.getY() < 0 && xCount == yCount) {
            arr = wayHelp(dist, arr, xCount, 1, -1);
        } else if (x - dist.getX() < 0 && y - dist.getY() < 0 && xCount == yCount) {
            arr = wayHelp(dist, arr, xCount, -1, 1);
        } else if (x - dist.getX() < 0 && y - dist.getY() > 0 && xCount == yCount) {
            arr = wayHelp(dist, arr, xCount, -1, -1);
        } else {
            throw new ImpossibleMoveException("Туда не ходит. ");
        }
        return Arrays.copyOf(arr, xCount);
    }

    /**
     * helper method for way.
     * @param dist - Cell listination.
     * @param arr - array for filling.
     * @param xCount - absolute difference between source cell & distance cell.
     * @param k1 - 1st parametr of way direction.
     * @param k2 - 2nd parametr of way direction.
     * @return - array of way cells.
     */
    public Cell[] wayHelp(Cell dist, Cell[] arr, int xCount, int k1, int k2) {
        for (int i = 0; i < xCount; i++) {
            arr[i] = new Cell(dist.getX() + i * k1, dist.getY() + i * k2);
        }
        return arr;
    }
}