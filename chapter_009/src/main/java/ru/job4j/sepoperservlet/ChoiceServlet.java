package ru.job4j.sepoperservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * choice operation with database.
 */
public class ChoiceServlet extends HttpServlet {
    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ChoiceServlet.class);

    /**
     * getter.
     * @return - ds.
     */
    public MyDataSource getDs() {
        return ds;
    }

    /**
     * object for apache connection pool connection.
     */
    private MyDataSource ds = MyDataSource.getInstance();

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
        conn = ds.getConnection();
        ds.dbCreate();
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("users", MyDataSource.getInstance().getUsers());
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/views/choice.jsp");
        view.forward(req, resp);
    }
    /**
     * get().
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("users", MyDataSource.getInstance().getUsers());
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/views/choice.jsp");
        view.forward(req, resp);
    }
}
