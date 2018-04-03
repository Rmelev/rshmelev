package ru.job4j.repository;

import java.util.List;

/**
 * common DAO methods.
 * @param <T> - entity.
 */
public interface DAO<T> {
    /**
     * get all.
     * @return - all entities.
     */
    List<T> getAll();

    /**
     * @param id - entity id.
     * @return - entity.
     */
    T getById(int id);
}
