package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.interfaces.Action;
import ru.job4j.carstore.interfaces.ActionGet;
import ru.job4j.carstore.interfaces.ActionListGet;

import java.lang.reflect.ParameterizedType;
import java.util.List;

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
     * reflection.
     */
    private Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /**
     * transaction, that return result.
     * @param actionGet - action for transact.
     * @return - entity.
     */
    T getTx(ActionGet actionGet) {
        T user = null;
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            user = (T) actionGet.execute(session);
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
     * transaction, that doesn't return result.
     * @param action - action for transact.
     */
    void voidTx(Action action) {
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            action.execute(session);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * transaction, that return list of entities.
     * @param actionListGet - action for transact.
     * @return - list of entities.
     */
    List<T> listGetTx(ActionListGet actionListGet) {
        List<T> users = null;
        Transaction transaction = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            users = (List<T>) actionListGet.execute(session);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    /**
     * add or update if exist.
     * @param t - entity for add.
     * @return - assigned id.
     */
    public int add(T t) {
        return (Integer) getTx(session -> session.save(t));
    }

    /**
     * get all entities.
     * @return - list of entities.
     */
    public List<T> getAll() {
        return listGetTx(session -> session.createQuery("from " + persistentClass.getSimpleName()).list());
    }

    /**
     * get by id.
     * @param id - entity id.
     * @return - entity.
     */
    public T getById(int id) {
        return getTx(session -> session.get(persistentClass, id));
    }

    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    public T getByName(String name) {
        Transaction transaction = null;
        T entity = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery("from " + persistentClass.getSimpleName() + " as entity where entity.name=:name");
            query.setParameter("name", name);
            entity = (T) query.iterate().next();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return entity;
    }

    /**
     * get by login.
     * @param login - login.
     * @return - entity.
     */
    public T getByLogin(String login) {
        Transaction transaction = null;
        T entity = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery("from " + persistentClass.getSimpleName() + " as entity where entity.login=:login");
            query.setParameter("login", login);
            entity = (T) query.iterate().next();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return entity;
    }

    /**
     * saveOrUpdate entity.
     * @param t - entity.
     */
    public void saveOrUpdate(T t) {
        voidTx(session -> session.saveOrUpdate(t));
    }

    /**
     * for clear table in tests.
     * @param query - query.
     */
    public void executeQuery(String query) {
        voidTx(session -> session.createNativeQuery(query).executeUpdate());
    }
}
