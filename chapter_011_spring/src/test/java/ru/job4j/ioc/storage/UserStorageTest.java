package ru.job4j.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.ioc.model.User;
import ru.job4j.ioc.storage.MemoryStorage;
import ru.job4j.ioc.storage.Storage;
import ru.job4j.ioc.storage.UserStorage;

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
        storage.add(new User("Muscul"));
    }
    /**
     * test2.
     */
    @Test
    public void whenInitializeMemoryStorageBeanThenResult() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage memory = context.getBean(MemoryStorage.class);
        assertNotNull(memory);
        UserStorage storage = new UserStorage(memory);
        storage.add(new User("Ferry"));
    }
    /**
     * test3.
     */
    @Test
    public void whenInitializeUserStorageBeanThenResult() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        assertNotNull(storage);
        storage.add(new User("Picka4u"));
    }
}