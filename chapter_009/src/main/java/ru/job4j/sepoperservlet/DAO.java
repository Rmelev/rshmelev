package ru.job4j.sepoperservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * common class for interaction with database.
 */
public class DAO {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DAO.class);
    /**
     * create table query.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users2512 "
            + " (id SERIAL PRIMARY KEY NOT NULL, "
            + "  user_name VARCHAR(255), "
            + "  user_login VARCHAR(255) UNIQUE, "
            + "  user_email VARCHAR(255), "
            + "  date_create TIMESTAMP, "
            + "  password VARCHAR(255), "
            + "  role VARCHAR(255), "
            + "  country VARCHAR(255), "
            + "  city VARCHAR(255))";
    /**
     * create record query.
     */
    private static final String CREATE_USER = "INSERT INTO users2512 "
            + " (user_name, user_login, user_email, date_create, password, role, country, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    /**
     * edit record query.
     */
    private static final String EDIT_USER = "UPDATE users2512 SET user_name=?, user_email=?,"
            + " date_create=?, password=?, role=?, country=?, city=? WHERE user_login=?";
    /**
     * get all records from table.
     */
    private static final String GET_USER = "SELECT * FROM users2512 ORDER BY id";
    /**
     * delete query.
     */
    private static final String DELETE_USER = "DELETE FROM users2512 WHERE user_login=?";

    /**
     * Properties.
     */
    private Properties prop = new Properties();

    /**
     * getter.
     * @return - properties.
     */
    public Properties getProp() {
        return prop;
    }

    /**
     * fill properties from properties file.
     */
    public void fillProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            prop.load(in);
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        }
    }

    /**
     * create table method.
     */
    public void dbCreate() {
        try (Statement st = conn.createStatement()) {
            st.execute(CREATE_TABLE);
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * connection to database.
     */
    private static Connection conn = UserStoreEnum.INSTANCE.dbConnection();

    /**
     * getter.
     * @return - connection.
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * database.
     */
    private static final DAO DATABASE = new DAO();

    /**
     * Getter.
     * @return - database.
     */
    public static DAO getDATABASE() {
        return DATABASE;
    }


    /**
     * create user.
     * @param user - user.
     */
    public void createUser(User user) {
        try (PreparedStatement ps = conn.prepareStatement(CREATE_USER)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getCountry());
            ps.setString(8, user.getCity());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * edit user.
     * @param user - user.
     */
    public void editUser(User user) {
        try (PreparedStatement ps = conn.prepareStatement(EDIT_USER)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getCountry());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getLogin());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * get all users.
     * @return - all users.
     */
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(GET_USER); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User(rs.getString("user_name"), rs.getString("user_login"),
                        rs.getString("user_email"), rs.getTimestamp("date_create"),
                        rs.getString("password"), rs.getString("role"),
                        rs.getString("country"), rs.getString("city"));
                userList.add(user);
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
    public void deleteUser(String login) {
        try (PreparedStatement ps = conn.prepareStatement(DELETE_USER)) {
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * is database have user with this login and password.
     * @param login - login.
     * @param password - password.
     * @return - true, if db have.
     */
    public boolean isCredentional(String login, String password) {
        boolean exists = false;
        for (User nextUser : getUsers()) {
            if (nextUser.getLogin().equals(login) && nextUser.getPassword().equals(password)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * getter.
     * @param login - login.
     * @param password - password.
     * @return - user's role.
     */
    public String getRole(String login, String password) {
        String role = "user";
        for (User nextUser : getUsers()) {
            if (nextUser.getLogin().equals(login) && nextUser.getPassword().equals(password)) {
                role = nextUser.getRole();
                break;
            }
        }
        return role;
    }

    /**
     * getter.
     * @param login - login.
     * @param password - password.
     * @return - user's name.
     */
    public String getName(String login, String password) {
        String name = "unknown";
        for (User nextUser : getUsers()) {
            if (nextUser.getLogin().equals(login) && nextUser.getPassword().equals(password)) {
                name = nextUser.getName();
                break;
            }
        }
        return name;
    }
}
