package DAO;

import org.hibernate.SessionFactory;
import util.DBHelper;
import util.ReadProperties;
import java.sql.Connection;

public class UserDaoFactory {
    public static String daoType = null;
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

            switch (ReadProperties.readProperty("connectToDB")){
                case ("Hibernate"):
                    {
                        daoType = "Hibernate";
                        SessionFactory sessionFactory = DBHelper.getSessionFactory();
                        userDao = UserDaoHibernateImpl.getInstance(sessionFactory);
                        break;
                    }
                case ("JDBC"):
                    {
                        daoType = "JDBC";
                        Connection connection = DBHelper.getConnection();
                        userDao = UserDaoJDBCImpl.getInstance(connection);
                    }
                default:
                    {
                        daoType = "default - JDBC";
                        Connection connection = DBHelper.getDefaultConnection();
                        userDao = UserDaoJDBCImpl.getInstance(connection);
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDao;
    }
}
