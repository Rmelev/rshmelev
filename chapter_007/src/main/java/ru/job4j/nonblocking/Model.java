package ru.job4j.nonblocking;

/**
 * template of element.
 */
public class Model {
    /**
     * name.
     */
    private String name;
    /**
     * number of changes in element.
     */
    private volatile int version;

    /**
     * Constructor.
     * @param name - name.
     */
    Model(String name) {
        this.name = name;
    }

    /**
     * setter.
     * @param name - name.
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * getter.
     * @return - name.
     */
    String getName() {
        return this.name;
    }

    /**
     * set next version after element change.
     */
    void incrementVersion() {
        this.version++;
    }

    /**
     * getter.
     * @return - version.
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * overrided toString().
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "Model{" + "name='" + name + '\'' + ", version=" + version + '}';
    }
}
