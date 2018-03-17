package ru.job4j.carstore.models;

import java.util.Objects;
/**
 * car.
 */
public class User {
    /**
     * id.
     */
    private int id;
    /**
     * login.
     */
    private String login;
    /**
     * password.
     */
    private String password;
    /**
     * email.
     */
    private String email;
    /**
     * default constructor.
     */
    public User() {
    }
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
     * @return - login.
     */
    public String getLogin() {
        return login;
    }
    /**
     * @param login - login.
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * @return - password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password - password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return - email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email - email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param o - obj.
     * @return - true, if equals
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
        return id == user.id
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(email, user.email);
    }
    /**
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }
    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
