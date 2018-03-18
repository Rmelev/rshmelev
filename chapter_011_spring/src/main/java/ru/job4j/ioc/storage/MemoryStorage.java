package ru.job4j.ioc.storage;

import org.springframework.stereotype.Component;
import ru.job4j.ioc.model.User;

/**
 * store in memory.
 */
@Component
public class MemoryStorage implements Storage {
    /**
     * add user.
     * @param user - user.
     */
    @Override
    public void add(User user) {
        System.out.println("Added user is: " + user);
    }
}
