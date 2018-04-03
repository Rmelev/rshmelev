package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Car;

/**
 * car DAO.
 */
@Repository
public interface CarDAO extends CrudRepository<Car, Integer> {
}
