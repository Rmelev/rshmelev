package ru.job4j;

/**
 * abstract class for actions.
 */
abstract class BaseAction implements UserAction {
    /**
     * name of operation.
     */
    private String name;
    /**
     * name of operation.
     */
    private int key;

    /**
     * Constructor.
     * @param name - name of operation.
     * @param key - name of operation.
     */
    BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    /**
     * info about action for menu.
     * @return - string.
     */
    public String info() {
        return String.format("%s. %s", key, name);
    }
}