package ru.job4j.testtask;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.HOURS;

/**
 * Application for parse sql.ru. Find java job every 6 houres.
 */
public class Application {
    /**
     * parser object.
     */
    private WorkParser parser = new WorkParser();
    /**
     * datebase object.
     */
    private DataBaseExecutor db = new DataBaseExecutor();

    /**
     * start initial processes.
     */
    void init() {
        db.fillProperties();
        db.createTable();
    }

    /**
     * start application.
     */
    void start() {
        Runnable runner = () -> {
            parser.findJavaVacancy(db, new GregorianCalendar(2018, Calendar.JANUARY, 01));
        };
        ScheduledExecutorService dailyJobPicker = Executors.newSingleThreadScheduledExecutor();
        dailyJobPicker.scheduleAtFixedRate(runner, 0, 6, HOURS);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dailyJobPicker.shutdown();
    }

    /**
     * main.
     * @param args - args.
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.init();
        app.start();
    }
}
