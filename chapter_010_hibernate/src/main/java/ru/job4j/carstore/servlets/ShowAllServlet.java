package ru.job4j.carstore.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carstore.dao.OrderDAO;
import ru.job4j.carstore.models.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        Boolean lastDay = Boolean.valueOf(req.getParameter("lastDay"));
        Boolean withFoto = Boolean.valueOf(req.getParameter("withFoto"));
        String brand  = req.getParameter("brand");
        List<Order> list = OrderDAO.getINSTANCE().getAll();
        List<Order> newList;
        if (lastDay) {
            newList = OrderDAO.getINSTANCE().getByLastDay(list);
            list = newList;
        }
        if (withFoto) {
            newList = OrderDAO.getINSTANCE().getByWithFoto(list);
            list = newList;
        }
        if (!brand.equals("")) {
            newList = OrderDAO.getINSTANCE().getByBrand(list, brand);
            list = newList;
        }
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.append(mapper.writeValueAsString(list));
        writer.flush();
    }
}
