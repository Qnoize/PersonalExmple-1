package DAO;

import model.UserRole;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static UserDaoHibernateImpl instance;
    private SessionFactory sessionFactory;

    private UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserDaoHibernateImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new UserDaoHibernateImpl(sessionFactory);
        }
        return instance;
    }
    //language=SQL
    private final String SQL_SELECT_BY_ID = "FROM User WHERE user_id = :user_id";
    //language=SQL
    private final String SQL_SELECT_BY_NAME = "FROM User WHERE name = :name";
    //language=SQL
    private final String SQL_SELECT_BY_NAME_AND_PASS = "FROM User WHERE name = :name and password =:password";
    //language=SQL
    private final String SQL_SELECT_ALL = "FROM User";
    //language=SQL
    private final String SQL_SELECT_ROLE = "FROM UserRole WHERE user_id  =:user_id ";

    @Override
    public User getById(long user_id) {
        Session session = sessionFactory.openSession();

        User user = session.createQuery(SQL_SELECT_BY_ID, User.class)
                .setParameter("user_id", user_id)
                .getSingleResult();
        session.close();
        return user;
    }
    @Override
    public boolean getByName(String name, String password) {
        Session session = sessionFactory.openSession();
        try {
            User user = session.createQuery(SQL_SELECT_BY_NAME_AND_PASS, User.class)
                    .setParameter("name", name)
                    .setParameter("password", password)
                    .getSingleResult();
            if(user != null){
                return true;
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        session.close();
        return false;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> list = new ArrayList<>(session.createQuery(SQL_SELECT_ALL).list());
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
        return list;
    }

    @Override
    public void edit(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Override
    public void delete(long user_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //session.delete(getByRole(user_id));
        session.delete(session.get(User.class, user_id));
        //session.delete(getById(user_id));
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
    }

    public UserRole getByRole(long user_id) {
        Session session = sessionFactory.openSession();
        UserRole userRole =  session.createQuery(SQL_SELECT_ROLE, UserRole.class)
                .setParameter("user_id", user_id)
                .getSingleResult();
        session.close();
        return userRole;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
        addRole(user);
    }

    @Override
    public void addRole(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        UserRole userRole = new UserRole(user.getUser_id(), 1L);
        session.save(userRole);
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Override
    public UserRole getUserRole(String name) {
        Session session = sessionFactory.openSession();

            User user = session.createQuery(SQL_SELECT_BY_NAME, User.class)
                    .setParameter("name", name)
                    .getSingleResult();
            UserRole userRole = session.createQuery(SQL_SELECT_ROLE, UserRole.class)
                    .setParameter("user_id", user.getUser_id())
                    .getSingleResult();
            session.close();
            return userRole;
    }

    @Override
    public boolean getByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            User user = session.createQuery(SQL_SELECT_BY_NAME, User.class)
                    .setParameter("name", name)
                    .getSingleResult();
            if(user != null){
                return true;
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        session.close();
        return false;
    }
}
