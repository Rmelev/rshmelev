/*package ru.job4j.testtask;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MatcherClass {


    public static void main(String[] args) {
        String result = "privet";
        Matcher m;
        boolean found;
        int i = -2;
        int j = -2;
        Pattern p = Pattern.compile("\\d+");
        m = p.matcher("http://www.sql.ru/forum/1282152/senior-java-developer-msk-dinamo-vozmozhna-chastichnaya-udalenka-250000000000000-gross");
        found = m.find();
        result = m.group();

        //found = m.matches();
        //if (found) System.out.println("Yes");
        while (m.find()) {
            i = m.start();
            j = m.end();
            //result = m.group(1);
        }
        System.out.println(result + "  " + "i = " + i + " j = " + j);
    }
}*/
