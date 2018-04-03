package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Brand;

/**
 * Brand DAO.
 */
@Repository
public interface BrandDAO extends CrudRepository<Brand, Integer> {
}
