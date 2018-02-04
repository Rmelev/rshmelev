package ru.job4j.testtask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Parse site for Java job.
 */
public class WorkParser {
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(WorkParser.class);
    /**
     * data format for reading from site.
     */
    private final SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm", new Locale("ru", "RU"));
    /**
     * restrict date for parse (using last update data).
     */
    private static long lastUpdateTopElementTime = 0;
    /**
     * Jsoup Document for parse.
     */
    private Document doc;
    /**
     * page-iterator of vacancies site pages.
     */
    private int pageCounter = 1;

    /**
     * connection.
     * @param i - page iterator.
     */
    void connectionToSite(int i) {
        try {
            String url = "http://www.sql.ru/forum/job-offers/" + i;
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * find java vacancies, parsing page code.
     * @param db - database for add vacancies.
     * @param cal - date for restrict date, from which search begin.
     */
    void findJavaVacancy(DataBaseExecutor db, Calendar cal) {
        Timestamp noFirstTime = db.topPositionDate();
        if (noFirstTime != null) {
            lastUpdateTopElementTime = noFirstTime.getTime();
        }
        do {
            connectionToSite(pageCounter);
            Elements elems = doc.select("tr:has(.postslisttopic)");
            Timestamp dateCreate;
            try {
                for (Element elem : elems) {
                    if (elem.text().toLowerCase().contains("java") && !elem.text().toLowerCase().contains("script")) {
                        Elements link = elem.select("a");
                        Elements data = elem.select("td");
                        String textVacancy = link.text();
                        String linkVacancy = link.attr("href");
                        int id = Integer.valueOf(parseId(linkVacancy));
                        String daCr = data.get(5).text();
                        dateCreate = toTimeStamp(daCr);
                        if (((noFirstTime != null)
                                && (lastUpdateTopElementTime > dateCreate.getTime()))
                                || (dateCreate.getTime() < cal.getTimeInMillis())) {
                            return;
                        }
                        db.addToDB(id, textVacancy, linkVacancy, dateCreate);
                        LOG.info(String.format("New elements:  %s | %s | %s | %s", id, textVacancy, linkVacancy, dateCreate));
                    }
                }
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
            pageCounter++;
        } while (true);
    }

    /**
     * transformation date from site using format to database using format.
     * @param date - string representation of date from site.
     * @return - date in Timestamp format.
     * @throws ParseException - exception.
     */
    Timestamp toTimeStamp(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        if (date.contains("сегодня")) {
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(9, 11)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(12, 14)));
        } else if (date.contains("вчера")) {
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(7, 9)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
        } else {
            calendar.setTime(format.parse(date));
        }
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * cut id from url string.
     * @param string - url string.
     * @return - vacancy id.
     */
    String parseId(String string) {
        String id = "";
        Pattern pat = Pattern.compile("forum/(\\d+)");
        Matcher match = pat.matcher(string);
        if (match.find()) {
            id = match.group(1);
        }
        return id;
    }

}