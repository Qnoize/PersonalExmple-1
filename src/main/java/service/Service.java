package service;

import java.util.List;

public interface Service <T> {
    <T>T getUserById(long user_id);
    boolean getUserByName(String name, String password);
    List<T> getAllUsers();
    void userEdit(T user);
    void deleteUser(long user_id);
    void addUser(T user);
    boolean userExist(String name, String password);
    boolean userExistByName(String name);
    boolean getByName(String name);
}
