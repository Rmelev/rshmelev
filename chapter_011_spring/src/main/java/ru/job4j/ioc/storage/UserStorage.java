package ru.job4j.ioc.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.job4j.ioc.HibernateFactory;
import ru.job4j.ioc.interfaces.Storage;
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

    /**
     * get user by id.
     * @param id - id.
     * @return - user.
     */
    public User getById(int id) {
        return this.storage.getById(id);
    }

    /**
     * main().
     * @param args - args.
     * @throws Exception - exc.
     */
    public static void main(String[] args) throws Exception {
         ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage storage = (UserStorage) context.getBean("storage");
        User user = new User();
        user.setName("Norja");
        storage.add(user);
        System.out.println(storage.getById(4));
        HibernateFactory.close();
    }
}
