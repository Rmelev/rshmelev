package ru.job4j.todo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todo.dao.TodoDAO;
import ru.job4j.todo.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * get all items from database.
 */
public class ShowAllServlet extends HttpServlet {
    /**
     *
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<Item> items = TodoDAO.getINSTANCE().getAll(Boolean.valueOf(req.getParameter("done")));
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(items));
        writer.append(mapper.writeValueAsString(items));
        writer.flush();
    }
}
