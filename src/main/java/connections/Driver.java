package connections;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;

public class Driver {
    private static BasicDataSource dataSource;
    private static final String USERNAME = "*****";
    private static final String PASSWORD = "********";
    private static final String DB_URL = "jdbc:mysql:*********";

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(DB_URL);
            ds.setUsername(USERNAME);
            ds.setPassword(PASSWORD);
            dataSource = ds;
        }
        System.out.println("Created Connection");
        return dataSource;
    }


    public static synchronized Connection getConnection() {
        Connection conn = null;
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", USERNAME);
            connectionProps.put("password", PASSWORD);
            conn = DriverManager.getConnection(DB_URL, connectionProps);
            System.out.println("Created Connection");
        } catch (SQLException throwables) {
            System.out.println("failed to connect to: " + DB_URL);
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = Driver.getDataSource().getConnection();
        System.out.println(conn.getMetaData().getDatabaseProductName());
    }
}