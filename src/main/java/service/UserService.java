package service;

import DAO.UserDAO;
import exception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public User getClientById(long id) throws DBException {
        try {
            return getUserDAO().getClientById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User getClientByName(String name) {
        User client = null;
        try {
            client = getUserDAO().getClientByName(name);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return client;
    }

    public List<User> getAllClient() {
        List<User> list = null;
        try {
            list = getUserDAO().getAllBankClient();
        }
        catch (SQLException e) {
            e.getStackTrace();
        }
        return list;
    }


    public boolean deleteClient(String name) {
        return false;
    }

    public void addClient(User client) throws  SQLException {
        if (!clientExist(client.getName())) {
            getUserDAO().addClient(client);
        }
    }

    public boolean clientExist(String name) throws SQLException {
        if (getClientByName(name)!= null) {
            return true;
        }
        return false;
    }

    public boolean clientExist(String name, String password) {
        if (getUserDAO().validateClient(name, password)) {
            return true;
        }
        return false;
    }

    public void cleanUp() throws DBException {
        UserDAO dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public void createTable() throws DBException{
        UserDAO dao = getUserDAO();
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
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root").       //password
                    append("&serverTimezone=UTC");   //setup server time

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
