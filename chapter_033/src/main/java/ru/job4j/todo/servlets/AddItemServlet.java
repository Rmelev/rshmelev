package ru.job4j.todo.servlets;

import ru.job4j.todo.dao.TodoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * add Item.
 */
public class AddItemServlet extends HttpServlet {
    /**
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDAO.getINSTANCE().addItem(req.getParameter("desc"));
    }
}
