package ru.job4j;

/**
 * input interface.
 */
public interface Input {
    /**
     *
     * @param question - text message for user.
     * @return - user's choice.
     */
    String ask(String question);
}