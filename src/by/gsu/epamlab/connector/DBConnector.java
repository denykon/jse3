package by.gsu.epamlab.connector;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 15.01.2015.
 * @version 1.0
 */
public enum DBConnector {
    CONNECTOR;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/results";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    private final Connection connection;

    DBConnector() {
        this.connection = Connect(DB_URL, USER, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection Connect(String dbUrl, String user, String password) {
        Connection con = null;
        try {
            String driver = DRIVER;
            Class.forName(driver);
            con = DriverManager.getConnection(dbUrl, user, password);

        } catch (Exception e) {
            System.err.println("Connection failed. Please, check login and password.");
            System.exit(1);
        }
        return con;
    }
}
