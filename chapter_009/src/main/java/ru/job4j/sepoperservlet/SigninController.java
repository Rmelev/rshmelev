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
 * servlet for sign in operation interface.
 */
public class SigninController extends HttpServlet {
    /**
     * Getter.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SigninController.class);

    /**
     * doGet.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }
    /**
     * doPost.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (MyDataSource.getInstance().isCredentional(login, password)) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
                session.setAttribute("role", MyDataSource.getInstance().getRole(login, password));
                session.setAttribute("name", MyDataSource.getInstance().getName(login, password));
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentional invalid");
            doGet(req, resp);
        }
    }
}
