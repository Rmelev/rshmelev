package ru.job4j.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * First servlet.
 */
public class EchoServlet extends HttpServlet {
    /**
     * Logger.
     */
    public static final Logger LOG = LoggerFactory.getLogger(EchoServlet.class);
    /**
     * list of users.
     */
    private List<String> users = new CopyOnWriteArrayList<>();

    /**
     * doGet.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exp.
     * @throws IOException - exp.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("Hello, World!" + this.users);
        writer.flush();
    }

    /**
     * doPost.
     * @param req - request.
     * @param resp - response.
     * @throws ServletException - exp.
     * @throws IOException - exp.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        users.add(login);
        doGet(req, resp);
    }
}
