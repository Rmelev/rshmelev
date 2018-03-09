package ru.job4j.todo.models;

import java.sql.Timestamp;

/**
 * Item entity.
 */
public class Item {
    /**
     * id.
     */
    private int id;
    /**
     * description.
     */
    private String desc;
    /**
     * created date.
     */
    private Timestamp created;
    /**
     * true, if task done.
     */
    private boolean done;

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
     * @return - desc.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc - desc.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return - create date.
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * @param created - create date.
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * @return - current status.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done - new status.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", done=" + done
                + '}';
    }
}
