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
     * @throws IOException - erxp.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        for (String login : this.users) {
            sb.append("<tr><td>" + login + "  " + Math.random() + "</td></tr>");
        }
        sb.append("</table>");
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Title</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/echo' method='post'>"
                + "Name : <input type='text' name='login'/>"
                + "<input type='submit'>"
                + "</form>"
                + "<br/>"
                + sb.toString()
                + "</body>"
                + "</html>");
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
