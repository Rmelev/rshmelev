package ru.job4j.chess;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * common abstract class for figures.
 */
abstract class Figures {
    /**
     * position of figure.
     */
    protected Cell position;

    /**
     * Constructor.
     * @param dist - we will stand.
     * @return - figure with coordinates of cell.
     */
    public Figures clone(Cell dist) {
        this.position.x = dist.x;
        this.position.y = dist.y;
        return this;
    }

    /**
     * abstract method for way array.
     * @param dist - where we want to step.
     * @return - array of cells.
     * @throws ImpossibleMoveException - ImpossibleMoveException.
     */
    abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
}