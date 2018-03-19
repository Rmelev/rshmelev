package ru.job4j.ioc.interfaces;

import org.hibernate.Session;

/**
 * action interface.
 * @param <T> -  type of entities.
 */
public interface Action<T> {
    /**
     * execute something.
     * @param session - session.
     */
    void execute(Session session);
}
