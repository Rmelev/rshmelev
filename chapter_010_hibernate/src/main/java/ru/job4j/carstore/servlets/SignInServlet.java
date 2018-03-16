package ru.job4j.carstore.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carstore.dao.UserDAO;
import ru.job4j.carstore.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        HttpSession session = req.getSession();
        session.setAttribute("user", null);
        User validUser = null;
        List<User> list = UserDAO.getInstance().getAll();
        for (User next : list) {
            if (next.getLogin().equals(req.getParameter("login")) &&
                    next.getPassword().equals(req.getParameter("password"))) {
                validUser = next;
                session.setAttribute("user", next);
                session.setAttribute("user_id", next.getId());
                session.setAttribute("login", next.getLogin());
                break;
            }
        }
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.append(mapper.writeValueAsString(validUser));
        writer.flush();
    }
}
