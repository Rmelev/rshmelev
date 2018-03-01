package ru.job4j.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends UserServletBase {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        repository.create(req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("role"),
                req.getParameter("address"),
                req.getParameter("mtype1"),
                req.getParameter("mtype2"),
                req.getParameter("mtype3")
        );
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
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
