package ru.job4j.chess;

/**
 * cell of board.
 */
public class Cell extends Figures{
    /**
     * положение в массиве (линейный от 1 до 64).
     */
    int x;

    /**
     * Занята или свободна. Если занята - то тип фигуры, если свободна - то null.
     */
    Figures statement;

    /**
     * Constructor.
     * @param x -  first index in array (board).
     * @param statement - who's there.
     */
    Cell(int x, Figures statement) {
        this.x = x;
        this.statement = statement;
    }

    boolean specify(Cell cellSource, Cell cellDist) {
        boolean flag = false;
        return flag;
    }
}