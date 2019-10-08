package service;

import model.User;
import java.util.List;

public interface UserService {
    User getUserById(long id);
    boolean getUserByName(String name);
    List<User> getAllUsers();
    void userEdit(User user);
    void deleteUser(long id);
    void addUser(User user);
    boolean userExist(String name);
}