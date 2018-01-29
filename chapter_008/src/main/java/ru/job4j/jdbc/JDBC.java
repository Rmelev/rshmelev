package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * JDBC operations.
 */
public class JDBC {
    /**
     * connection.
     */
    private Connection conn;
    /**
     * entry collection.
     */
    private Entries entries = new Entries();
    /**
     * path to connection with database.
     */
    private String dbConnect;
    /**
     * number of entries.
     */
    private int n;

    /**
     * Constructor.
     * @param dbConnect - path to connection with database.
     * @param n - number of entries.
     */
    public JDBC(String dbConnect, int n) {
        this.dbConnect = dbConnect;
        this.n = n;
    }

    /**
     * create table query.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS numbers "
            + " (num INTEGER PRIMARY KEY NOT NULL)";
    /**
     * fill table query.
     */
    private static final String FILL_TABLE = "INSERT INTO numbers VALUES (?)";
    /**
     * get all records from table.
     */
    private static final String GET_RECORDS = "SELECT num FROM numbers";
    /**
     * clear table.
     */
    private static final String DELETE_FROM_TABLE = "DELETE FROM numbers";
    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(JDBC.class);

    /**
     * create table.
     * @throws SQLException - SQLException.
     */
    void createTable() throws SQLException {
        long start = System.currentTimeMillis();
        conn = DriverManager.getConnection(dbConnect);
        try (Statement st = conn.createStatement()) {
            st.execute(CREATE_TABLE);
            st.execute(DELETE_FROM_TABLE);
        }
        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("createTable: " + total);
    }

    /**
     * fill table.
     */
    void fillTable() {
        long start = System.currentTimeMillis();
        try (PreparedStatement fillStatement = conn.prepareStatement(FILL_TABLE)) {
            conn.setAutoCommit(false);
            for (int i = 1; i < n + 1; i++) {
                fillStatement.setInt(1, i);
                fillStatement.addBatch();
            }
            fillStatement.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        }
        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("fillTable: " + total);
    }

    /**
     * get element from table and input to collection.
     * @return - entry collection.
     */
    Entries tableToEntryColl() {
        long start = System.currentTimeMillis();
        ArrayList<Entry> entry = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(GET_RECORDS); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Entry ent = new Entry();
                ent.setField(rs.getInt("num"));
                entry.add(ent);
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        entries.setEntry(entry);
        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("tableToXML: " + total);
        return entries;
    }
}
