package ru.job4j.carstore.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * test.
 */
public class ModelDAOTest {
    /**
     * bodyDAO.
     */
    private final ModelDAO modelDAO = ModelDAO.getInstance();
    /**
     * clear table.
     */
    @Before
    public void clearTable() {
        modelDAO.executeQuery("Truncate table model restart identity and commit no check");
    }
    /**
     * test add.
     * @throws Exception - exc.
     */
    @Test
    public void add() throws Exception {
        Model brand = new Model();
        brand.setName("Volvo");
        modelDAO.add(brand);
        List<Model> result = modelDAO.getAll();
        assertThat(result.get(0), is(brand));
    }
    /**
     * test get All entities.
     * @throws Exception - exc.
     */
    @Test
    public void getAll() throws Exception {
        Model model1 = new Model();
        model1.setName("Volvo");
        Model model2 = new Model();
        model2.setName("Saab");
        modelDAO.add(model1);
        modelDAO.add(model2);
        List<Model> expected = new ArrayList<>(Arrays.asList(model1, model2));
        List<Model> result = modelDAO.getAll();
        assertThat(result, is(expected));
    }
    /**
     * test get by id.
     * @throws Exception - exc.
     */
    @Test
    public void getById() throws Exception {
        Model model1 = new Model();
        model1.setName("Volvo");
        Model model2 = new Model();
        model2.setName("Saab");
        modelDAO.add(model1);
        modelDAO.add(model2);
        Model result = modelDAO.getById(1);
        assertThat(result, is(model1));
    }
    /**
     * test get by name.
     * @throws Exception - exc.
     */
    @Test
    public void getByName() throws Exception {
        Model model1 = new Model();
        model1.setName("Volvo");
        Model model2 = new Model();
        model2.setName("Saab");
        modelDAO.add(model1);
        modelDAO.add(model2);
        Model result = modelDAO.getByName("Saab");
        assertThat(result, is(model2));
    }
}