package service;

import dto.Employee;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<T,ID> extends SuperService{
    boolean add(T service) throws SQLException;
    T search(ID id) throws SQLException;
    List<T> getAll() throws SQLException;
    boolean update(T service) throws SQLException;
    boolean delete(ID id) throws SQLException;
}
