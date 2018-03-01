package ru.job4j.dal;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public enum StoreDataSource {

    DATA_SOURCE;

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(StoreDataSource.class);

    /**
     * apache connection pool class.
     */
    BasicDataSource source;

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
     * Connection field.
     */
    private Connection conn;

    /**
     * Constructor.
     */
    StoreDataSource() {
        source = new BasicDataSource();
        fillProperties();
        source.setDriverClassName(getProp().getProperty("driver"));
        source.setUrl(getProp().getProperty("url"));
        source.setUsername(getProp().getProperty("user"));
        source.setPassword(getProp().getProperty("password", ""));
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
     * connection with db.
     * @return - connection.
     */
    public Connection dbConnection() {
        try {
            conn = source.getConnection();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return conn;
    }

    public Connection getConn() {
        return conn;
    }
}
