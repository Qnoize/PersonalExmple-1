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
            System.out.println("Ошибка верификации по логину и паролю");
            e.getStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
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
            System.out.println("Ошибка получения всех пользователей");
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public void edit(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(session.get(User.class, user.getUser_id()));
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("Ошибка редактирования пользователя");
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(long user_id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.get(User.class, user_id));
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("Ошибка удаения пользователя");
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.save(new UserRole(user.getUser_id(), 1L));
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("Ошибка добавления пользователя");
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
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
            System.out.println("Ошибка нахождения по имени пользователя");
            e.getStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return false;
    }
}
