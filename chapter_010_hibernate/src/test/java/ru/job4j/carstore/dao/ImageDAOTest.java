package ru.job4j.carstore.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * test.
 */
public class ImageDAOTest {
    /**
     * bodyDAO.
     */
    private final ImageDAO imageDAO = ImageDAO.getInstance();
    /**
     * clear table.
     */
    @Before
    public void clearTable() {
        imageDAO.executeQuery("Truncate table image restart identity and commit no check");
    }
    /**
     * test add.
     * @throws Exception - exc.
     */
    @Test
    public void add() throws Exception {
        Image image = new Image();
        image.setData("Picture".getBytes());
        imageDAO.saveOrUpdate(image);
        List<Image> result = imageDAO.getAll();
        assertThat(result.get(0), is(image));
    }
    /**
     * test get All entities.
     * @throws Exception - exc.
     */
    @Test
    public void getAll() throws Exception {
        Image picture = new Image();
        picture.setData("Image".getBytes());
        Image anotherPicture = new Image();
        anotherPicture.setData("Other Image".getBytes());
        imageDAO.saveOrUpdate(picture);
        imageDAO.saveOrUpdate(anotherPicture);

        List<Image> expected = new ArrayList<>(Arrays.asList(picture, anotherPicture));
        List<Image> result = imageDAO.getAll();

        assertThat(result, is(expected));
    }
}