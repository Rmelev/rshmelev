package ru.job4j.chess.exceptions;

/**
 * exception.
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Constructor.
     * @param msg - message for user.
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}