package ru.job4j.carstore.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * test.
 */
public class EngineDAOTest {
    /**
     * engineDAO.
     */
    private final EngineDAO engineDAO = EngineDAO.getInstance();
    /**
     * clear table.
     */
    @Before
    public void clearTable() {
        engineDAO.executeQuery("Truncate table engine restart identity and commit no check");
    }
    /**
     * test add.
     * @throws Exception - exc.
     */
    @Test
    public void add() throws Exception {
        Engine engine = new Engine();
        engine.setName("gas");
        engineDAO.add(engine);
        List<Engine> result = engineDAO.getAll();
        assertThat(result.get(0), is(engine));
    }
    /**
     * test get All entities.
     * @throws Exception - exc.
     */
    @Test
    public void getAll() throws Exception {
        Engine engine1 = new Engine();
        engine1.setName("gas");
        Engine engine2 = new Engine();
        engine2.setName("diesel");
        engineDAO.add(engine1);
        engineDAO.add(engine2);
        List<Engine> expected = new ArrayList<>(Arrays.asList(engine1, engine2));
        List<Engine> result = engineDAO.getAll();
        assertThat(result, is(expected));
    }
    /**
     * test get by id.
     * @throws Exception - exc.
     */
    @Test
    public void getById() throws Exception {
        Engine engine1 = new Engine();
        engine1.setName("gas");
        Engine engine2 = new Engine();
        engine2.setName("diesel");
        engineDAO.add(engine1);
        engineDAO.add(engine2);
        Engine result = engineDAO.getById(1);
        assertThat(result, is(engine1));
    }
    /**
     * test get by name.
     * @throws Exception - exc.
     */
    @Test
    public void getByName() throws Exception {
        Engine engine1 = new Engine();
        engine1.setName("gas");
        Engine engine2 = new Engine();
        engine2.setName("diesel");
        engineDAO.add(engine1);
        engineDAO.add(engine2);
        Engine result = engineDAO.getByName("diesel");
        assertThat(result, is(engine2));
    }
}