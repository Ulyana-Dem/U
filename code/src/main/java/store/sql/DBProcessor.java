package store.sql;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBProcessor {
    private Connection connection;
    public static final String URL = "jdbc:mysql://localhost:3306/MySQL?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "7211297Adf";


    public DBProcessor() throws SQLException {
        DriverManager.registerDriver(new FabricMySQLDriver());
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public Connection getConnection() {
        return connection;
    }

}
