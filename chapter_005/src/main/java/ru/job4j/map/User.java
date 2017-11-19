package ru.job4j.map;

import java.util.Calendar;

/**
 * user info.
 */
public class User {
    /**
     * user's name.
     */
    private String name;
    /**
     * number of children.
     */
    private int children;
    /**
     * date of birthday.
     */
    private Calendar birthday;

    /**
     * Constructor.
     * @param name - name.
     * @param children - number of children.
     * @param birthday - date of birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * overrided toString().
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.get(Calendar.DATE)
                + "/" + birthday.get(Calendar.MONTH)
                + "/" + birthday.get(Calendar.YEAR)
                + '}';
    }
}