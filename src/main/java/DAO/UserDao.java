package DAO;

import model.User;

public interface UserDao extends CrudDao<User> {
    boolean isExistUserByNameAndPassword(String name, String password);
    boolean isExistUserByName(String name);
    User getByName(String name);
}
