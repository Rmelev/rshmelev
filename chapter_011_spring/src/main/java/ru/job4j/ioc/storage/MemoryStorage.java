package ru.job4j.ioc.storage;

import org.springframework.stereotype.Component;
import ru.job4j.ioc.interfaces.Storage;
import ru.job4j.ioc.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * store in memory.
 */
@Component
public class MemoryStorage implements Storage {
    /**
     * list of users.
     */
    private List<User> list = new ArrayList<>();

    /**
     * add user.
     * @param user - user.
     */
    @Override
    public void add(User user) {
        list.add(user);
    }

    /**
     * get user by id.
     * @param id - id.
     * @return - user.
     */
    @Override
    public User getById(int id) {
        return list.get(id);
    }
}
