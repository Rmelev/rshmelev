package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Transmission;

import java.util.List;

/**
 * transmission DAO.
 */
@Repository
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
        return super.getAll();
    }
    /**
     * get by id.
     * @param id - id
     * @return - entity.
     */
    @Override
    public Transmission getById(int id) {
        return super.getById(id);
    }
    /**
     * get by name.
     * @param name - name.
     * @return - entity.
     */
    public Transmission getByName(String name) {
        return super.getByName(name);
    }
}
