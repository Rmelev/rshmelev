package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * class Tracker is user interface of items array.
 */
public class Tracker {
    /**
     * Log exceptions.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);
    /**
     * create table if not exist.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS tracker (id SERIAL PRIMARY KEY,"
            + " item_name VARCHAR(100), item_desc VARCHAR(1000), created TIMESTAMP)";
    /**
     * get all records.
     */
    private static final String GET_RECORDS = "SELECT * FROM tracker ORDER BY id";
    /**
     * insert record.
     */
    private static final String INSERT = "INSERT INTO tracker (item_name, item_desc, created) VALUES (?, ?, ?)";
    /**
     * update record.
     */
    private static final String UPDATE = "UPDATE tracker SET item_name = ?, item_desc = ?, created = ? WHERE id = ?";
    /**
     * delete record.
     */
    private static final String DELETE = "DELETE FROM tracker WHERE id = ?";
    /**
     * find record, using column "name".
     */
    private static final String FIND_BY_NAME = "SELECT * FROM tracker WHERE item_name = ?";
    /**
     * find record, using column "id".
     */
    private static final String FIND_BY_ID = "SELECT * FROM tracker WHERE id = ?";
    /**
     * path to properties file.
     */
    private static final String PATH = "./chapter_002/src/main/java/ru/job4j/db.properties";
    /**
     * properties.
     */
    private Properties prop = new Properties();
    /**
     * connection.
     */
    private Connection conn = null;

    /**
     * input connection data in Properties object.
     */
    void fillProperites() {
        try (InputStream in = Files.newInputStream(Paths.get(PATH))) {
            prop.load(in);
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        }
    }

    /**
     * connection with database.
     */
    void dbConnection() {
        try {
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password", "");
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * create table if not exist.
     */
    void dbCreateIfNotExist() {
        try (Statement st = conn.createStatement()) {
            st.execute(CREATE_TABLE);
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * close connection after all user's operations.
     */
    void connClose() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * read and add to list of all current items in table.
     * @return - list of all current items in table.
     */
    public List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(GET_RECORDS); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Item item = new Item(
                        rs.getString("item_name"),
                        rs.getString("item_desc"),
                        rs.getTimestamp("created").getTime()
                );
                item.setId(rs.getInt("id"));
                list.add(item);
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return list;
    }

    /**
     * add item in table.
     * @param item - added item.
     */
    public void add(Item item) {
        try (PreparedStatement ps = conn.prepareStatement(INSERT)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setTimestamp(3, new Timestamp(item.getCreated()));
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * update item in table.
     * @param item - updated item.
     */
    public void update(Item item) {
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setTimestamp(3, new Timestamp(item.getCreated()));
            ps.setInt(4, item.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * delete item from table.
     * @param id - id of deleted item.
     */
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }

    /**
     * find record, using column "name".
     * @param name - name of item.
     * @return - list of items with identical names.
     */
    public List<Item> findByName(String name) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(FIND_BY_NAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(String.format("%s  %s  %s  %s", rs.getInt("id"),
                            rs.getString("item_name"),
                            rs.getString("item_desc"),
                            rs.getTimestamp("created")));
                }
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
        return list;
    }

    /**
     * find record, using column "id".
     * @param id - id of item.
     */
    public void findById(int id) {
        try (PreparedStatement ps = conn.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                System.out.println(String.format("%s  %s  %s  %s", rs.getInt("id"),
                        rs.getString("item_name"),
                        rs.getString("item_desc"),
                        rs.getTimestamp("created")));
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle);
        }
    }
}

/**
 * array of items.
 */
//private List<Item> items = new ArrayList<>();

/**
 * getter for items.
 * @return - items.
 */
//public List<Item> getItems() {
//    return items;
//}

/**
 * @param item - item, which need to update.
 */
/*public void update(Item item) {
       for (Item itemTemp : items) {
           if (itemTemp != null && itemTemp.getId().equals(item.getId())) {
               itemTemp.setName(item.getName());
               itemTemp.setDesc(item.getDesc());
               itemTemp.setCreated(item.getCreated());
           }
       }
   }*/

/**
 * @param name - item name, which need to find.
 * @return - found item.
 */
    /*public ArrayList<Item> findByName(String name) {
        ArrayList<Item> itemName = new ArrayList<>();
        for (Item itemTemp : items) {
            if (itemTemp != null && itemTemp.getName().equals(name)) {
                itemName.add(itemTemp);
            }
        }
        return itemName;
    }*/

/**
 * @param id item name, which need to find.
 * @return - found item.
 */
    /*public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }*/