package service;

import model.User;

public interface UserService extends Service<User>{
    boolean userExist(String name, String password);
    boolean userExistByName(String name);
    User getByName(String name);
}
