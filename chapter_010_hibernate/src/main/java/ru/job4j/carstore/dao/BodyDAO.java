package ru.job4j.carstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.Body;

import java.util.List;

/**
 * body DAO.
 */
public class BodyDAO extends AbstractDAO<Body> {
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
    public int add(final Body body) {
        return super.add(body);
    }

    /**
     * get all.
     * @return - all.
     */
    @Override
    public List<Body> getAll() {
        return super.getAll();
    }

    /**
     * get by id.
     * @param id - id
     * @return - entity.
     */
    @Override
    public Body getById(int id) {
        return super.getById(id);
    }

    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    public Body getByName(String name) {
        return super.getByName(name);
    }
}
