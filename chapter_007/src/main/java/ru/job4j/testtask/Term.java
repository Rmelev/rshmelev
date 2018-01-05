package ru.job4j.testtask;

public abstract class Term {
    private int x;
    private int y;
    Term(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
