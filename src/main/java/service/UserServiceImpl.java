package service;

import DAO.UserDao;
import DAO.UserDaoFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private static UserDao userDao;

    private UserServiceImpl() { }

    public static UserService getInstance() {
        if (instance == null || userDao == null) {
            instance = new UserServiceImpl();
            userDao = UserDaoFactory.getInstance().getDAO();
        }
        return instance;
    }

    @Override
    public User getUserById(long user_id){ return userDao.getById(user_id); }

    @Override
    public List<User> getAllUsers(){ return userDao.getAll(); }

    @Override
    public void userEdit(User user){ userDao.edit(user); }

    @Override
    public void deleteUser(long user_id) {
        userDao.delete(user_id);
    }

    @Override
    public boolean userExistByName(String name){
        return userDao.isExistUserByName(name);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public boolean userExist(String name, String password) { return userDao.isExistUserByNameAndPassword(name, password); }

    @Override
    public void addUser(User user) {
        if (!userExistByName(user.getName())) {
            userDao.add(user);
        }
    }
}
