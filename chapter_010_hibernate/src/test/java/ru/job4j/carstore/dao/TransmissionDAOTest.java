package ru.job4j.carstore.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.Transmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * test.
 */
public class TransmissionDAOTest {
    /**
     * transmissionDAO.
     */
    private final TransmissionDAO transmissionDAO = TransmissionDAO.getInstance();
    /**
     * clear table.
     */
    @Before
    public void clearTable() {
        transmissionDAO.executeQuery("Truncate table transmission restart identity and commit no check");
    }
    /**
     * test add.
     * @throws Exception - exc.
     */
    @Test
    public void add() throws Exception {
        Transmission transmission = new Transmission();
        transmission.setName("auto");
        transmissionDAO.add(transmission);
        List<Transmission> result = transmissionDAO.getAll();
        assertThat(result.get(0), is(transmission));
    }
    /**
     * test get All entities.
     * @throws Exception - exc.
     */
    @Test
    public void getAll() throws Exception {
        Transmission transmission1 = new Transmission();
        transmission1.setName("auto");
        Transmission transmission2 = new Transmission();
        transmission2.setName("musk");
        transmissionDAO.add(transmission1);
        transmissionDAO.add(transmission2);
        List<Transmission> expected = new ArrayList<>(Arrays.asList(transmission1, transmission2));
        List<Transmission> result = transmissionDAO.getAll();
        assertThat(result, is(expected));
    }
    /**
     * test get by id.
     * @throws Exception - exc.
     */
    @Test
    public void getById() throws Exception {
        Transmission transmission1 = new Transmission();
        transmission1.setName("auto");
        Transmission transmission2 = new Transmission();
        transmission2.setName("musk");
        transmissionDAO.add(transmission1);
        transmissionDAO.add(transmission2);
        Transmission result = transmissionDAO.getById(1);
        assertThat(result, is(transmission1));
    }
    /**
     * test get by name.
     * @throws Exception - exc.
     */
    @Test
    public void getByName() throws Exception {
        Transmission transmission1 = new Transmission();
        transmission1.setName("auto");
        Transmission transmission2 = new Transmission();
        transmission2.setName("musk");
        transmissionDAO.add(transmission1);
        transmissionDAO.add(transmission2);
        Transmission result = transmissionDAO.getByName("musk");
        assertThat(result, is(transmission2));
    }
}