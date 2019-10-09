package DAO;

import org.hibernate.SessionFactory;
import util.DBHelper;
import util.ReadProperties;
import java.sql.Connection;

public class UserDaoFactory {
    private static UserDaoFactory instance;
    private static UserDao userDao;
    private UserDaoFactory() {
    }
    public static UserDaoFactory getInstance() {
        if (instance == null) {
            instance = new UserDaoFactory();
        }
        return instance;
    }

    public UserDao getDAO() {
        try {
            if (ReadProperties.readProperty("connectToDB").equalsIgnoreCase("Hibernate")) {
                SessionFactory sessionFactory = DBHelper.getSessionFactory();
                userDao = UserDaoHibernateImpl.getInstance(sessionFactory);
            } else if (ReadProperties.readProperty("connectToDB").equalsIgnoreCase("JDBC")) {
                Connection connection = DBHelper.getConnection();
                userDao = UserDaoJDBCImpl.getInstance(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDao;
    }
}
