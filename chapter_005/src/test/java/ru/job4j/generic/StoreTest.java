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
    public void whenTestAddUpdateDeleteThenCorrectResult() { // Замени название!!!
        Store<Base> store = new UserStore<>();
        store.add(new User("Miami"));
        Base city = new Role("Houston");
        store.add(city);
        System.out.println(store.add(new User("New York")));
        System.out.println();
        store.add(new Role("Seattle"));
        store.add(new User("San Francisco"));
        store.add(new User("Los Angeles"));
        store.update(city);
        store.delete("Miami");
        System.out.println(store.getEl(0));
        System.out.println(store.getEl(1));
        System.out.println(store.getEl(2));
        System.out.println(store.getEl(3));
        System.out.println(store.getEl(4));
        assertThat(store.getEl(1), is("New York"));
        System.out.println();
        System.out.println(city.getClass());
    }
}
