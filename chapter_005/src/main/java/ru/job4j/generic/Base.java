package ru.job4j.generic;

/**
 * common class of store elements.
 */
abstract class Base {
    /**
     * id of element.
     */
    private String id;

    /**
     * getter.
     * @return - id.
     */
    String getId() {
        return id;
    }

    /**
     * setter.
     * @param id - setting id.
     */
    void setId(String id) {
        this.id = id;
    }

    /**
     * @return - element id.
     */
    @Override
    public String toString() {
        return getId();
    }
}