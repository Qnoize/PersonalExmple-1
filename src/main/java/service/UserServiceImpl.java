package service;

import DAO.UserDao;
import model.User;
import util.DBHelper;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao dao = DBHelper.getUserDAO();
    private static UserServiceImpl userServiceImpl;

    public static UserServiceImpl getInstance(){
        if(userServiceImpl == null){
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User getUserById(long id){
        return dao.getUserById(id);
    }

    @Override
    public boolean getUserByName(String name){
        return dao.getUserByName(name);
    }

    @Override
    public List<User> getAllUsers(){
        return dao.getAllUsers();
    }

    @Override
    public void userEdit(User user){
        dao.userEdit(user);
    }

    @Override
    public void deleteUser(long id) { dao.deleteUser(id); }

    @Override
    public void addUser(User user){
       if (userExist(user.getName())){
            dao.addUser(user);
       }
    }

    public boolean userExist(String name) {
        if (getUserByName(name)) {
            return true;
        }
        return false;
    }
}
