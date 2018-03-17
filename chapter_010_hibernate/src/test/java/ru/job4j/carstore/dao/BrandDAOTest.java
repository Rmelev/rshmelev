package ru.job4j.carstore.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.Brand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * test.
 */
public class BrandDAOTest {
    /**
     * brandDAO.
     */
    private final BrandDAO brandDAO = BrandDAO.getInstance();
    /**
     * clear table.
     */
    @Before
    public void clearTable() {
        brandDAO.executeQuery("Truncate table brand restart identity and commit no check");
    }
    /**
     * test add.
     * @throws Exception - exc.
     */
    @Test
    public void add() throws Exception {
        Brand brand = new Brand();
        brand.setName("Volvo");
        brandDAO.add(brand);
        List<Brand> result = brandDAO.getAll();
        assertThat(result.get(0), is(brand));
    }
    /**
     * test get All entities.
     * @throws Exception - exc.
     */
    @Test
    public void getAll() throws Exception {
        Brand brand1 = new Brand();
        brand1.setName("Volvo");
        Brand brand2 = new Brand();
        brand2.setName("Saab");
        brandDAO.add(brand1);
        brandDAO.add(brand2);
        List<Brand> expected = new ArrayList<>(Arrays.asList(brand1, brand2));
        List<Brand> result = brandDAO.getAll();
        assertThat(result, is(expected));
    }
    /**
     * test get by id.
     * @throws Exception - exc.
     */
    @Test
    public void getById() throws Exception {
        Brand brand1 = new Brand();
        brand1.setName("Volvo");
        Brand brand2 = new Brand();
        brand2.setName("Saab");
        brandDAO.add(brand1);
        brandDAO.add(brand2);
        Brand result = brandDAO.getById(1);
        assertThat(result, is(brand1));
    }
    /**
     * test get by name.
     * @throws Exception - exc.
     */
    @Test
    public void getByName() throws Exception {
        Brand brand1 = new Brand();
        brand1.setName("Volvo");
        Brand brand2 = new Brand();
        brand2.setName("Saab");
        brandDAO.add(brand1);
        brandDAO.add(brand2);
        Brand result = brandDAO.getByName("Saab");
        assertThat(result, is(brand2));
    }
}