package ru.job4j.models;

import java.util.Objects;

/**
 * car transmission.
 */
public class Transmission {
    /**
     * id.
     */
    private int id;
    /**
     * name.
     */
    private String name;
    /**
     * default constructor.
     */
    public Transmission() {
    }
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
     * @param o - obj.
     * @return - true, if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transmission that = (Transmission) o;
        return id == that.id
                && Objects.equals(name, that.name);
    }
    /**
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "Transmission{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
