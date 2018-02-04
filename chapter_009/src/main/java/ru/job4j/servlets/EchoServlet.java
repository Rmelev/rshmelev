package ru.job4j.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * First servlet.
 */
public class EchoServlet extends HttpServlet {
    /**
     * Logger.
     */
    public static final Logger LOG = LoggerFactory.getLogger(EchoServlet.class);

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
        writer.append("Hello, World!");
        writer.flush();
    }

}
