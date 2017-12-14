package ru.job4j.testtask;

import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Test order book.
 */
public class OrderBookTest {
    /**
     * Test.
     * @throws XMLStreamException - XMLStreamException.
     * @throws IOException - IOException.
     */
    @Test
    public void whenStartWithRawDataThenPrintAllBookOrders() throws XMLStreamException, IOException {
        long start = System.nanoTime();
        Parser parser = new Parser();
        parser.importUrl("/Users/romansmelev/orders.xml");
        long finish = System.nanoTime();
        System.out.println(finish - start);
    }
}