package ru.job4j.ioc.interfaces;

import ru.job4j.ioc.model.User;

/**
 * storage interface.
 */
public interface Storage {
    /**
     * add entity.
     * @param user - user.
     */
    void add(User user);

    /**
     * get by id.
     * @param id - id.
     * @return - entity.
     */
    User getById(int id);
}
