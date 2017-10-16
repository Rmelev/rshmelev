package ru.job4j.condition;

/**
 * class, that is meaning point with x and y coordinates.
 */
public class Point {
    /**
     * coordinats of point.
     */
    private int x, y;

    /**
     * Constructor.
     * @param x - x coordinate.
     * @param y - y coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return - return value of x of object who called it.
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return - return value of y of object who called it.
     */
    public int getY() {
        return this.y;
    }

    /**
     * @param a - coefficient a of equation.
     * @param b - coefficient a of equation.
     * @return - return true if point belonf function.
     */
    public boolean is(int a, int b) {
        return a * getX() + b - getY() == 0;
    }
}