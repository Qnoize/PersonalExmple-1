package service;

import model.User;

public interface UserService extends Service<User>{
    boolean userExistByName(String name);
}
