package repository.custom;

import db.DBConnection;
import entity.EmployeeEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeDao extends CrudDao<EmployeeEntity,String> {
    ObservableList<Integer> getIds() throws SQLException;
    EmployeeEntity search(Integer id) throws SQLException;
}
