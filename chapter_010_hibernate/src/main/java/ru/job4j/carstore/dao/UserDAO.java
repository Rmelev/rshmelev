package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.models.User;

import java.util.ArrayList;
import java.util.List;
/**
 * user DAO.
 */
public class UserDAO implements DAO<User> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
    /**
     * single DAO instance.
     */
    private static final UserDAO INSTANCE = new UserDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static UserDAO getInstance() {
        return INSTANCE;
    }

    public User add(final User body) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(body);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return body;
    }
    /**
     * get all
     * @return - all.
     */
    @Override
    public List<User> getAll() {
        Transaction transaction = null;
        List<User> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from User").list();
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
    public User getById(int id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }
    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    @Override
    public User getByName(String name) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery("from User as user where user.name=:name");
            query.setParameter("name", name);
            user = (User) query.iterate().next();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }
}
