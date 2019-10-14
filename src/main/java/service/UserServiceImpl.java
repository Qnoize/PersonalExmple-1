package service;

import DAO.UserDao;
import DAO.UserDaoFactory;
import model.Role;
import model.User;
import model.UserRole;

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
    public User getUserById(long id){ return userDao.getById(id);}

    @Override
    public boolean getUserByName(String name, String password){ return userDao.getByName(name, password);}

    @Override
    public List<User> getAllUsers(){
        return userDao.getAll();
    }

    @Override
    public void userEdit(User user){
        userDao.edit(user);
    }

    @Override
    public void deleteUser(long id) { userDao.delete(id); }

    @Override
    public boolean userExistByName(String name){ return getByName(name);}

    @Override
    public boolean getByName(String name) { return userDao.getByName(name); }

    @Override
    public boolean userExist(String name, String password) {
        return getUserByName(name, password);
    }

    @Override
    public void addUser(User user){ if (!userExistByName(user.getName())){ userDao.add(user); } }

    @Override
    public UserRole getUserRole(String name) {
        return userDao.getUserRole(name);
    }
}
