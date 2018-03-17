package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.models.Transmission;

import java.util.ArrayList;
import java.util.List;
/**
 * transmission DAO.
 */
public class TransmissionDAO extends AbstractDAO<Transmission> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TransmissionDAO.class);
    /**
     * single DAO instance.
     */
    private static final TransmissionDAO INSTANCE = new TransmissionDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static TransmissionDAO getInstance() {
        return INSTANCE;
    }
    /**
     * get all.
     * @return - all.
     */
    @Override
    public List<Transmission> getAll() {
        Transaction transaction = null;
        List<Transmission> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from Transmission").list();
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
    public Transmission getById(int id) {
        Transaction transaction = null;
        Transmission transmission = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            transmission = session.get(Transmission.class, id);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return transmission;
    }
    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    @Override
    public Transmission getByName(String name) {
        Transaction transaction = null;
        Transmission transmission = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery(
                    "from Transmission as transmission where transmission.name=:name"
            );
            query.setParameter("name", name);
            transmission = (Transmission) query.iterate().next();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return transmission;
    }
}
