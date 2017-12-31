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
    private int version;

    /**
     * Constructor.
     * @param name - name.
     */
    public Model(String name) {
        this.name = name;
    }

    /**
     * setter.
     * @param name - name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter.
     * @return - name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * set next version after element change.
     */
    public void setVersion() {
        this.version = version + 1;
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
     * @return
     */
    @Override
    public String toString() {
        return "Model{" + "name='" + name + '\'' + ", version=" + version + '}';
    }
}
