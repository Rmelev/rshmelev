package ru.job4j.models;

import java.util.Objects;

/**
 * car engine.
 */
public class Engine {
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
    public Engine() {
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
        Engine engine = (Engine) o;
        return id == engine.id
                && Objects.equals(name, engine.name);
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
        return "Engine{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
