package ru.job4j.carstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.Image;

import java.util.ArrayList;
import java.util.List;

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

    public List<Image> getAll() {
        return new ArrayList<Image>();
    }
}
