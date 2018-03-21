package ru.job4j.carstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * body DAO.
 */
public class OrderDAO extends AbstractDAO<Order> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(OrderDAO.class);
    /**
     * single DAO instance.
     */
    private static final OrderDAO INSTANCE = new OrderDAO();
    /**
     * Getter for instance.
     * @return instance.
     */
    public static OrderDAO getINSTANCE() {
        return INSTANCE;
    }
    /**
     * get all.
     * @return - all.
     */
    public List<Order> getAll() {
        return listGetTx(session -> session.createQuery("from Order as o order by o.id").list());
    }
    /**
     * get by id.
     * @param id - id
     * @return - entity.
     */
    @Override
    public Order getById(int id) {
        return super.getById(id);
    }

    /**
     * getByLastDay.
     * @param list - list.
     * @return - list of added orders in last day (from 00:00).
     */
    public List<Order> getByLastDay(List<Order> list) {
        List<Order> newlist = new ArrayList<>();
        int month = LocalDateTime.now().getDayOfMonth();
        for (Order nextOrder : list) {
            if (nextOrder.getDate().toLocalDateTime().getDayOfMonth() == month) {
                newlist.add(nextOrder);
            }
        }
        return newlist;
    }

    /**
     * with foto.
     * @param list - list.
     * @return - with foto.
     */
    public List<Order> getByWithFoto(List<Order> list) {
        List<Order> newlist = new ArrayList<>();
        for (Order nextOrder : list) {
            if (nextOrder.getImages().size() > 0) {
                newlist.add(nextOrder);
            }
        }
        return newlist;
    }

    /**
     * by brand.
     * @param list - list.
     * @param brand - brand.
     * @return - by brand.
     */
    public List<Order> getByBrand(List<Order> list, String brand) {
        List<Order> newlist = new ArrayList<>();
        for (Order nextOrder : list) {
            if (nextOrder.getCar().getModel().getBrand().getName().equals(brand)) {
                newlist.add(nextOrder);
            }
        }
        return newlist;
    }
}