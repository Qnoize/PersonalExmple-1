package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    public List<User> getAllBankClient() throws SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from bank_client");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String pass = resultSet.getString("password");
            String email = resultSet.getString("email");
            User user = new User(id, name, pass, email);
            list.add(user);
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }

    public boolean validateClient(String name, String password) {
        try {
            User user = getClientByName(name);
            if (user.getPassword().equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getClientById(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank_client WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        resultSet.close();
        preparedStatement.close();
        return new User(name, password, email);
    }

    public long getClientIdByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bank_client WHERE name = ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Long id = resultSet.getLong("id");
        resultSet.close();
        preparedStatement.close();
        return id;
    }

    public User getClientByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from bank_client where name = ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String names = resultSet.getNString(2);
        String password = resultSet.getNString(3);
        String email = resultSet.getNString(4);
        resultSet.close();
        preparedStatement.close();
        return new User(names, password, email);
    }

    public void addClient(User user) throws SQLException {
        String sql = "INSERT INTO bank_client (name, password) Values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists bank_client (id bigint auto_increment, name varchar(256), password varchar(256), money bigint, primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS bank_client");
        stmt.close();
    }
}
