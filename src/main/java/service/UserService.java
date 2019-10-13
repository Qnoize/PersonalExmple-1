package service;

import model.Role;
import model.User;

public interface UserService extends Service<User>{
    Role getUserRole(String name);
}
