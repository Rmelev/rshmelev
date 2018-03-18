package ru.job4j.ioc.model;

/**
 * user class.
 */
public class User {
    /**
     * name.
     */
    private String name;

    /**
     * Constrictor.
     * @param name - name.
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * @return - String representation.
     */
    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + '}';
    }
}