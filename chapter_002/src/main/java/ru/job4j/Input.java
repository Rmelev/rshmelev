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

    /**
     * overloading method.
     * @param question - question.
     * @param range - array of choices.
     * @return - user's choice.
     */
    int ask(String question, int[] range);
}