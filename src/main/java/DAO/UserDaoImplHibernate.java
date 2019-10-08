package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import model.User;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImplHibernate implements UserDao{

    private static UserDaoImplHibernate instance;
    private SessionFactory sessionFactory;

    private UserDaoImplHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserDaoImplHibernate getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new UserDaoImplHibernate(sessionFactory);
        }
        return instance;
    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        return (User) session.createQuery("from User where id = ?1")
                .setParameter(1, id)
                .list().get(0);
    }

    @Override
    public boolean getUserByName(String name) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> list = new ArrayList<>(query.list());
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void userEdit(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(getUserById(id));
        session.getTransaction().commit();
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
