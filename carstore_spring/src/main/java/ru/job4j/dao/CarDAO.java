package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Car;

/**
 * car DAO.
 */
@Repository
public interface CarDAO extends CrudRepository<Car, Integer> {
}
