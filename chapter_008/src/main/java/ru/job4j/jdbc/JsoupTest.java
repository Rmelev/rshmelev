/*package ru.job4j.jdbc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.log4j.Logger;

import java.io.IOException;

public class JsoupTest {

    final static Logger LOG = Logger.getLogger(JsoupTest.class);
    public static void main(String[] args) throws IOException {
        //Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = "https://www.pornhub.com/photo/119372551";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 200));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 3500));
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }*/
//    public static void main(String[] args) {
//
//        File input = new File("/Users/romansmelev/projects/rshmelev/chapter_008/src/main/java/ru/job4j/input.html");
//        Document doc = null;
//        try {
//            doc = Jsoup.parse(input, "UTF-8", "http://en.wikipedia.com/");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Element content = doc.getElementById("content");
//        Elements links = content.getElementsByTag("a");
//        for (Element link : links) {
//            System.out.println(link.attr("href"));
//            System.out.println(link.text());
//        }
//        Document doc = null;
//        try {
//            doc = Jsoup.connect("http://ru.wikipedia.com/wiki/Андогский,_Александр_Иванович").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(doc.title());
//        Elements newsHeadlines = doc.select("[href]");
//        Iterator<Element> elementIterator = newsHeadlines.iterator();
//        while (elementIterator.hasNext()) {
//            System.out.println(elementIterator.next());
//        }
//        for (Element headline : newsHeadlines) {
//            System.out.println(headline.text());
//        }
//}
