package ru.job4j.carstore.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * log out.
 */
public class LogOutServlet extends HttpServlet {
    /**
     * get.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        HttpSession session = req.getSession();
        session.invalidate();
//        session.setAttribute("user", null);
//        session.setAttribute("user_id", null);
//        session.setAttribute("login", null);
    }
}
