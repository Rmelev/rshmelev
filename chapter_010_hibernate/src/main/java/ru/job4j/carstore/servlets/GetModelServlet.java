package ru.job4j.carstore.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carstore.dao.ModelDAO;
import ru.job4j.carstore.models.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GetModelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        String brand = req.getParameter("brand");
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        List<Model> list = ModelDAO.getInstance().getAll();
        List<Model> newlist = new ArrayList<>();
        for (Model next : list) {
            if (next.getBrand().getName().equals(brand)) {
                newlist.add(next);
            }
        }
        writer.append(mapper.writeValueAsString(newlist));
        writer.flush();
    }
}
