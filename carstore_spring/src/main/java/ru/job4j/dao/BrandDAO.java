package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Brand;

/**
 * Brand DAO.
 */
@Repository
public interface BrandDAO extends CrudRepository<Brand, Integer> {
}
