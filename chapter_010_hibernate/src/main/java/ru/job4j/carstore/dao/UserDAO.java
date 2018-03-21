package ru.job4j.carstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.User;

import java.util.List;
/**
 * user DAO.
 */
public class UserDAO extends AbstractDAO<User> {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
    /**
     * single DAO instance.
     */
    private static final UserDAO INSTANCE = new UserDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static UserDAO getInstance() {
        return INSTANCE;
    }

    /**
     * get all.
     * @return - all.
     */
    @Override
    public List<User> getAll() {
        return super.getAll();
    }
    /**
     * get by id.
     * @param id - id
     * @return - entity.
     */
    @Override
    public User getById(int id) {
        return super.getById(id);
    }

    /**
     * get by login.
     * @param login - login.
     * @return - entity.
     */
    public User getByLogin(String login) {
        return super.getByLogin(login);
    }
}
