package ru.job4j.sepoperservlet;

import ru.job4j.crudservlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Edit user.
 */
public class EditUserServlet extends ChoiceServlet {
    /**
     * post().
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - ServletException.
     * @throws IOException - IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getDs().editUser(new User(req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                new Timestamp(System.currentTimeMillis())));
        resp.sendRedirect("./choice");
    }
}
