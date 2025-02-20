package service.custom;

import dto.Employee;
import service.CrudService;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends CrudService<Employee, String> {

}
