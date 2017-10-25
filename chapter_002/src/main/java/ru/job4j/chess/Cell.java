package ru.job4j.chess;

/**
 * cell of board.
 */
class Cell {
    /**
     * coordinate x on board.
     */
    private int x;
    /**
     * coordinate y.
     */
    private int y;

    /**
     * Constructor.
     * @param x - x.
     * @param y - y.
     */
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter.
     * @return - x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * getter.
     * @return - x.
     */
    public int getY() {
        return this.y;
    }
}