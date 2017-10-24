package ru.job4j.chess.exceptions;

/**
 * exception.
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Constructor.
     * @param msg - message for user.
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}