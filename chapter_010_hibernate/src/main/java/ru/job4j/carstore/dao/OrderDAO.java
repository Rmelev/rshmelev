package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
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
        Transaction transaction = null;
        List<Order> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from Order as o order by o.id").list();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return list;
    }
    /**
     * get by id.
     * @param id - id
     * @return - entity.
     */
    @Override
    public Order getById(int id) {
        Transaction transaction = null;
        Order order = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            order = session.get(Order.class, id);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return order;
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

    /**
     * only for override parent method.
     * @param name - entity name.
     * @return - null.
     */
    @Override
    public Order getByName(String name) {
        return null;
    }
}