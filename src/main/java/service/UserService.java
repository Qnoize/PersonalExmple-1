package service;

import model.User;
import model.UserRole;

public interface UserService extends Service<User>{
    UserRole getUserRole(String name);
}
