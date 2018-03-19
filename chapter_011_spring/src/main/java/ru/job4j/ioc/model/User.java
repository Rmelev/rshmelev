package ru.job4j.ioc.model;

import java.util.Objects;

/**
 * user class.
 */
public class User {
    /**
     * id.
     */
    private int id;
    /**
     * name.
     */
    private String name;

    /**
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id - id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constrictor.
     */
    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * @return - String representation.
     */
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}