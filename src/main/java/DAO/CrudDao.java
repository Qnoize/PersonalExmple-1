package DAO;

import java.util.List;

public interface CrudDao<T> {
    <T>T getById(long id);
    List<T> getAll();
    void delete(long id);
    void edit(T user);
    void add(T user);
}
