package ru.job4j.nonblocking;

/**
 * Exception.
 */
public class OptimisticException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public OptimisticException() {
        super("Value was changed. Dangerous operation!");
    }
}
