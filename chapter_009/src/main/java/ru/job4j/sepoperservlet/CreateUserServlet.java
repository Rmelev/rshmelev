package ru.job4j.sepoperservlet;

import ru.job4j.crudservlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * create user.
 */
public class CreateUserServlet extends ChoiceServlet {
    /**
     * post().
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.getDs().createUser(new User(req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                new Timestamp(System.currentTimeMillis())));
        resp.sendRedirect("./choice");
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
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>User's operation choice</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/create' method='post'>"
                + "Add: login <input type='text' name='name'/> name <input type='text' name='login'/> email <input type='text' name='email'/>"
                + "<input type='submit'> <br/>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
        writer.close();
    }
}
