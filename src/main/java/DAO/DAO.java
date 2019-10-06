package DAO;

import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    User getUserById(long id) throws DBException, SQLException;
    User getUserByName(String name) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    void userEdit(User user) throws SQLException;
    void deleteUser(long id) throws SQLException;
    void addUser(User user) throws  SQLException;
    boolean userExist(String name) throws SQLException;

}
