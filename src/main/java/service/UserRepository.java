package service;

import DAO.userDAOJdbc;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {
    private static UserRepository userRepository;

    public static UserRepository getInstance(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public User getUserById(long id) throws DBException {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User getUserByName(String name) {
        User client = null;
        try {
            client = getUserDAO().getUserByName(name);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return client;
    }

    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            list = getUserDAO().getAllUsers();
        }
        catch (SQLException e) {
            e.getStackTrace();
        }
        return list;
    }

    public void userEdit(User user) throws SQLException {
        getUserDAO().userEdit(user);
    }
    public void deleteUser(long id) throws SQLException {
        getUserDAO().deleteUser(id);
    }

    public void addUser(User user) throws  SQLException {
        if (!userExist(user.getName())) {
            getUserDAO().addUser(user);
        }
    }

    public boolean userExist(String name) throws SQLException {
        if (getUserByName(name)!= null) {
            return true;
        }
        return false;
    }

    public boolean userExist(String name, String password) {
        if (getUserDAO().validateUser(name, password)) {
            return true;
        }
        return false;
    }

    public void cleanUp() throws DBException {
        userDAOJdbc dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public void createTable() throws DBException{
        userDAOJdbc dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("db_example?").
                    append("user=root&").
                    append("password=root").
                    append("&serverTimezone=UTC");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static userDAOJdbc getUserDAO() {
        return new userDAOJdbc(getMysqlConnection());
    }
}
