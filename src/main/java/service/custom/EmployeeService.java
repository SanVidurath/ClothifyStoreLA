package service.custom;

import dto.Employee;
import entity.EmployeeEntity;
import javafx.collections.ObservableList;
import service.CrudService;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends CrudService<Employee, String> {
    ObservableList<Integer> getIds() throws SQLException;
    Employee search(Integer id) throws SQLException;

}
