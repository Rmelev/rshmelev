package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Body;

import java.util.List;

/**
 * body DAO.
 */
@Repository
public interface BodyDAO extends CrudRepository<Body, Integer> {
    Body getBodyByName(String name);
}
//public class BodyDAO extends AbstractDAO<Body> {
//    /**
//     * Logger.
//     */
//    private static final Logger LOG = LoggerFactory.getLogger(BodyDAO.class);
//    /**
//     * single DAO instance.
//     */
//    private static final BodyDAO INSTANCE = new BodyDAO();
//
//    /**
//     * Getter for instance.
//     * @return instance.
//     */
//    public static BodyDAO getInstance() {
//        return INSTANCE;
//    }
//
//    /**
//     * add entity.
//     * @param body - body.
//     * @return - added entity.
//     */
//    public int add(final Body body) {
//        return super.add(body);
//    }
//
//    /**
//     * get all.
//     * @return - all.
//     */
//    @Override
//    public List<Body> getAll() {
//        return super.getAll();
//    }
//
//    /**
//     * get by id.
//     * @param id - id
//     * @return - entity.
//     */
//    @Override
//    public Body getById(int id) {
//        return super.getById(id);
//    }
//
//    /**
//     * get by name.
//     * @param name - name.
//     * @return - entity.
//     */
//    public Body getByName(String name) {
//        return super.getByName(name);
//    }
//}
