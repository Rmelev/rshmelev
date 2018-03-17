package ru.job4j.carstore.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.Body;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test.
 */
public class BodyDAOTest {
    /**
     * bodyDAO.
     */
    private final BodyDAO bodyDAO = BodyDAO.getInstance();
    /**
     * clear table.
     */
    @Before
    public void clearTable() {
        bodyDAO.executeQuery("Truncate table body restart identity and commit no check");
    }
    /**
     * test add.
     * @throws Exception - exc.
     */
    @Test
    public void add() throws Exception {
        Body body = new Body();
        body.setName("hatchback");
        bodyDAO.add(body);
        List<Body> result = bodyDAO.getAll();
        assertThat(result.get(0), is(body));
    }
    /**
     * test get All entities.
     * @throws Exception - exc.
     */
    @Test
    public void getAll() throws Exception {
        Body body1 = new Body();
        body1.setName("hatchback");
        Body body2 = new Body();
        body2.setName("sedan");
        bodyDAO.add(body1);
        bodyDAO.add(body2);
        List<Body> expected = new ArrayList<>(Arrays.asList(body1, body2));
        List<Body> result = bodyDAO.getAll();
        assertThat(result, is(expected));
    }
    /**
     * test get by id.
     * @throws Exception - exc.
     */
    @Test
    public void getById() throws Exception {
        Body body1 = new Body();
        body1.setName("hatchback");
        Body body2 = new Body();
        body2.setName("sedan");
        bodyDAO.add(body1);
        bodyDAO.add(body2);
        Body result = bodyDAO.getById(1);
        assertThat(result, is(body1));
    }
    /**
     * test get by name.
     * @throws Exception - exc.
     */
    @Test
    public void getByName() throws Exception {
        Body body1 = new Body();
        body1.setName("hatchback");
        Body body2 = new Body();
        body2.setName("sedan");
        bodyDAO.add(body1);
        bodyDAO.add(body2);
        Body result = bodyDAO.getByName("sedan");
        assertThat(result, is(body2));
    }
}