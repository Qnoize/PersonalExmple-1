package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import static util.ReadSettings.readProperty;

public class DBHelperHibernate {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = createSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    private static SessionFactory createSessionFactory() throws Exception {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    private static Configuration getConfiguration() throws Exception {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", readProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", readProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", readProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", readProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", readProperty("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql", readProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", readProperty("hibernate.hbm2ddl.auto"));
        return configuration;
    }
}
