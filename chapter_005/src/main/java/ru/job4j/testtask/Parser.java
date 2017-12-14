package ru.job4j.testtask;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/**
 * parser xml file.
 */
public class Parser {
    /**
     * book of orderbooks.
     */
    private Map<String, HashMap<String, Order>> books = new HashMap<>();

    /**
     * import all orders and delete cancelled orders. Prepare valid list of orders.
     * @param urlString - address for export.
     * @throws XMLStreamException - XMLStreamException.
     * @throws IOException - IOException.
     */
    void importUrl(String urlString) throws XMLStreamException, IOException {
        InputStream in = new FileInputStream(urlString);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(in);
        try {
            int event;
            while (reader.hasNext()) {
                event = reader.getEventType();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (reader.getLocalName().equals("AddOrder")) {
                        addOrder(reader);
                    } else if (reader.getLocalName().equals("DeleteOrder")) {
                        delOrder(reader);
                    }
                }
                reader.next();
            }
        } finally {
            reader.close();
        }
        processingEachBook();
    }

    /**
     * add order with tag "AddOrder" in map of orders.
     * @param parser - parser.
     */
    private void addOrder(XMLStreamReader parser) {
        String bookName = parser.getAttributeValue(0);
        HashMap<String, Order> book = books.get(bookName);
        if (book == null) {
            book = new HashMap<String, Order>();
        }

        book.put(parser.getAttributeValue(4), new Order(
                parser.getAttributeValue(4), parser.getAttributeValue(1).equals("SELL"),
                Double.valueOf(parser.getAttributeValue(2)),
                Integer.valueOf(parser.getAttributeValue(3))
        ));
        books.put(bookName, book);
    }

    /**
     * delete order with tag "DeleteOrder" from map of orders.
     * @param parser - parser.
     */
    private void delOrder(XMLStreamReader parser) {
        HashMap<String, Order> book = books.get(parser.getAttributeValue(0));
        String id = parser.getAttributeValue(1); // потому что в строке удаления всего 2 атрибута.
        book.remove(id);
    }

    /**
     * start operations with all books of orders.
     */
    private void processingEachBook() {
        Set<Map.Entry<String, HashMap<String, Order>>> set = books.entrySet();
        List<String> keys = new ArrayList<>(books.keySet());
        OrderBooksCreation orderBookRead = new OrderBooksCreation();
        int i = 0;
        for (Map.Entry<String, HashMap<String, Order>> tempBook : set) {
            System.out.println(keys.get(i++));
            orderBookRead.divideToBuyAndSell(tempBook.getValue());
        }
    }

}
