package DAO;

import model.Role;
import model.User;
import model.UserRole;

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
    //language=SQL
    private final String SQL_GET_BY_ID = "SELECT * FROM user_table WHERE user_id = ?";
    //language=SQL
    private final String SQL_GET_BY_NAME = "SELECT * FROM user_table WHERE name = ?";
    //language=SQL
    private final String SQL_GET_ROLE = "SELECT * FROM user_role WHERE user_id = ?";
    //language=SQL
    private final String SQL_GET_BY_NAME_AND_PASS = "SELECT * FROM user_table WHERE name = ? AND password = ?";
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM user_table";
    //language=SQL
    private final String SQL_EDIT = "UPDATE user_table SET name = ?, password = ?, email = ? WHERE user_id = ?";
    //language=SQL
    private final String SQL_ADD = "INSERT INTO user_table (name, password, email) VALUES (?, ?, ?)";
    //language=SQL
    private final String SQL_ADD_ROLE = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
    //language=SQL
    private final String SQL_CREATE_TABLE = "CREATE TABLE if NOT EXISTS user_table (id bigint auto_increment, NAME VARCHAR(256), password VARCHAR(256), email VARCHAR(256), PRIMARY KEY (id))";
    //language=SQL
    private final String SQL_DROP_TABLE = "DELETE FROM user_table WHERE user_id = ?";

    @Override
    public List<User> getAll(){
        createTable();
        List<User> list = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
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
    public void edit(User user){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_EDIT);
            preparedStatement.setLong(4, user.getUser_id());
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
    public User getById(long id){
        PreparedStatement preparedStatement;
        String name;
        String password;
        String email;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                name = resultSet.getString("name");
                password = resultSet.getString("password");
                email = resultSet.getString("email");
                user = new User(id, name, password, email);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean getByName(String name, String password) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_BY_NAME_AND_PASS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void add(User user){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            addRole(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRole(User user) {
        PreparedStatement preparedStatement;
        try {
            UserRole userRole = new UserRole(user.getUser_id(), 1L);
            preparedStatement = connection.prepareStatement(SQL_ADD_ROLE);
            preparedStatement.setLong(1, userRole.getUser_id());
            preparedStatement.setLong(2, userRole.getRole_id());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserRole getUserRole(String name) {
        PreparedStatement preparedStatement;
        UserRole userRole = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if(resultSet.next()){
                Long id = resultSet.getLong("user_id");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                user = new User(id, name, password, email);
            }
            resultSet.close();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(SQL_GET_ROLE);
            preparedStatement.setLong(1, user.getUser_id());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Long idu = resultSet.getLong("user_id");
                Long idr = resultSet.getLong("role_id");
                userRole = new UserRole(idu, idr);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRole;
    }


    @Override
    public boolean getByName(String name) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTable(){
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.execute(SQL_CREATE_TABLE);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(long id){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_DROP_TABLE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
