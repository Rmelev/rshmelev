package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * class for sort.
 */
public class SortUser {
    /**
     * method to sort by age.
     * @param list - list of users.
     * @return - set of users.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}
/*
public class SortUser {
    public static void main(String[] args) {
        SortU sortU = new SortU();
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
        System.out.println(sortU.sort(users));
    }
}
*/