package ru.job4j.carstore.interfaces;

import org.hibernate.Session;

/**
 * action interface, return result of execute.
 * @param <T> -  type of entities.
 */
public interface ActionGet<T> {
    /**
     * execute something, return result.
     * @param session - session.
     * @return - entity.
     */
    T execute(Session session);
}
