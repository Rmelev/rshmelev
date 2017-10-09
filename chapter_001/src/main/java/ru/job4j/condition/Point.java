package ru.job4j.condition;

public class Point {
    private int x, y;

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