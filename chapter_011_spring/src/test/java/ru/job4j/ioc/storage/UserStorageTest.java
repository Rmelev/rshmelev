package ru.job4j.ioc.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.ioc.model.User;
import ru.job4j.ioc.interfaces.Storage;

import static org.junit.Assert.assertNotNull;

/**
 * spring ioc tests.
 */
public class UserStorageTest {
    /**
     * test1.
     */
    @Test
    public void whenNoSpringThenSimpleResult() {
        UserStorage storage = new UserStorage(new MemoryStorage());
        User user = new User();
        user.setName("Muscul");
        storage.add(user);
    }
    /**
     * test2.
     */
    @Test
    public void whenInitializeMemoryStorageBeanThenResult() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-test.xml");
        Storage memory = context.getBean(MemoryStorage.class);
        assertNotNull(memory);
        UserStorage storage = new UserStorage(memory);
        User user = new User();
        user.setName("Ferry");
        storage.add(user);
    }
    /**
     * test3.
     */
    @Test
    public void whenInitializeUserStorageBeanThenResult() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-test.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        assertNotNull(storage);
        User user = new User();
        user.setName("Picka4u");
        storage.add(user);
    }
}