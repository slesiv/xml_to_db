package dao;

import domain.Org;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectoryDao {

    Connection con = getConnection(loadProperties("./src/main/resources/app.properties"));
    public long timerInsert = 0L;
    public int countErr = 0;

    public void insertOrgsPS(Set<Org> orgs) {
        long time = System.currentTimeMillis();
        try (PreparedStatement ps = con.prepareStatement("insert into orgs(name, inn, ogrn, address, postcode, open_date)values (?, ?, ?, ?, ?, ?)")) {
            con.setAutoCommit(false);
            for (Org org : orgs) {
                ps.setString(1, org.getName());
                ps.setLong(2, org.getInn());
                ps.setLong(3, org.getOgrn());
                ps.setString(4, org.getAddress());
                ps.setInt(5, org.getPostcode());
                ps.setDate(6, Date.valueOf(org.getDateOpen()));
                ps.addBatch();
            }
            try {
                ps.executeBatch();
                con.commit();
            } catch (BatchUpdateException ex) {
                con.rollback();
                Org orgDel = new Org(parseErrorForGetInn(ex.getMessage()));
                orgs.remove(orgDel);
                insertOrgsPS(orgs);
                System.out.printf("%s, ", ++countErr);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        timerInsert += System.currentTimeMillis() - time;
    }

    public void connectClose() {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private long parseErrorForGetInn(String str) {
        String result = "";
        Matcher matcher = Pattern.compile("\\(\\d+\\)").matcher(str);
        while (matcher.find()) {
            result = str.substring(matcher.start(), matcher.end());
        }
        result = result.substring(1, result.length() - 1);
        return Long.parseLong(result);
    }

    //For tests, used in Main
    public int deleteOrgs() {
        int result = 0;
        Properties prop = loadProperties("./src/main/resources/app.properties");
        try (Connection con = getConnection(prop);
             PreparedStatement ps = con.prepareStatement("DELETE FROM orgs")) {
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private Connection getConnection(Properties properties) {
        Connection con = null;
        try {
            Class.forName(properties.getProperty("postgres.driver"));
            con = DriverManager.getConnection(
                    properties.getProperty("postgres.url"),
                    properties.getProperty("postgres.user"),
                    properties.getProperty("postgres.password")
            );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    private static Properties loadProperties(String path) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}