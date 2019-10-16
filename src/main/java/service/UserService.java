package service;

import model.User;
import model.UserRole;

public interface UserService extends Service<User>{
    UserRole getUserRole(String name);
    boolean userExist(String name, String password);
    boolean userExistByName(String name);
    boolean getByName(String name);
    boolean getUserByName(String name, String password);
}
