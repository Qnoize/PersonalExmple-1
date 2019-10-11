package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import model.User;
import org.hibernate.query.Query;
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
    private final String SQL_SELECT_ALL = "FROM User";

    public User getById(long id) {
        Session session = sessionFactory.openSession();
        return session.createQuery(SQL_SELECT_BY_ID, User.class).setParameter("id", id).list().get(0);
    }

    public boolean getByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(SQL_SELECT_BY_NAME);
        if (query.setParameter("name", name).list().isEmpty()){
            return true;
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
    }
}
