package ru.job4j.carstore.servlets;

import ru.job4j.carstore.dao.*;
import ru.job4j.carstore.models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;


public class AddOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        CarDAO carDAO = new CarDAO();
        Car car = new Car();
        car.setColor(req.getParameter("color"));
        car.setModel(ModelDAO.getInstance().getByName(req.getParameter("model")));
        car.setBody(BodyDAO.getInstance().getByName(req.getParameter("body")));
        car.setTransmission(TransmissionDAO.getInstance().getByName(req.getParameter("transmission")));
        car.setEngine(EngineDAO.getInstance().getByName(req.getParameter("engine")));
        carDAO.save(car);

        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order();
        order.setDescription(req.getParameter("description"));
        order.setPrice(Integer.valueOf(req.getParameter("price")));
        order.setUser((User) session.getAttribute("user"));
        order.setSold(false);
        order.setDate(new Timestamp(System.currentTimeMillis()));
        order.setCar(car);
        int orderId = orderDAO.save(order);
        session.setAttribute("order_id", orderId);
    }
}
