import dao.DirectoryDao;
import xml.XMLBuilder;
import xml.XMLParser;

public class Main {
    public static void main(String[] args) {

        DirectoryDao dao = new DirectoryDao();
        System.out.println("deleted rows: " + dao.deleteOrgs());

        String path = "orgsData.xml";

        long time = System.currentTimeMillis();
        XMLBuilder builder = new XMLBuilder();
        builder.buildFileXml(path);
        time = System.currentTimeMillis() - time;

        XMLParser parser = new xml.XMLParser();
        parser.parseAndSendDB(path);

        System.out.println("build " + time);
    }
}