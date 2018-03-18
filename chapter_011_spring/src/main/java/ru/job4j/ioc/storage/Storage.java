package ru.job4j.ioc.storage;

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
}
