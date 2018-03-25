package ru.job4j.ioc.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.job4j.ioc.interfaces.Storage;
import ru.job4j.ioc.model.User;

/**
 * storage in DB.
 */
@Component
public class JdbcStorage extends AbstractStorage<User> implements Storage {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractStorage.class);

    /**
     * add.
     * @param user - user.
     */
    @Override
    public void add(User user) {
        voidTx(session -> session.saveOrUpdate(user));
    }

    /**
     * get by id.
     * @param id - id.
     * @return - user.
     */
    @Override
    public User getById(int id) {
        return getTx(session -> session.get(User.class, id));
    }
}
