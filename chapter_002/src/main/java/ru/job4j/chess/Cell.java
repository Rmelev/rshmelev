package ru.job4j.chess;

/**
 * cell of board.
 */
class Cell {
    /**
     * coordinate x on board.
     */
    int x;
    /**
     * coordinate y.
     */
    int y;

    /**
     * Constructor.
     * @param x - x.
     * @param y - y.
     */
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}