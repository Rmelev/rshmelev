package ru.job4j.changes;

import java.sql.Time;
import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//import java.util.TimeZone;

/**
 *
 */
public class DataTrain {
    /**
     *
     * @param args - args.
     * @throws InterruptedException - exc.
     */
    public static void main(String[] args) throws InterruptedException {
        Timestamp t = new Timestamp(System.currentTimeMillis());
//        Thread.sleep(2000);
//        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
//        Thread.sleep(1000);
//        Timestamp timestamp3 = new Timestamp(System.currentTimeMillis());
//        long l = timestamp2.getTime() - timestamp1.getTime();
//        long l2 = timestamp3.toLocalDateTime().toLocalTime()..getTime() - timestamp2.getTime();
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
//        calendar1.setTimeInMillis(0);
////        calendar.set(2015, Calendar.JANUARY, 10, 1, 0, 0);
//        calendar1.setTimeInMillis(t.getTime());
//        long now1 = calendar1.getTimeInMillis();
//
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
//        calendar2.setTimeInMillis(0);
////        calendar.set(2015, Calendar.JANUARY, 10, 1, 0, 0);
//        calendar2.setTimeInMillis(t.getTime());
//        long now2 = calendar2.getTimeInMillis();
//
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.setTimeInMillis(5000);
//        calendar3.set(0,0,0,0,0, 0);
//        calendar3.setTimeInMillis(5000);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(5000);
        Timestamp tut = new Timestamp(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DATE);

//        calendar1.set(Calendar.HOUR, 0);


        // Создаем инстанс java.sql.Time
//        java.sql.Time sqlTime = new java.sql.Time(now2 - now1);
//        java.sql.Date sqlDate = new java.sql.Date(now);
        Time time = new Time(5000);
        LocalTime.of(0, 0, 4);
        int m = time.getHours();
//        time.setHours(0);

        System.out.println(dayOfWeek);
    }
}
