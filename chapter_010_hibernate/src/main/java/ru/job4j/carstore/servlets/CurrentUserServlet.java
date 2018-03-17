package ru.job4j.carstore.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * return current log in user.
 */
public class CurrentUserServlet extends HttpServlet {
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
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String login = (String) session.getAttribute("login");
        if (login == null) {
            login = "Guest";
        }
        writer.append(mapper.writeValueAsString(login));
        writer.flush();
    }
}
