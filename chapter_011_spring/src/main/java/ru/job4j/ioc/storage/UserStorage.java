package ru.job4j.ioc.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.ioc.model.User;

/**
 * user storage.
 */
@Component
public class UserStorage {
    /**
     * storage.
     */
    private Storage storage;

    /**
     * Constructor.
     * @param storage - storage.
     */
    @Autowired
    public UserStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * add user to using storage.
     * @param user - user.
     */
    public void add(User user) {
        this.storage.add(user);
    }
}
