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
 * credential user servlet.
 */
public class CredentialServlet extends HttpServlet {
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
        HttpSession session = req.getSession(false);
        Integer id;
        if (session == null) {
            id = -1;
        } else {
            if (session.getAttribute("user_id") == null) {
                System.out.println("Hi");
            }
            id = (Integer) session.getAttribute("user_id");
            if (id == null) {
                id = -1;
            }
        }
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.append(mapper.writeValueAsString(id));
        writer.flush();
    }
}
