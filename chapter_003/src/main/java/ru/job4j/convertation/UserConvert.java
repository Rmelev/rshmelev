package ru.job4j.convertation;

import java.util.*;

/**
 * class for convert List to HashMap.
 */
public class UserConvert {
    /**
     * HashMap of keys & users.
     */
    HashMap<Integer, User> userMap = new HashMap<>();

    /**
     * convertation.
     * @param list - List of Users.
     * @return - HashMap of keys & users.
     */
    public HashMap<Integer, User> process(List<User> list) {
        for (User listTemp : list) {
            userMap.put(listTemp.id++, listTemp);
        }
        return userMap;
    }
}