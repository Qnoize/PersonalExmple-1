package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User getUserById(long id);
    User getUserByName(String name);
    List<User> getAllUsers();
    void userEdit(User user);
    void deleteUser(long id);
    void addUser(User user);
    boolean userExist(String name);
}
