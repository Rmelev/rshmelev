package ru.job4j.carstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.Order;

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
}