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

    /*
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

    /**
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    /**
     *
     * @param o - object for comparing.
     * @return - true, if equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (children != user.children) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }
}