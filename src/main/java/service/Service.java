package service;

import java.util.List;

public interface Service <T> {
    <T>T getUserById(long user_id);
    List<T> getAllUsers();
    void userEdit(T user);
    void deleteUser(long user_id);
    void addUser(T user);

}
