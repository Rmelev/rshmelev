package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.models.Body;

import java.util.ArrayList;
import java.util.List;

/**
 * body DAO.
 */
public class BodyDAO implements DAO<Body> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BodyDAO.class);
    /**
     * single DAO instance.
     */
    private static final BodyDAO INSTANCE = new BodyDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static BodyDAO getInstance() {
        return INSTANCE;
    }

    /**
     * add entity.
     * @param body - body.
     * @return - added entity.
     */
    public Body add(final Body body) {
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
    public List<Body> getAll() {
        Transaction transaction = null;
        List<Body> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from Body").list();
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
    public Body getById(int id) {
        Transaction transaction = null;
        Body body = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            body = session.get(Body.class, id);
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
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    @Override
    public Body getByName(String name) {
        Transaction transaction = null;
        Body body = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery("from Body as body where body.name=:name");
            query.setParameter("name", name);
            body = (Body) query.iterate().next();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return body;
    }
}
