package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * class for test sort method.
 */
public class SortUserTest {
    /**
     * test1.
     */
    @Test
    public void whenUnsortedInputThenSortedOutput() {
        SortUser sortU = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("tony", 102),
                        new User("stiv", 23),
                        new User("roma", 23),
                        new User("leha", 37),
                        new User("bill", 12)

                )
        );
        Set<User> setResult = sortU.sort(users);
        assertThat(
                setResult.toArray(
                        new User[setResult.size()]
                )[0].getName(), is("bill")
        );
    }
}