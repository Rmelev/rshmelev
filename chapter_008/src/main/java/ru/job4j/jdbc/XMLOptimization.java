package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * optimization one view xml file to another.
 */
public class XMLOptimization {
    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(XMLOptimization.class);
    /**
     * path to connection with database.
     */
    private String dbConnect;

    /**
     * setter.
     * @param dbConnect - path to connection with database.
     */
    public void setDbConnect(String dbConnect) {
        this.dbConnect = dbConnect;
    }

    /**
     * number of entries.
     */
    private int n;

    /**
     * setter.
     * @param n number of entries.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * main().
     * @param args - args.
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        XMLOptimization optim = new XMLOptimization();
        optim.setDbConnect("jdbc:sqlite:/Users/romansmelev/projects/rshmelev/chapter_008/src/main/java/ru/job4j/jdbc/test.db");
        optim.setN(1000000);
        JDBC jdbc = new JDBC(optim.dbConnect, optim.n);
        XmlXsltXml xmlObj = new XmlXsltXml();
        try {
            jdbc.createTable();
            jdbc.fillTable();
            Entries es = jdbc.tableToEntryColl();
            xmlObj.marshaller(es);
            xmlObj.xsltTransformation();
            xmlObj.summator();
        } catch (TransformerException | XMLStreamException | IOException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("Итого секунд: " + total / 1000);
    }
}
