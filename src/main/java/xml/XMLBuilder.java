package xml;

import util.RandomData;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XMLBuilder {

    public void buildFileXml(String path) {
        try (BufferedWriter fos = new BufferedWriter(new FileWriter(path))) {
            XMLStreamWriter xmlw = XMLOutputFactory.newInstance().createXMLStreamWriter(fos);
            xmlw.writeStartDocument();
            xmlw.writeStartElement("orgs");
            for (int i = 0; i < 10000000; i++) {
                xmlw.writeStartElement("org");
                writeElement(xmlw, "name", RandomData.getRandomStringChar(8));
                writeElement(xmlw, "inn", RandomData.getInnWithNotDuplicate());
                writeElement(xmlw, "ogrn", RandomData.getRandomStringNum(13));
                writeElement(xmlw, "address", RandomData.getRandomStringChar(50));
                writeElement(xmlw, "postcode", RandomData.getRandomStringNum(6));
                writeElement(xmlw, "openDate", RandomData.getRandomDate());
                xmlw.writeEndElement();
            }
            xmlw.writeEndElement();
            xmlw.writeEndDocument();
            xmlw.flush();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private void writeElement(XMLStreamWriter xmlw, String name, String value) throws XMLStreamException {
        xmlw.writeStartElement(name);
        xmlw.writeCharacters(value);
        xmlw.writeEndElement();
    }
}