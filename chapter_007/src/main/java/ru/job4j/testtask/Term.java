package ru.job4j.testtask;

/**
 * abstruct class for Walls, Player, Monsters.
 */
public abstract class Term implements Runnable {
    /**
     * x position.
     */
    private int x;
    /**
     * y position.
     */
    private int y;

    /**
     * Constructor.
     * @param x - x.
     * @param y - y.
     */
    Term(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter.
     * @return x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * getter.
     * @return y.
     */
    public int getY() {
        return this.y;
    }
}
