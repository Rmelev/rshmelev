package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * simple element class.
 */
@XmlRootElement
public class Entry {
    /**
     * element value.
     */
    private int field;

    /**
     * getter.
     * @return - value.
     */
    public int getField() {
        return field;
    }

    /**
     * setter.
     * @param field - value.
     */
    @XmlElement
    public void setField(int field) {
        this.field = field;
    }
}
