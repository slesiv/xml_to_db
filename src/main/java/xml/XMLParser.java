package xml;

import dao.DirectoryDao;
import domain.Org;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class XMLParser {

    long timerParse = 0L;

    public void parseAndSendDB(String path) {
        Set<Org> orgs = new HashSet<>();
        DirectoryDao dao = new DirectoryDao();
        try (FileInputStream fis = new FileInputStream(path)) {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (xmlr.hasNext()) {
                int event = xmlr.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if ("org".equals(xmlr.getLocalName())) {
                        orgs.add(parseOrg(xmlr));
                        if (orgs.size() >= 1000) {
                            dao.insertOrgsPS(orgs);
                            orgs.clear();
                        }
                    }
                }
                if (event == XMLStreamConstants.END_DOCUMENT) {
                    dao.insertOrgsPS(orgs);
                    System.out.println();
                    System.out.println("parse: " + timerParse + " ms");
                    System.out.println("insert into DB: " + dao.timerInsert + " ms");
                    System.out.println("count errors: " + dao.countErr + " ms");
                }
            }
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private Org parseOrg(XMLStreamReader xmlr) throws XMLStreamException {
        long time = System.currentTimeMillis();
        Org org = new Org();
        while (xmlr.hasNext()) {
            int event = xmlr.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                switch (xmlr.getLocalName()) {
                    case "name" -> org.setName(xmlr.getElementText());
                    case "inn" -> org.setInn(Long.parseLong(xmlr.getElementText()));
                    case "ogrn" -> org.setOgrn(Long.parseLong(xmlr.getElementText()));
                    case "address" -> org.setAddress(xmlr.getElementText());
                    case "postcode" -> org.setPostcode(Integer.parseInt(xmlr.getElementText()));
                    case "openDate" -> org.setDateOpen(LocalDate.
                            parse(xmlr.getElementText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                }
            } else if (event == XMLStreamConstants.END_ELEMENT && "org".equals(xmlr.getLocalName())) {
                break;
            }
        }
        timerParse += System.currentTimeMillis() - time;

        return org;
    }
}