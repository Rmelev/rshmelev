package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Engine;

/**
 * engine DAO.
 */
@Repository
public interface EngineDAO extends CrudRepository<Engine, Integer> {
    /**
     * get Engine By Name.
     * @param name - name.
     * @return - engine.
     */
    Engine getEngineByName(String name);
}
