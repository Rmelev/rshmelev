package ru.job4j.sepoperservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * data for get by JSON.
 */
public class JsonServlet extends HttpServlet {
    /**
     * map of country/city choice.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[{\"country\" : \"USA\", \"city\" : \"New York\"}, "
                + "{\"country\" : \"USA\", \"city\" : \"Seattle\"},"
                + "{\"country\" : \"USA\", \"city\" : \"San Francisco\"}, "
                + "{\"country\" : \"Russia\", \"city\" : \"Moscow\"}, "
                + "{\"country\" : \"Russia\", \"city\" : \"Leningrad\"}, "
                + "{\"country\" : \"Russia\", \"city\" : \"Ekaterinodar\"}]");
        writer.flush();
    }

}
