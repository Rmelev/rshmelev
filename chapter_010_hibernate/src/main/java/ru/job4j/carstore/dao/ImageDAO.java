package ru.job4j.carstore.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.HibernateFactory;
import ru.job4j.carstore.models.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * image dao.
 */
public class ImageDAO extends AbstractDAO<Image> {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ImageDAO.class);
    /**
     * single DAO instance.
     */
    private static final ImageDAO INSTANCE = new ImageDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static ImageDAO getInstance() {
        return INSTANCE;
    }

    /**
     * get all.
     * @return - all.
     */
    public List<Image> getAll() {
        Transaction transaction = null;
        List<Image> list = new ArrayList<>();
        try (Session session = HibernateFactory.getFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from Image").list();
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
    public Image getById(int id) {
        return null;
    }
    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    @Override
    public Image getByName(String name) {
        return null;
    }
}
