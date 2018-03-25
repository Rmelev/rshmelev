package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Engine;

import java.util.List;

/**
 * engine DAO.
 */
@Repository
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
        return super.getAll();
    }
    /**
     * get by id.
     * @param id - id
     * @return - entity.
     */
    @Override
    public Engine getById(int id) {
        return super.getById(id);
    }
    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    public Engine getByName(String name) {
        return super.getByName(name);
    }
}
