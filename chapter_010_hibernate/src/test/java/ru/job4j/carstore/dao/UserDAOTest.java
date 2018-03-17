package ru.job4j.carstore.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
/**
 * test.
 */
public class UserDAOTest {
    /**
     * userDAO.
     */
    private final UserDAO userDAO = UserDAO.getInstance();
    /**
     * clear table.
     */
    @Before
    public void clearTable() {
        userDAO.executeQuery("Truncate table users restart identity and commit no check");
    }
    /**
     * test add.
     * @throws Exception - exc.
     */
    @Test
    public void add() throws Exception {
        User user = new User();
        user.setLogin("gora");
        user.setPassword("123");
        user.setEmail("gora@123");
        userDAO.add(user);
        List<User> result = userDAO.getAll();
        assertThat(result.get(0), is(user));
    }
    /**
     * test get All entities.
     * @throws Exception - exc.
     */
    @Test
    public void getAll() throws Exception {
        User user1 = new User();
        user1.setLogin("nora");
        user1.setPassword("223");
        user1.setEmail("nora@223");
        User user2 = new User();
        user2.setLogin("pora");
        user2.setPassword("323");
        user2.setEmail("pora@323");
        userDAO.add(user1);
        userDAO.add(user2);
        List<User> expected = new ArrayList<>(Arrays.asList(user1, user2));
        List<User> result = userDAO.getAll();
        assertThat(result, is(expected));
    }
    /**
     * test get by id.
     * @throws Exception - exc.
     */
    @Test
    public void getById() throws Exception {
        User user1 = new User();
        user1.setLogin("nora");
        user1.setPassword("223");
        user1.setEmail("nora@223");
        User user2 = new User();
        user2.setLogin("pora");
        user2.setPassword("323");
        user2.setEmail("pora@323");
        userDAO.add(user1);
        userDAO.add(user2);
        User result = userDAO.getById(1);
        assertThat(result, is(user1));
    }
    /**
     * test get by name.
     * @throws Exception - exc.
     */
    @Test
    public void getByLogin() throws Exception {
        User user1 = new User();
        user1.setLogin("nora");
        user1.setPassword("223");
        user1.setEmail("nora@223");
        User user2 = new User();
        user2.setLogin("pora");
        user2.setPassword("323");
        user2.setEmail("pora@323");
        userDAO.add(user1);
        userDAO.add(user2);
        List<User> expected = new ArrayList<>(Arrays.asList(user1, user2));
        User result = userDAO.getByLogin("pora");
        assertThat(result, is(user2));
        assertTrue(expected.contains(user1));
    }
}