package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for testing SimpleArray.
 */
public class StoreTest {
    /**
     * test1.
     */
    @Test
    public void whenTestAddUpdateDeleteThenCorrectResult() {
        Store<User> storeForUser = new UserStore();
        Store<Role> storeForRole = new RoleStore();
        storeForUser.add(new User("Miami"));
        Role city = new Role("Houston");
        storeForRole.add(city);
        System.out.println(storeForUser.add(new User("New York")));
        System.out.println();
        storeForRole.add(new Role("Seattle"));
        storeForUser.add(new User("San Francisco"));
        storeForUser.add(new User("Los Angeles"));
        storeForRole.update(city);
        storeForUser.delete("Miami");
        System.out.println(storeForUser.getEl(0));
        System.out.println(storeForUser.getEl(1));
        System.out.println(storeForUser.getEl(2));
        System.out.println(storeForRole.getEl(0));
        System.out.println(storeForRole.getEl(1));
        assertThat(storeForUser.getEl(0), is("New York"));
        System.out.println();
        System.out.println(city.getClass());
    }
}
