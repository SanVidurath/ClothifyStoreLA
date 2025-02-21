package repository.custom;

import entity.EmployeeEntity;
import entity.SupplierEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

import java.sql.SQLException;

public interface SupplierDao extends CrudDao<SupplierEntity, String> {
    ObservableList<Integer> getIds() throws SQLException;
    SupplierEntity search(Integer id) throws SQLException;
}
