package util;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static util.ReadSettings.readProperty;

public class DBHelperJDBC {

    public static Connection getJDBCConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            connection = DriverManager.getConnection(readProperty("jdbc.connection.url"),
                    readProperty("jdbc.connection.user"),
                    readProperty("jdbc.connection.password"));
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return connection;
    }
}
