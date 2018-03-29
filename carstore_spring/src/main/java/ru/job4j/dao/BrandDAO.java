package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Brand;

import java.util.List;

/**
 * Brand DAO.
 */
@Repository
public interface BrandDAO extends CrudRepository<Brand, Integer> {
}
//public class BrandDAO extends AbstractDAO<Brand> {
//    /**
//     * Logger.
//     */
//    private static final Logger LOG = LoggerFactory.getLogger(BrandDAO.class);
//    /**
//     * single DAO instance.
//     */
//    private static final BrandDAO INSTANCE = new BrandDAO();
//
//    /**
//     * Getter for instance.
//     * @return instance.
//     */
//    public static BrandDAO getInstance() {
//        return INSTANCE;
//    }
//
//    /**
//     * get all.
//     * @return - all.
//     */
//    @Override
//    public List<Brand> getAll() {
//        return super.getAll();
//    }
//
//    /**
//     * get by id.
//     * @param id - id
//     * @return - entity.
//     */
//    @Override
//    public Brand getById(int id) {
//        return super.getById(id);
//    }
//
//    /**
//     * get by name.
//     * @param name - name.
//     * @return - entity.
//     */
//    public Brand getByName(String name) {
//        return super.getByName(name);
//    }
//}
