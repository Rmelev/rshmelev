package ru.job4j.crudservlet;

import java.sql.Timestamp;

/**
 * user (for database record).
 */
public class User {
    /**
     * name.
     */
    private String name;
    /**
     * login.
     */
    private String login;
    /**
     * email.
     */
    private String email;

    /**
     * user was created.
     */
    private Timestamp createDate;

    /**
     * Constructor.
     * @param name - name.
     * @param login - login.
     * @param email - email.
     * @param createDate - date.
     */
    public User(String name, String login, String email, Timestamp createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * Getter.
     * @return - name.
     */
    public String getName() {
        return name;
    }
    /**
     * Getter.
     * @return - login.
     */
    public String getLogin() {
        return login;
    }
    /**
     * Getter.
     * @return - email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter.
     * @return - create date.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "name=" + name
                + ", login=" + login
                + ", email=" + email
                + ", createDate=" + createDate + '}';
    }
}
