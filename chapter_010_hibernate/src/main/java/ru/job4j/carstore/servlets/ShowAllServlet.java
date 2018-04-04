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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * show all orders.
 */
public class ShowAllServlet extends HttpServlet {
    /**
     * get.
     * @param req - req.
     * @param resp - resp.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        Boolean lastDay = Boolean.valueOf(req.getParameter("lastDay"));
        Boolean withFoto = Boolean.valueOf(req.getParameter("withFoto"));
        String brand  = req.getParameter("brand");
        List<Order> list = OrderDAO.getINSTANCE().getAll();
        if (lastDay) {
            List<Order> newlist = new ArrayList<>();
            int month = LocalDateTime.now().getDayOfMonth();
            for (Order nextOrder : list) {
                if (nextOrder.getDate().toLocalDateTime().getDayOfMonth() == month) {
                    newlist.add(nextOrder);
                }
            }
            list = newlist;
        }
        if (withFoto) {
            List<Order> newlist = new ArrayList<>();
            for (Order nextOrder : list) {
                if (nextOrder.getImages().size() > 0) {
                    newlist.add(nextOrder);
                }
            }
            list = newlist;
        }
        if (!brand.equals("")) {

            List<Order> newlist = new ArrayList<>();
            for (Order nextOrder : list) {
                if (nextOrder.getCar().getModel().getBrand().getName().equals(brand)) {
                    newlist.add(nextOrder);
                }
            }
            list = newlist;
        }
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.append(mapper.writeValueAsString(list));
        writer.flush();
    }
}
