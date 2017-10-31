package ru.job4j.convertation;

/**
 * One user info.
 */
public class User {
    /**
     * - user's id.
     */
    int id;
    /**
     * - user's name.
     */
    String name;
    /**
     * - user's city.
     */
    String city;

    /**
     * Constructor.
     * @param name - user's name.
     * @param city - user's city.
     */
    public User(String name, String city){
        this.name = name;
        this.city = city;
    }

    /**
     * overrided method toString().
     * @return - understandable note.
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}