package ru.job4j.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Car;

import java.util.List;

/**
 * car DAO.
 */
@Repository
public interface CarDAO extends CrudRepository<Car, Integer> {
}
//public class CarDAO  extends AbstractDAO<Car> {
//    /**
//     * single DAO instance.
//     */
//    private static final CarDAO INSTANCE = new CarDAO();
//
//    /**
//     * Getter for instance.
//     * @return instance.
//     */
//    public static CarDAO getInstance() {
//        return INSTANCE;
//    }
//
//    @Override
//    public List<Car> getAll() {
//        return super.getAll();
//    }
//
//    @Override
//    public Car getById(int id) {
//        return super.getById(id);
//    }
//
//}