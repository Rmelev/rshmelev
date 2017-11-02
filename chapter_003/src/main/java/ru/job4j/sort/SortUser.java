package ru.job4j.sort;

import java.util.Collections;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
import java.util.Comparator;

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

    /**
     * sort by length of name.
     * @param list - list of users.
     * @return - sorted list of users.
     */
    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() > o2.getName().length() ? 1 : -1;
            }
        });
        return list;
    }

    /**
     * sort by name, if names are equal - by age.
     * @param list - list of users.
     * @return - sorted list of users.
     */
    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().equals(o2.getName()) ? o1.getAge().compareTo(o2.getAge()) : o1.getName().compareTo(o2.getName());
            }
        });
        return list;
    }
}
