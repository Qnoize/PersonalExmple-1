package DAO;

import model.Role;
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
    private final String SQL_SELECT_BY_ID = "FROM User WHERE id = :id";
    //language=SQL
    private final String SQL_SELECT_BY_NAME = "FROM User WHERE name = :name";
    //language=SQL
    private final String SQL_SELECT_BY_NAME_AND_PASS = "FROM User WHERE name = :name and password =:password";
    //language=SQL
    private final String SQL_SELECT_ALL = "FROM User";
    //language=SQL
    private final String SQL_SELECT_ALL_ROLE = "FROM Role";
    //language=SQL
    private final String SQL_SELECT_ROLE = "FROM Role WHERE id_owner  =:id_owner ";
    @Override
    public User getById(long id) {
        Session session = sessionFactory.openSession();
        return session.createQuery(SQL_SELECT_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
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
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(getById(id));
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
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
        Role role = new Role("user", user.getId());
        session.save(role);
        try {
            session.getTransaction().commit();
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();

    }

    @Override
    public Role getUserRole(String name) {
        Session session = sessionFactory.openSession();

            User user = session.createQuery(SQL_SELECT_BY_NAME, User.class)
                    .setParameter("name", name)
                    .getSingleResult();
            Role role = session.createQuery(SQL_SELECT_ROLE, Role.class)
                    .setParameter("id_owner", user.getId())
                    .getSingleResult();
            System.out.println(user);
            System.out.println(role);
            return role;

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
        return false;
    }
}
