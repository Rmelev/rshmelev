package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.models.Role;

public interface RoleDAO extends CrudRepository<Role, Integer> {
}
