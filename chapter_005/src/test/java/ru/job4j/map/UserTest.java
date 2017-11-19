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
        User second = new User("Sol", 2, new GregorianCalendar(1978, Calendar.APRIL, 12));
        mapTaskOne.put(first, new Object() {
            private String objectName = "Bullet";

            @Override
            public String toString() {
                return objectName;
            }
        });
        mapTaskOne.put(second, new Object() {
            private String objectName = "Bayonet";

            @Override
            public String toString() {
                return objectName;
            }
        });
        System.out.println(mapTaskOne);
    }
}