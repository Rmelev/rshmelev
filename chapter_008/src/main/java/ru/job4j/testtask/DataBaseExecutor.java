package ru.job4j.testtask;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * run SQL queries.
 */
public class DataBaseExecutor {
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(DataBaseExecutor.class);
    /**
     * connection variable.
     */
    private Connection conn;
    /**
     * create table.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS vacancies "
            + " (id INTEGER PRIMARY KEY NOT NULL, "
            + "  text VARCHAR(255), "
            + "  link VARCHAR(255), "
            + "  date_create TIMESTAMP)";
    /**
     * insert record.
     */
    private static final String INSERT_RECORD = "INSERT INTO vacancies VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";
    /**
     * the youngest record in table.
     */
    private static final String LAST_DATE = "SELECT date_create FROM vacancies ORDER BY date_create DESC LIMIT 1";
    /**
     * properties.
     */
    private Properties prop = new Properties();

    /**
     * fill prop from db.properties.
     */
    void fillProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            prop.load(in);
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        }
    }

    /**
     * create table if not exist.
     */
    void createTable() {
        Statement st = null;
        try {
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password", ""));
            st = conn.createStatement();
            st.execute(CREATE_TABLE);
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * add record to db.
     * @param id - record id.
     * @param textVacancy - text of vacancy.
     * @param linkVacancy - link of vacancy.
     * @param dateCreate - date of created.
     */
    void addToDB(int id, String textVacancy, String linkVacancy, Timestamp dateCreate) {
        try (PreparedStatement ps = conn.prepareStatement(INSERT_RECORD)) {
            ps.setInt(1, id);
            ps.setString(2, textVacancy);
            ps.setString(3, linkVacancy);
            ps.setTimestamp(4, dateCreate);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * @return - the youngest record in table.
     */
    Timestamp topPositionDate() {
        Timestamp ts = null;
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(LAST_DATE);
            if (rs.next()) {
                ts = rs.getTimestamp("date_create");
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return ts;
    }
}
