package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.models.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * engine DAO.
 */
public class EngineDAO extends AbstractDAO<Engine> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(EngineDAO.class);
    /**
     * single DAO instance.
     */
    private static final EngineDAO INSTANCE = new EngineDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static EngineDAO getInstance() {
        return INSTANCE;
    }

    /**
     * get all.
     * @return - all.
     */
    @Override
    public List<Engine> getAll() {
        Transaction transaction = null;
        List<Engine> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from Engine").list();
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
    public Engine getById(int id) {
        Transaction transaction = null;
        Engine engine = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            engine = session.get(Engine.class, id);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return engine;
    }
    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    @Override
    public Engine getByName(String name) {
        Transaction transaction = null;
        Engine engine = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery("from Engine as engine where engine.name=:name");
            query.setParameter("name", name);
            engine = (Engine) query.iterate().next();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return engine;
    }
}
