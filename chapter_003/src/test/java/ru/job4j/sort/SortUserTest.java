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
                        new User("bill", 12),
                        new User("abov", 23)
                )
        );
        Set<User> setResult = sortU.sort(users);
        System.out.println(setResult);
        assertThat(
                setResult.toArray(
                        new User[setResult.size()]
                )[0].getName(), is("bill")
        );
    }

    /**
     * test2.
     */
    @Test
    public void whenDifSizeOfNameThenSorted() {
        SortUser sortU = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("tonny", 102),
                        new User("stiv", 23),
                        new User("roma", 23),
                        new User("lehalord", 37),
                        new User("bil", 12),
                        new User("atom", 21)
                )
        );
        List<User> result = sortU.sortNameLength(users);
        System.out.println(result);
        assertThat(result.toArray(new User[result.size()])[0].getName(), is("bil"));
    }

    /**
     * test3.
     */
    @Test
    public void whenIfNamesEqualThenCompareAge() {
        SortUser sortU = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(
                Arrays.asList(
                        new User("tonny", 102),
                        new User("stiv", 12),
                        new User("stiv", 6),
                        new User("lehalord", 37),
                        new User("bil", 34),
                        new User("bil", 23)
                )
        );
        List<User> result = sortU.sortNameLength(users);
        System.out.println(result);
        assertThat(result.toArray(new User[result.size()])[0].getAge(), is(23));
    }
}