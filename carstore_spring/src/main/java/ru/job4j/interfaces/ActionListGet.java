package ru.job4j.interfaces;

import org.hibernate.Session;

import java.util.List;

/**
 * action interface, return result of execute.
 * @param <T> -  type of entities.
 */
public interface ActionListGet<T> {
    /**
     * execute something, return result.
     * @param session - session.
     * @return - list of entities.
     */
    List<T> execute(Session session);
}
