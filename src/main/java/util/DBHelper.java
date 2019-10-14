package util;

import com.mysql.cj.jdbc.Driver;
import model.Role;
import model.User;
import model.UserRole;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static util.ReadProperties.readProperty;

public class DBHelper {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(UserRole.class);
        configuration.setProperty("hibernate.dialect", readProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", readProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", readProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", readProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", readProperty("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql", readProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", readProperty("hibernate.hbm2ddl.auto"));
        return configuration;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver((Driver) Class.forName(readProperty("jdbc.Driver")).newInstance());
            connection = DriverManager.getConnection(readProperty("jdbc.connection.url"),
                    readProperty("jdbc.connection.username"), readProperty("jdbc.connection.password"));
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return connection;
    }
    public static Connection getDefaultConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_example?serverTimezone=UTC",
                    "root", "root");
        } catch (SQLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return connection;
    }
}
