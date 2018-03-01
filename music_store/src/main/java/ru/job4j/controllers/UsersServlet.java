package ru.job4j.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dal.StoreDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UsersServlet extends UserServletBase {
    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);

    /**
     * connection.
     */
    private Connection conn;

    /**
     * init start configuration.
     * @throws ServletException
     */
    @Override
    public void init() {
        conn = StoreDataSource.DATA_SOURCE.dbConnection();
        repository.dataBasesCreate();
    }

    /**
     * close connection to database.
     */
    @Override

    public void destroy() {
        try {
            conn.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * get().
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("users", repository.getAll());
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/views/users.jsp");
        view.forward(req, resp);
//        resp.setContentType("text/html");
//        doPost(req, resp);
    }
    /**
     * get().
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        req.setAttribute("users", repository.getAll());
//        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/views/users.jsp");
//        view.forward(req, resp);
    }
}
