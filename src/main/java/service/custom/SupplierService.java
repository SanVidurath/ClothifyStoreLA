package service.custom;

import dto.Employee;
import dto.Supplier;
import javafx.collections.ObservableList;
import service.CrudService;
import service.SuperService;

import java.sql.SQLException;

public interface SupplierService extends CrudService<Supplier, String> {
    ObservableList<Integer> getIds() throws SQLException;
    Supplier search(Integer id) throws SQLException;
}
