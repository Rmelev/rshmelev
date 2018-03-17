package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.models.Model;

import java.util.ArrayList;
import java.util.List;
/**
 * engine DAO.
 */
public class ModelDAO extends AbstractDAO<Model> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ModelDAO.class);
    /**
     * single DAO instance.
     */
    private static final ModelDAO INSTANCE = new ModelDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static ModelDAO getInstance() {
        return INSTANCE;
    }
    /**
     * get all.
     * @return - all.
     */
    @Override
    public List<Model> getAll() {
        Transaction transaction = null;
        List<Model> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from Model").list();
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
    public Model getById(int id) {
        Transaction transaction = null;
        Model model = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            model = session.get(Model.class, id);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return model;
    }
    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    @Override
    public Model getByName(String name) {
        Transaction transaction = null;
        Model model = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery("from Model as model where model.name=:name");
            query.setParameter("name", name);
            model = (Model) query.iterate().next();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return model;
    }
}
