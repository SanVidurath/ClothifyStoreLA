package service.custom.impl;

import db.DBConnection;
import dto.Employee;
import entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEEENTITY);
    @Override
    public boolean add(Employee employee) throws SQLException {
        EmployeeEntity employeeEntity = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDao.add(employeeEntity);
    }

    @Override
    public Employee search(String email) throws SQLException {
        EmployeeEntity searchedEmployeeEntity = employeeDao.search(email);
        if(searchedEmployeeEntity!=null){
            return new ModelMapper().map(searchedEmployeeEntity, Employee.class);
        }else{
            return null;
        }

    }

    @Override
    public List<Employee> getAll() throws SQLException {
        List<EmployeeEntity> employeeEntityList = employeeDao.getAll();
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeEntity employeeEntity:employeeEntityList){
            Employee map = new ModelMapper().map(employeeEntity, Employee.class);
            employeeList.add(map);
        }

        return employeeList;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        EmployeeEntity employeeEntity = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDao.update(employeeEntity);
    }

    @Override
    public boolean delete(String email) throws SQLException {
        return employeeDao.delete(email);
    }

}
