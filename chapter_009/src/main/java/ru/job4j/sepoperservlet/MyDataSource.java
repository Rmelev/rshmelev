//package ru.job4j.sepoperservlet;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import org.apache.commons.dbcp.BasicDataSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * apache connection pool connection.
// */
//public class MyDataSource extends DAO {
//    /**
//     * object for connection.
//     */
//    private static MyDataSource datasource;
//    /**
//     * parent class.
//     */
//    private BasicDataSource ds;
//    /**
//     * Logger.
//     */
//    private static final Logger LOG = LoggerFactory.getLogger(MyDataSource.class);
//
//    /**
//     * Constructor.
//     * @throws IOException - IOException.
//     * @throws SQLException - SQLException.
//     */
//    private MyDataSource() throws IOException, SQLException {
//        ds = new BasicDataSource();
//        fillProperties();
//        ds.setDriverClassName(getProp().getProperty("driver"));
//        ds.setUrl(getProp().getProperty("url"));
//        ds.setUsername(getProp().getProperty("user"));
//        ds.setPassword(getProp().getProperty("password", ""));
//    }
//
//    /**
//     * Thread safe Singlton.
//     * @return - single source state.
//     */
//    public static MyDataSource getInstance() {
//        if (datasource == null) {
//            synchronized (MyDataSource.class) {
//                if (datasource == null) {
//                    try {
//                        datasource = new MyDataSource();
//                    } catch (SQLException | IOException e) {
//                        LOG.error(e.getMessage(), e);
//                    }
//                }
//            }
//        }
//        return datasource;
//    }
//
//    /**
//     * @return - connection.
//     */
//    public Connection getConnection() {
//        try {
//            super.setConn(this.ds.getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return super.getConn();
//    }
//
//}
