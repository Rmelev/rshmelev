package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for user's hash map.
 */
public class UsersSimpleHashMapTest {
    /**
     * test1. Key - Integer, Value - String.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenInsertGetDeleteThenCorrectResult() {
        UsersSimpleHashMap<Integer, String> map = new UsersSimpleHashMap(3);
        Iterator<String> iter = map.iterator();
        map.insert(12345, "Mira");
        map.insert(55555, "Vera");
        map.insert(11111, "Dedo");
        map.insert(22222, "Edva");
        map.insert(44444, "Rand");
        map.insert(66666, "Bond");
        map.insert(77777, "Romb");
        map.insert(14111, "Pool");
        map.insert(24222, "Medved");
        assertThat(map.get(12345), is("Mira"));
        assertThat(map.get(32323), is((String) null));
        map.delete(12345);
        System.out.println(map.getSize());
        System.out.println(map);
        for (String temp : map) {
            System.out.print(temp + "  ");
        }
        System.out.println();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Medved"));
        assertThat(iter.next(), is("Vera"));
        assertThat(iter.next(), is("Rand"));
        iter.next(); iter.next(); iter.next(); iter.next();
        assertThat(iter.next(), is("Pool"));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    /**
     * test2. Key - User, Value - String.
     */
    @Test
    public void whenKeyIsObjectThenCorrectResult() {
        UsersSimpleHashMap<User, String> map = new UsersSimpleHashMap(3);
        Iterator<String> iter = map.iterator();
        map.insert(new User("Heisenberg", 3, new GregorianCalendar(1975, Calendar.MARCH, 15)), "Mira");
        map.insert(new User("Velozeraptor", 12, new GregorianCalendar(1812, Calendar.JUNE, 30)), "Vera");
        User userN = new User("Buldozer", 15, new GregorianCalendar(1812, Calendar.JUNE, 30));
        map.insert(userN, "Dedo");
        map.insert(new User("Grigorio", 15, new GregorianCalendar(1914, Calendar.JUNE, 30)), "Edva");
        System.out.println("Текущий размер массива: " + map.getSize());
        System.out.println(map);
        for (String temp : map) {
            System.out.print(temp + " ");
        }
        System.out.println();
        assertThat(map.get(userN), is("Dedo"));
        map.delete(userN);
        for (String temp : map) {
            System.out.print(temp + "  ");
        }
        System.out.println();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Edva"));
        assertThat(iter.next(), is("Mira"));
        assertThat(iter.next(), is("Vera"));
        assertThat(iter.hasNext(), is(false));
    }

    /**
     * test3. Key - String, Value - User.
     */
    @Test
    public void whenValueIsObjectThenCorrectResult() {
        UsersSimpleHashMap<String, User> map = new UsersSimpleHashMap(3);
        Iterator<User> iter = map.iterator();
        User user1 = new User("Heisenberg", 3, new GregorianCalendar(1975, Calendar.MARCH, 15));
        map.insert("Mira", user1);
        User user2 = new User("Velozeraptor", 12, new GregorianCalendar(1812, Calendar.JUNE, 30));
        map.insert("Vera", user2);
        User user3 = new User("Buldozer", 15, new GregorianCalendar(1812, Calendar.JUNE, 30));
        map.insert("Dedo", user3);
        User user4 = new User("Grigorio", 15, new GregorianCalendar(1914, Calendar.JUNE, 30));
        map.insert("Edva", user4);
        System.out.println(map.getSize());
        System.out.println(map);
        for (User temp : map) {
            System.out.println(temp + " ");
        }
        System.out.println();
        assertThat(map.get("Dedo"), is(user3));
        map.delete("Dedo");
        for (User temp : map) {
            System.out.println(temp + "  ");
        }
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(user2));
        assertThat(iter.next(), is(user1));
        assertThat(iter.next(), is(user4));
        assertThat(iter.hasNext(), is(false));
    }
}