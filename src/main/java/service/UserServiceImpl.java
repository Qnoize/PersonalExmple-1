package service;

import DAO.UserDao;
import DAO.UserDaoFactory;
import model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private static UserDao userDao;

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null || userDao == null) {
            instance = new UserServiceImpl();
            userDao = UserDaoFactory.getInstance().getDAO();
        }
        return instance;
    }

    @Override
    public User getUserById(long id){
        return userDao.getById(id);
    }

    @Override
    public boolean getUserByName(String name){
        return userDao.getByName(name);
    }

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
    public void addUser(User user){
       if (userExist(user.getName())){
           userDao.add(user);
       }
    }

    public boolean userExist(String name) {
        if (getUserByName(name)) {
            return true;
        }
        return false;
    }
}
