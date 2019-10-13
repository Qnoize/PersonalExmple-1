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
    public void addUser(User user){
     if (!userExistByName(user.getName())){
           userDao.add(user);
      }
    }
    public boolean userExistByName(String name){ return getByName(name);}

    private boolean getByName(String name) { return userDao.getByName(name); }

    public boolean userExist(String name, String password) {
        return getUserByName(name, password);
    }
}
