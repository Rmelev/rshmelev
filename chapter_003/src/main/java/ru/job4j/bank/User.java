package ru.job4j.bank;

/**
 * bank client.
 */
public class User implements Comparable<User> {
    /**
     * client's name.
     */
    private String name;
    /**
     * client's number of passport.
     */
    private int passport;

    /**
     * Constructor.
     * @param name - client's name.
     * @param passport - client's number of passport.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * @param o - object.
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

        if (passport != user.passport) {
            return false;
        }
        return name.equals(user.name);
    }

    /**
     * @return - hashcode of object.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + passport;
        return result;
    }

    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "name: " + name + "  passport: " + passport;
    }

    /**
     * @param o - user to compare.
     * @return - more, less or equals zero, like super.compareTo().
     */
    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.name);
    }
}