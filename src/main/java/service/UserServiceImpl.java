package service;

import DAO.DAOService;
import model.User;
import util.DBHelper;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static DAOService dao = DBHelper.getUserDAO();
    private static UserServiceImpl userServiceImpl;

    public static UserServiceImpl getInstance(){
        if(userServiceImpl == null){
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User getUserById(long id){
        try {
            return dao.getUserById(id);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByName(String name){
        User client = null;
        try {
            client = dao.getUserByName(name);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return client;
    }
    @Override
    public List<User> getAllUsers(){
        List<User> list = null;
        try {
            list = dao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void userEdit(User user){
        try {
            dao.userEdit(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteUser(long id) {
        try {
            dao.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addUser(User user){
        try {
            if (!userExist(user.getName())) {
                dao.addUser(user);
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
