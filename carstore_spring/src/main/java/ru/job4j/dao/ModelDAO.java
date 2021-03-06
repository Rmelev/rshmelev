package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Model;

/**
 * engine DAO.
 */
@Repository
public interface ModelDAO extends CrudRepository<Model, Integer> {
    /**
     * get Model By Name.
     * @param name - name.
     * @return - model.
     */
    Model getModelByName(String name);
}