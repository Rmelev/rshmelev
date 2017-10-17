package ru.job4j;

/**
 * One item - some fields of informarion about.
 */
public class Item {
    /**
     * ID of item.
     */
    private String id;
    /**
     * name of item.
     */
    private String name;
    /**
     * description of item.
     */
    private String desc;
    /**
     * date and time of item created.
     */
    private long created;
    /**
     * comments about item.
     */
    private String[] comments;

    /**
     * Constructor for item.
     * @param name - name.
     * @param desc - description.
     * @param created - date of created.
     */
    public Item(String name, String desc, long created) {
        this.id = Long.toString((long) (Math.random() * 10000) + System.currentTimeMillis());
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    /**
     * @param id - item ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param name - item name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param desc item description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @param created - date and time of item created.
     */
    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * @param comments - comments about item.
     */
    public void setComments(String[] comments) {
        this.comments = comments;
    }

    /**
     * @return - ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @return - name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return - description.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * @return date & time of created.
     */
    public long getCreated() {
        return this.created;
    }

    /**
     * @return - comments.
     */
    public String[] getComments() {
        return this.comments;
    }
}