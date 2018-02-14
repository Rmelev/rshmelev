package ru.job4j.crudservlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sepoperservlet.DAO;

/**
 * database operations.
 */
public class UserStore extends DAO {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);


    /**
     * Getter.
     * @return - conn.
     */
    public Connection getConn() {
        return super.getConn();
    }

    /**
     * static instance exists in one single instance.
     */
    private static UserStore instance;

    /**
     * private constructor (for ThreadSafe Singleton).
     */
    private UserStore() {
    }

    /**
     * for ThreadSafe Singleton implementation.
     * @return - single database for interactions.
     */
    public static UserStore getInstance() {
        if (instance == null) {
            synchronized (UserStore.class) {
                if (instance == null) {
                    instance = new UserStore();
                }
            }
        }
        return instance;
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
            super.setConn(DriverManager.getConnection(
                    getProp().getProperty("url"), getProp().getProperty("user"), getProp().getProperty("password", ""
                    )
            ));
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }
}
