package ru.job4j.crudservlet;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enum for thread-safe Singlton.
 */
public enum UserStoreEnum {
    /**
     * Singlton instance.
     */
    INSTANCE;

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);
    /**
     * Properties.
     */
    private Properties prop = new Properties();
    /**
     * Connection field.
     */
    private Connection conn;

    /**
     * Getter.
     * @return - conn.
     */
    public Connection getConn() {
        return this.conn;
    }

    /**
     * create table query.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users2512 "
            + " (id SERIAL PRIMARY KEY NOT NULL, "
            + "  user_name VARCHAR(255), "
            + "  user_login VARCHAR(255) UNIQUE, "
            + "  user_email VARCHAR(255), "
            + "  date_create TIMESTAMP)";
    /**
     * create record query.
     */
    private static final String CREATE_USER = "INSERT INTO users2512 "
            + " (user_name, user_login, user_email, date_create) VALUES (?, ?, ?, ?)";
    /**
     * edit record query.
     */
    private static final String EDIT_USER = "UPDATE users2512 SET user_name=?, user_email=?, date_create=? WHERE user_login=?";
    /**
     * get all records from table.
     */
    private static final String GET_USER = "SELECT * FROM users2512";
    /**
     * delete query.
     */
    private static final String DELETE_USER = "DELETE FROM users2512 WHERE user_login=?";

    /**
     * fill properties for database initialization.
     */
    void fillProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            prop.load(in);
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        }
    }

    /**
     * connection with db.
     */
    void dbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(
                    prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password", ""
                    )
            );
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * create table.
     */
    void dbCreate() {
        System.out.println(conn);
        try (Statement st = conn.createStatement()) {
            st.execute(CREATE_TABLE);
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * create user.
     * @param user - user.
     */
    void createUser(User user) {
        try (PreparedStatement ps = conn.prepareStatement(CREATE_USER)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * edit user.
     * @param user - user.
     */
    void editUser(User user) {
        try (PreparedStatement ps = conn.prepareStatement(EDIT_USER)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setString(4, user.getLogin());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * get all users.
     * @return - all users.
     */
    List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(GET_USER); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User(rs.getString("user_name"), rs.getString("user_login"),
                        rs.getString("user_email"), rs.getTimestamp("date_create"),
                        rs.getString("password"), rs.getString("role"));
                userList.add(user);
                System.out.println(user);
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return userList;
    }

    /**
     * delete user.
     * @param login - user's login.
     */
    void deleteUser(String login) {
        try (PreparedStatement ps = conn.prepareStatement(DELETE_USER)) {
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }
}
