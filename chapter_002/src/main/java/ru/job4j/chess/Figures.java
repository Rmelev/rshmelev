package ru.job4j.chess;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * common abstract class for figures.
 */
abstract class Figures {
    /**
     * position of figure.
     */
    private Cell position;

    /**
     * Constuctor.
     * @param position - initializator cell.
     */
    Figures(Cell position) {
        this.position = position;
    }

    /**
     * abstract method for way array.
     *
     * @param dist - where we want to step.
     * @return - array of cells.
     * @throws ImpossibleMoveException - ImpossibleMoveException.
     */
    abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * method for realy step.
     * @param dist - we will stand.
     * @return - figure with coordinates of cell.
     */
    abstract Figures clone(Cell dist);
}