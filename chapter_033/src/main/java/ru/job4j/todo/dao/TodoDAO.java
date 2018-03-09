package ru.job4j.todo.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.HibernateFactory;
import ru.job4j.todo.models.Item;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO.
 */
public class TodoDAO {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TodoDAO.class);
    /**
     * DAO instance.
     */
    private static final TodoDAO INSTANCE = new TodoDAO();

    /**
     * Constructor.
     */
    private TodoDAO() {
    }

    /**
     * getter.
     * @return - instance.
     */
    public static TodoDAO getINSTANCE() {
        return INSTANCE;
    }

    /**
     * add item into database.
     * @param desc - task description.
     */
    public void addItem(String desc) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Item item = new Item();
            item.setDesc(desc);
            item.setCreated(new Timestamp(System.currentTimeMillis()));
            session.save(item);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    /**
     * get all items from database.
     * @param done - status.
     * @return - list of choosen tasks.
     */
    public List<Item> getAll(boolean done) {
        Transaction transaction = null;
        List<Item> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = !done ? session.createQuery("from Item order by id").list()
                    : session.createQuery("from Item where done = true order by id").list();
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
     * update status of choosen item.
     * @param id - item id.
     * @param isDone - new id status.
     */
    public void updateStatus(int id, boolean isDone) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            Item item = session.get(Item.class, id);
            item.setDone(isDone);
            session.update(item);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
