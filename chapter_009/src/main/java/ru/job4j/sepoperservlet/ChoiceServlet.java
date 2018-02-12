package ru.job4j.sepoperservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudservlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        for (User nextUser : ds.getUsers()) {
            writer.append("<p>" + nextUser.toString() + "</p>");
        }
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>User's operation choice</title>"
                + "</head>"
                + "<body>"
                + "<a href='" + req.getContextPath() + "/create'>Add element</a>"
                + "<form action='" + req.getContextPath() + "/edit' method='post'>"
                + "Edit: login <input type='text' name='login'/> new name <input type='text' name='name'/> new email <input type='text' name='email'/>"
                + "<input type='submit'> <br/></form>"
                + "<form action='" + req.getContextPath() + "/delete' method='post'>"
                + "Delete: login <input type='text' name='login'/>"
                + "<input type='submit'> <br/>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
        writer.close();
    }
}
