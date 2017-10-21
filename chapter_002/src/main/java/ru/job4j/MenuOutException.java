package ru.job4j;

/**
 * exception class.
 */
public class MenuOutException extends RuntimeException {
    /**
     * @param msg - message.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}