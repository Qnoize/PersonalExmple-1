package DAO;

import model.User;
import model.UserRole;

public interface UserDao extends CrudDao<User> {
    boolean getByName(String name, String password);
    UserRole getUserRole(String name);
    boolean getByName(String name);
}
