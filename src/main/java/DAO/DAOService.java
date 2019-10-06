package DAO;

import model.User;
import java.sql.SQLException;
import java.util.List;

public interface DAOService {
    User getUserById(long id) throws SQLException;
    User getUserByName(String name) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    void userEdit(User user) throws SQLException;
    void deleteUser(long id) throws SQLException;
    void addUser(User user) throws SQLException;
}
