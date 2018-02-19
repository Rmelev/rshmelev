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
     * user's password.
     */
    private String password;
    /**
     * user's role.
     */
    private String role;

    /**
     *
     * @param name - name.
     * @param login - login.
     * @param email - email.
     * @param createDate - create date.
     * @param password - password.
     * @param role - role.
     */
    public User(String name, String login, String email, Timestamp createDate, String password, String role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
        this.role = role;
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
     * Getter.
     * @return - password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter.
     * @return - role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter.
     * @param role - role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "name=" + name
                + ", login=" + login
                + ", email=" + email
                + ", createDate=" + createDate
                + ", role=" + role + '}';
    }
}
