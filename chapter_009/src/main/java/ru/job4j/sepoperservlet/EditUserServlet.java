package ru.job4j.sepoperservlet;

import ru.job4j.crudservlet.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        synchronized (session) {
            this.getDs().editUser(new User(req.getParameter("name"),
                    (String) session.getAttribute("login"),
                    req.getParameter("email"),
                    new Timestamp(System.currentTimeMillis()),
                    req.getParameter("password"),
                    (String) session.getAttribute("role")));
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
