package service;

import DAO.DAOService;
import model.User;
import util.DBHelper;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements DAOService {
    private static DAOService dao;
    private static UserRepository userRepository;

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    @Override
    public User getUserById(long id){
        try {
            return DBHelper.getUserDAO().getUserById(id);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByName(String name){
        User client = null;
        try {
            client = DBHelper.getUserDAO().getUserByName(name);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return client;
    }
    @Override
    public List<User> getAllUsers(){
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
    public void userEdit(User user){
        try {
            DBHelper.getUserDAO().userEdit(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteUser(long id) {
        try {
            DBHelper.getUserDAO().deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addUser(User user){
        try {
            if (!userExist(user.getName())) {
                DBHelper.getUserDAO().addUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean userExist(String name) {
        if (getUserByName(name)!= null) {
            return true;
        }
        return false;
    }

}
