package ru.job4j.ioc.storage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.ioc.HibernateFactory;
import ru.job4j.ioc.interfaces.Action;
import ru.job4j.ioc.interfaces.ActionGet;

/**
 * common class for memory and jdbc storages.
 * @param <T> - entity.
 */
public abstract class AbstractStorage<T> {
    /**
     * LOGGER.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractStorage.class);

    private SessionFactory sessionFactory;

    public AbstractStorage() {
        this.sessionFactory = HibernateFactory.getFactory();
    }

    /**
     * transaction, that return result.
     * @param actionGet - action for transact.
     * @return - entity.
     */
    T getTx(ActionGet actionGet) {
        T user = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction = session.beginTransaction();
            user = (T) actionGet.execute(session);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * transaction, that doesn't return result.
     * @param action - action for transact.
     */
    void voidTx(Action action) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction = session.beginTransaction();
            action.execute(session);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

}
