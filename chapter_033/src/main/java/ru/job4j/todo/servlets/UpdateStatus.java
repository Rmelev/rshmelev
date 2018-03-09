package ru.job4j.todo.servlets;

import ru.job4j.todo.dao.TodoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * update item's status.
 */
public class UpdateStatus extends HttpServlet {
    /**
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDAO.getINSTANCE().updateStatus(Integer.valueOf(req.getParameter("id")), !Boolean.valueOf(req.getParameter("done")));
    }
}
