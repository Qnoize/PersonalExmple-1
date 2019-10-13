package DAO;

import model.Role;
import model.User;

public interface UserDao extends CrudDao<User> {
    void edit(User user);
    void add(User user);
    void addRole(User user);
    Role getUserRole(String name);
}
