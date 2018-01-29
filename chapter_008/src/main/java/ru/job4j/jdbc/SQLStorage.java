/*package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class SQLStorage {
    private static final Logger Log = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/java_a_to_z";
        String username = "romansmelev";
        String password = "";
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            st = conn.prepareStatement("INSERT INTO users (login, password, create_date) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,"барампампам");
            st.setString(2,"password");
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
                generatedKeys.close();
                System.out.println("Сюда дошел");
            }
            //ResultSet rs = st.executeQuery("SELECT * FROM users");
            //while (rs.next())
            //{
            //    System.out.println(
            //            String.format("%s %s", rs.getString("description"), rs.getTimestamp("create_date")));
            //}
            //rs.close();
            generatedKeys.close();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                    System.out.println("st.close();");
                } catch (SQLException e) {
                    Log.error(e.getMessage(), e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("conn.close();");
                } catch (SQLException e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }

    }
}
*/