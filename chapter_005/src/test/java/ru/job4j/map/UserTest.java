package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertThat;

/**
 * test put elements into map.
 */
public class UserTest {
    /**
     * test1.
     */
    @Test
    public void mapTaskOne() {
        Map<User, Object> mapTaskOne = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        User first = new User("Heisenberg", 3, new GregorianCalendar(1975, Calendar.MARCH, 15));
        User second = new User("Heisenberg", 3, new GregorianCalendar(1975, Calendar.MARCH, 15));
        mapTaskOne.put(first, new Object());
        mapTaskOne.put(second, new Object());
        System.out.println(mapTaskOne);
    }

    /**
     * test2.
     */
    @Test
    public void mapTaskTwo() {
        Map<User, Object> mapTaskTwo = new HashMap<>();
        Calendar date1 = new GregorianCalendar(1815, Calendar.JUNE, 30);
        Calendar date2 = new GregorianCalendar(1815, Calendar.JUNE, 30);
        User first = new User("Velozeraptor", 12, date1);
        User second = new User("Velozeraptor", 12, date2);
        Object object = new Object() {
            private String objectName = "Bullet";
        };
        mapTaskTwo.put(first, object);
        mapTaskTwo.put(second, object);
        System.out.println(mapTaskTwo);
    }

    /**
     * test3.
     */
    @Test
    public void mapTaskThree() {
        Map<User, Object> mapTaskThree = new HashMap<>();
        Calendar date1 = new GregorianCalendar(1812, Calendar.JUNE, 30);
        Calendar date2 = new GregorianCalendar(1812, Calendar.JUNE, 30);
        User first = new User("Velozeraptor", 15, date1);
        User second = new User("Velozeraptor", 15, date2);
        Object object1 = new Object() {
            private String objectName = "Bullet";
        };
        Object object2 = new Object() {
            private String objectName = "Bullet";
        };
        mapTaskThree.put(first, object1);
        mapTaskThree.put(second, object2);
        System.out.println(mapTaskThree);
    }

    /**
     * test4.
     */
    @Test
    public void mapTaskFour() {
        Map<User, Object> mapTaskFour = new HashMap<>();
        Calendar date1 = new GregorianCalendar(1914, Calendar.JUNE, 30);
        Calendar date2 = new GregorianCalendar(1914, Calendar.JUNE, 30);
        User first = new User("Velozeraptor", 15, date1);
        User second = new User("Velozeraptor", 15, date2);
        Object object1 = new Object() {
            private String objectName = "Trash";
        };
        Object object2 = new Object() {
            private String objectName = "Trash";
        };
        mapTaskFour.put(first, object1);
        mapTaskFour.put(second, object2);
        System.out.println(mapTaskFour);
    }
}