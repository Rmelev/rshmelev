package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.models.Order;

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
     * get all
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
    public Order getById(Integer id) {
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

//    public int getLastOrderId(int carId, int userId) {
//        Transaction transaction = null;
//        Order order = null;
//        int orderId = -1;
//        try (Session session = HibernateFactory.getFactory().openSession()) {
//            transaction = session.beginTransaction();
//            final Query query = session.createQuery("from Order as order where order.id_car=:carId and order.id_user=:userId");
//            query.setParameter("carId", carId);
//            query.setParameter("userId", userId);
//            order = (Order) query.iterate().next();
//            orderId = order.getId();
//            transaction.commit();
//        } catch (HibernateException he) {
//            LOG.error(he.getMessage(), he);
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//        return orderId;
//    }
}