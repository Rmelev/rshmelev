package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.User;

/**
 * user DAO.
 */
@Repository
public interface UserDAO extends CrudRepository<User, Integer> {
    /**
     * find User By Login.
     * @param login - login.
     * @return - user.
     */
    User findUserByLogin(String login);
}