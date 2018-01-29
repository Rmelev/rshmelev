package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Collection of simple elements.
 */
@XmlRootElement
public class Entries {
    /**
     * entry collection.
     */
    private ArrayList<Entry> entry;

    /**
     * getter.
     * @return - value.
     */
    public ArrayList<Entry> getEntry() {
        return entry;
    }

    /**
     * setter.
     * @param entry - value.
     */
    @XmlElement
    public void setEntry(ArrayList<Entry> entry) {
        this.entry = entry;
    }
}
