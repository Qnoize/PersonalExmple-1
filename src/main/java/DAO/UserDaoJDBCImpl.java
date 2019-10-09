package DAO;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static UserDaoJDBCImpl instance;
    private Connection connection;

    private UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public static UserDaoJDBCImpl getInstance(Connection connection) {
        if (instance == null) {
            instance = new UserDaoJDBCImpl(connection);
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void userEdit(User user){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.
                    prepareStatement("UPDATE user_table SET name=?, password=?, email=? where id=? ");
            preparedStatement.setLong(4, user.getId());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public User getUserById(long id){
        PreparedStatement preparedStatement = null;
        String name = null;
        String password = null;
        String email = null;
        try {
            preparedStatement = connection.
                    prepareStatement("SELECT * FROM user_table WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            name = resultSet.getString("name");
            password = resultSet.getString("password");
            email = resultSet.getString("email");
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(id, name, password, email);
    }
    @Override
    public boolean getUserByName(String name) {
        String sql = "select * from user_table where name = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet != null ){
                return true;
            }
            resultSet.next();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void addUser(User user){
        createTable();
        String sql = "INSERT INTO user_table (name, password, email) Values (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTable(){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("create table if not exists user_table (id bigint auto_increment, name varchar(256), password varchar(256), email varchar(256), primary key (id))");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteUser(long id){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM user_table WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
