package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.models.Brand;

import java.util.ArrayList;
import java.util.List;

/**
 * Brand DAO.
 */
public class BrandDAO implements DAO<Brand> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BrandDAO.class);
    /**
     * single DAO instance.
     */
    private static final BrandDAO INSTANCE = new BrandDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static BrandDAO getInstance() {
        return INSTANCE;
    }

    /**
     * get all
     * @return - all.
     */
    @Override
    public List<Brand> getAll() {
        Transaction transaction = null;
        List<Brand> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from Brand").list();
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
    public Brand getById(int id) {
        Transaction transaction = null;
        Brand brand = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            brand = session.get(Brand.class, id);
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return brand;
    }

    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    @Override
    public Brand getByName(String name) {
        Transaction transaction = null;
        Brand brand = null;
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery("from Brand as brand where brand.name=:name");
            query.setParameter("name", name);
            brand = (Brand) query.iterate().next();
            transaction.commit();
        } catch (HibernateException he) {
            LOG.error(he.getMessage(), he);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return brand;
    }
}
