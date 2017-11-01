package ru.job4j.convertation;

/**
 * One user info.
 */
public class User {
    /**
     * - user's id.
     */
    private int id;
    /**
     * - user's name.
     */
    private String name;
    /**
     * - user's city.
     */
    private String city;

    /**
     * Constructor.
     * @param name - user's name.
     * @param city - user's city.
     */
    public User(String name, String city) {
        id = (int) System.currentTimeMillis();
        this.name = name;
        this.city = city;
    }

    /**
     * getter.
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     * overrided method toString().
     * @return - understandable note.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", city='" + city + '\''
                + '}';
    }
}