package ru.job4j.chess.exceptions;

/**
 * exception.
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Constructor.
     * @param msg - message for user.
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }

}