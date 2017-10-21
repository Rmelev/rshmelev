package ru.job4j;

/**
 * interface.
 */
public interface UserAction {
    /**
     * key.
     * @return nuber of key.
     */
    int key();

    /**
     * action.
     * @param input - input.
     * @param tracker - tracker.
     */
    void execute(Input input, Tracker tracker);

    /**
     * info about action.
     * @return - info.
     */
    String info();
}