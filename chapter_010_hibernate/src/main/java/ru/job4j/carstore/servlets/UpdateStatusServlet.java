package ru.job4j.carstore.servlets;

import ru.job4j.carstore.dao.OrderDAO;
import ru.job4j.carstore.models.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        Order order = OrderDAO.getINSTANCE().getById(Integer.valueOf(req.getParameter("id")));
        order.setSold(!Boolean.valueOf(req.getParameter("sold")));
        OrderDAO.getINSTANCE().saveOrUpdate(order);
        req.getParameter("id");
        req.getParameter("sold");
    }
}
