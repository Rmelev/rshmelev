package ru.job4j.crudservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * database interaction servlet.
 */
public class UserServlet extends HttpServlet {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    /**
     * database.
     */
    //private UserStore db = UserStore.getInstance();
    private UserStoreEnum db = UserStoreEnum.INSTANCE;

    /**
     * init start configuration.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        db.fillProperties();
        db.dbConnection();
        db.dbCreate();
    }

    /**
     * close connection to database.
     */
    @Override
    public void destroy() {
        try {
            db.getConn().close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * get all records from database.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (User nextUser : db.getUsers()) {
            writer.append("<p>" + nextUser.toString() + "</p>");
            writer.flush();
        }
        writer.close();
    }
    /**
     * create records into database.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        db.createUser(new User(req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                new Timestamp(System.currentTimeMillis())));
    }
    /**
     * opdate records into database.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        db.editUser(new User(req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                new Timestamp(System.currentTimeMillis())));
    }
    /**
     * delete records from database.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        db.deleteUser(req.getParameter("login"));
    }
}
