package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Body;

/**
 * body DAO.
 */
@Repository
public interface BodyDAO extends CrudRepository<Body, Integer> {
    /**
     * get Body By Name.
     * @param name - name.
     * @return - body.
     */
    Body getBodyByName(String name);
}

