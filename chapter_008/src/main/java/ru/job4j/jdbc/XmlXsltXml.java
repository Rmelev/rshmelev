package ru.job4j.jdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Integer.valueOf;

/**
 * Transformations: xml -> xslt -> xml.
 */
public class XmlXsltXml {
    /**
     * write to xml.
     * @param es - collection for write to xml file.
     */
    void marshaller(Entries es) {
        long start = System.currentTimeMillis();
        try {
            File file = new File("/Users/romansmelev/projects/rshmelev/chapter_008/src/main/java/ru/job4j/jdbc/1.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(es, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("marshaller: " + total);
    }

    /**
     * transformation with XSLT to another representation.
     * @throws TransformerException - exception.
     */
    public void xsltTransformation() throws TransformerException {
        long start = System.currentTimeMillis();
        String xmlFile = "./chapter_008/src/main/java/ru/job4j/jdbc/1.xml";
        String xslFile = "./chapter_008/src/main/java/ru/job4j/jdbc/trans.xml";
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(xslFile));
        Transformer transformer = factory.newTransformer(xslt);
        Source text = new StreamSource(new File(xmlFile));
        transformer.transform(text, new StreamResult(new File("./chapter_008/src/main/java/ru/job4j/jdbc/2.xml")));
        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("xsltTransformation: " + total);
    }

    /**
     * calculate sum of all elelments.
     * @throws XMLStreamException - XMLStreamException.
     * @throws IOException - IOException.
     */
    void summator() throws XMLStreamException, IOException {
        long start = System.currentTimeMillis();
        InputStream in = new FileInputStream("./chapter_008/src/main/java/ru/job4j/jdbc/2.xml");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(in);
        long sum = 0;
        try {
            int event;
            while (reader.hasNext()) {
                event = reader.getEventType();
                if (event == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("entry")) {
                    sum += valueOf(reader.getAttributeValue(0));
                }
                reader.next();
            }
        } finally {
            reader.close();
        }
        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("summator: " + total);
        System.out.println("sum: " + sum);
    }
}
