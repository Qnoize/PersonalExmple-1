package service;

import DAO.DAO;
import DAO.userDAOJdbc;
import exception.DBException;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements DAO{
    private static DAO dao;
    private static UserRepository userRepository;

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }
    @Override
    public User getUserById(long id) throws DBException {
        try {
            return DBHelper.getUserDAO().getUserById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    @Override
    public User getUserByName(String name) {
        User client = null;
        try {
            client = DBHelper.getUserDAO().getUserByName(name);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return client;
    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            list = DBHelper.getUserDAO().getAllUsers();
        }
        catch (SQLException e) {
            e.getStackTrace();
        }
        return list;
    }
    @Override
    public void userEdit(User user) throws SQLException {
        DBHelper.getUserDAO().userEdit(user);
    }
    @Override
    public void deleteUser(long id) throws SQLException {
        DBHelper.getUserDAO().deleteUser(id);
    }
    @Override
    public void addUser(User user) throws  SQLException {
        if (!userExist(user.getName())) {
            DBHelper.getUserDAO().addUser(user);
        }
    }
    @Override
    public boolean userExist(String name) throws SQLException {
        if (getUserByName(name)!= null) {
            return true;
        }
        return false;
    }

}
