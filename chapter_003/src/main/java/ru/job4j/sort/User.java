package ru.job4j.sort;

/**
 * one user information.
 */
public class User implements Comparable<User> {
    /**
     * user's name.
     */
    private String name;
    /**
     * user's age.
     */
    private Integer age;

    /**
     * Constructor.
     * @param name - user's name.
     * @param age - user's age.
     */
    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * getter.
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * getter.
     * @return - age.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * overrided toString().
     * @return - usability information.
     */
    @Override
    public String toString() {
        return "User{" + name
                + "  age:" + age + "}";
    }

    /**
     * overrided compareTo().
     * @param o - user for compare.
     * @return - who "bigger".
     */
    @Override
    public int compareTo(User o) {
        return this.age.equals(o.age) ? this.name.compareTo(o.name) : this.age.compareTo(o.age);
    }
}