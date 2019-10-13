package DAO;

import model.User;

public interface UserDao extends CrudDao<User> {
    void edit(User user);
    void add(User user);
    boolean getByName(String name);
}
