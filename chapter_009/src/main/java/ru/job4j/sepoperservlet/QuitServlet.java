package ru.job4j.sepoperservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * servlet for log out.
 */
public class QuitServlet extends HttpServlet {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(QuitServlet.class);

    /**
     * doGet.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }
}
