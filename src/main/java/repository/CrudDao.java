package repository;

import entity.EmployeeEntity;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T,ID> extends SuperDao{
    boolean add(T entity) throws SQLException;
    T search(ID id) throws SQLException;

    List<T> getAll() throws SQLException;

    boolean update(T entity) throws SQLException;

    boolean delete(ID id) throws SQLException;
}
