package DAO;

import model.User;
import model.UserRole;

public interface UserDao extends CrudDao<User> {
    void edit(User user);
    void add(User user);
    void addRole(User user);
    UserRole getUserRole(String name);
}
