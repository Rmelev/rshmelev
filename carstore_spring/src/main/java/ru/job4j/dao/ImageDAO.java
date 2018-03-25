package ru.job4j.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Image;

import java.util.List;

/**
 * image dao.
 */
@Repository
public class ImageDAO extends AbstractDAO<Image> {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ImageDAO.class);
    /**
     * single DAO instance.
     */
    private static final ImageDAO INSTANCE = new ImageDAO();

    /**
     * Getter for instance.
     * @return instance.
     */
    public static ImageDAO getInstance() {
        return INSTANCE;
    }

    /**
     * get all.
     * @return - all.
     */
    public List<Image> getAll() {
        return super.getAll();
    }
    /**
     * get by id.
     * @param id - id
     * @return - entity.
     */
    @Override
    public Image getById(int id) {
        return super.getById(id);
    }

}
