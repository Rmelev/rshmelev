package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;

/**
 * add complex entities.
 * @param <T> - type of entity.
 */
public abstract class AbstractDAO<T> implements DAO<T> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAO.class);

    /**
     * add or update if exist.
     * @param t - entity for add.
     * @return - assigned id.
     */
    public int add(T t) {
        int id = -1;
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            id = (Integer) session.save(t);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return id;
    }

    /**
     * saveOrUpdate entity.
     * @param t - entity.
     */
    public void saveOrUpdate(T t) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * for clear table in tests.
     * @param query - query.
     */
    public void executeQuery(String query) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
