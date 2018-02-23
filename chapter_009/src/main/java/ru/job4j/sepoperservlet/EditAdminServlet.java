package ru.job4j.sepoperservlet;

import ru.job4j.crudservlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Timestamp;

/**
 * edit for admin role.
 */
public class EditAdminServlet extends ChoiceServlet {
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
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (isValid(name, login, email, password, req)) {
            this.getDs().editUser(new User(req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("email"),
                    new Timestamp(System.currentTimeMillis()),
                    req.getParameter("password"),
                    req.getParameter("role"),
                    req.getParameter("country"),
                    req.getParameter("city")));
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
    /**
     * validate user's input on server side.
     * @param name - name.
     * @param login - login.
     * @param email - email.
     * @param password - password.
     * @param req - request.
     * @return - true, if input data valid.
     */
    private boolean isValid(String name, String login, String email, String password, HttpServletRequest req) {
        boolean valid = true;
        if (name.equals("") || login.equals("") || email.equals("") || password.equals("")) {
            req.setAttribute("isEmpty", "Please, fill all input data");
            valid = false;
        }
        if (!email.contains("@")) {
            req.setAttribute("notEmail", "email isn't correct");
            valid = false;
        }
        return valid;
    }
}
