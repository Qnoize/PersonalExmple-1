package DAO;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final String SQL_GET_BY_ID = "SELECT * FROM user_table WHERE id = ?";
    //language=SQL
    private final String SQL_GET_BY_NAME = "SELECT * FROM user_table WHERE name = ?";
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM user_table";
    //language=SQL
    private final String SQL_EDIT = "UPDATE user_table SET name=?, password=?, email=? WHERE id=?";
    //language=SQL
    private final String SQL_ADD = "INSERT INTO user_table (name, password, email) VALUES (?, ?, ?)";
    //language=SQL
    private final String SQL_CREATE_TABLE = "CREATE TABLE if NOT EXIST user_table (id bigint auto_increment, NAME VARCHAR(256), password VARCHAR(256), email VARCHAR(256), PRIMARY KEY (id))";
    //language=SQL
    private final String SQL_DROP_TABLE = "DELETE FROM user_table WHERE id=?";

    @Override
    public List<User> getAll(){
        createTable();
        List<User> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
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

    public void edit(User user){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_EDIT);
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
    public Optional<User> getById(long id){
        PreparedStatement preparedStatement = null;
        String name = null;
        String password = null;
        String email = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                name = resultSet.getString("name");
                password = resultSet.getString("password");
                email = resultSet.getString("email");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(new User(id, name, password, email));
    }

    @Override
    public boolean getByName(String name) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_BY_NAME);
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

    public void add(User user){
        createTable();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD);
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
            stmt.execute(SQL_CREATE_TABLE);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(long id){
        PreparedStatement preparedStatement = null;
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
