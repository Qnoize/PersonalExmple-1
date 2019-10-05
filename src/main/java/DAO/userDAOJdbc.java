package DAO;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDAOJdbc {
    private Connection connection;

    public userDAOJdbc(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.
                prepareStatement("select * from user_table");
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
    public void userEdit(User user) throws SQLException{
        PreparedStatement preparedStatement = connection.
                prepareStatement("INSERT INTO user_table (name, password, email) Values (?, ?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public boolean validateUser(String name, String password) {
        try {
            User user = getUserByName(name);
            if (user.getPassword().equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("SELECT * FROM user_table WHERE id = ?");
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

    public User getUserByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("select * from user_table where name = ?");
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

    public void addUser(User user) throws SQLException {
        createTable();
        String sql = "INSERT INTO user_table (name, password, email) Values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists user_table (id bigint auto_increment, name varchar(256), password varchar(256), email varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS bank_client");
        stmt.close();
    }

    public void deleteUser(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("DELETE FROM user_table WHERE id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
