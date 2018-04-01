package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Image;

/**
 * image dao.
 */
@Repository
public interface ImageDAO extends CrudRepository<Image, Integer> {
}