package service;

import java.util.List;

public interface Service <T> {
    <T>T getUserById(long id);
    boolean getUserByName(String name, String password);
    List<T> getAllUsers();
    void userEdit(T user);
    void deleteUser(long id);
    void addUser(T user);
    boolean userExist(String name, String password);
}
