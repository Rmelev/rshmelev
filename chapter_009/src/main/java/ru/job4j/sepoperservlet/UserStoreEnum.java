package ru.job4j.sepoperservlet;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.UserStore;

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
     * apache connection pool class.
     */
    private BasicDataSource ds;
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
     * Connection field.
     */
    private Connection conn;

    /**
     * Constructor.
     */
    UserStoreEnum() {
        ds = new BasicDataSource();
        fillProperties();
        ds.setDriverClassName(getProp().getProperty("driver"));
        ds.setUrl(getProp().getProperty("url"));
        ds.setUsername(getProp().getProperty("user"));
        ds.setPassword(getProp().getProperty("password", ""));
    }

    /**
     * connection with db.
     * @return - connection.
     */
    public Connection dbConnection() {
        try {
            conn = ds.getConnection();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return conn;
    }
}
