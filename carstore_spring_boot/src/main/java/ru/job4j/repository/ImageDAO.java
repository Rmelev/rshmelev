package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Image;

/**
 * image dao.
 */
@Repository
public interface ImageDAO extends CrudRepository<Image, Integer> {
}