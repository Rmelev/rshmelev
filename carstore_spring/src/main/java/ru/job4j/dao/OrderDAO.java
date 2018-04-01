package ru.job4j.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Order;

import java.sql.Timestamp;
import java.util.List;

/**
 * body DAO.
 */
@Repository
public interface OrderDAO extends CrudRepository<Order, Integer> {
    /**
     * find All Orders By Date Greater Than.
     * @param day - date.
     * @return - list of orders.
     */
    List<Order> findAllByDateGreaterThan(Timestamp day);

    /**
     * find All Orders By brand name.
     * @param name - name.
     * @return - list of orders.
     */
    List<Order> findAllByCarModelBrandName(String name);
}