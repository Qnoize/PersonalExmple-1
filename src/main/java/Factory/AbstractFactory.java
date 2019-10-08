package Factory;

import DAO.UserDao;
import DAO.UserDaoImplHibernate;
import DAO.UserDaoImplJdbc;
import org.hibernate.SessionFactory;
import util.DBHelperHibernate;
import util.DBHelperJDBC;
import util.ReadSettings;
import java.sql.Connection;

public class AbstractFactory {
    private static AbstractFactory instance;
    private static UserDao userDao;
    private AbstractFactory() {
    }
    public static AbstractFactory getInstance() {
        if (instance == null) {
            instance = new AbstractFactory();
        }
        return instance;
    }

    public UserDao getDAO() {
        try {
            if (ReadSettings.readProperty("connectToDB").equalsIgnoreCase("Hibernate")) {
                SessionFactory sessionFactory = DBHelperHibernate.getSessionFactory();
                userDao = UserDaoImplHibernate.getInstance(sessionFactory);
            } else if (ReadSettings.readProperty("connectToDB").equalsIgnoreCase("JDBC")) {
                Connection connection = DBHelperJDBC.getJDBCConnection();
                userDao = UserDaoImplJdbc.getInstance(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDao;
    }
}
