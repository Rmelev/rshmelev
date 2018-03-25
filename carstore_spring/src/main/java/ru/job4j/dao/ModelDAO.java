package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Model;

import java.util.List;

/**
 * engine DAO.
 */
@Repository
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
        return super.getAll();
    }
    /**
     * get by id.
     * @param id - id
     * @return - entity.
     */
    @Override
    public Model getById(int id) {
        return super.getById(id);
    }
    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    public Model getByName(String name) {
        return super.getByName(name);
    }
}
