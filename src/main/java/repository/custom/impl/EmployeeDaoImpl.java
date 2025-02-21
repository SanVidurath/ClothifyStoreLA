package repository.custom.impl;

import db.DBConnection;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.EmployeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean add(EmployeeEntity entity) throws SQLException {
        String sql = "INSERT INTO Employees(name,address,email,phone_number,password) VALUES (?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,entity.getName());
        preparedStatement.setObject(2,entity.getAddress());
        preparedStatement.setObject(3,entity.getEmail());
        preparedStatement.setObject(4,entity.getPhoneNo());
        preparedStatement.setObject(5,entity.getPassword());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public EmployeeEntity search(String email) throws SQLException {
        EmployeeEntity employeeEntity = null;
        String sql = "Select * from employees where email='" + email + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            employeeEntity = new EmployeeEntity(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
        }
        return employeeEntity;
    }

    @Override
    public List<EmployeeEntity> getAll() throws SQLException {
        ArrayList<EmployeeEntity> employeeEntityArrayListList = new ArrayList<>();
        String sql = "Select * from employees";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            EmployeeEntity employeeEntity = new EmployeeEntity(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            employeeEntityArrayListList.add(employeeEntity);
        }
        return employeeEntityArrayListList;
    }

    @Override
    public boolean update(EmployeeEntity entity) throws SQLException {
        String sql = "Update employees set name=?,address=?,phone_number=?,password=? where email='" + entity.getEmail() + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, entity.getName());
        preparedStatement.setObject(2, entity.getAddress());
        preparedStatement.setObject(3, entity.getPhoneNo());
        preparedStatement.setObject(4, entity.getPassword());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String email) throws SQLException {
        String sql = "Delete from employees where email='"+email+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeUpdate(sql)>0;
    }




    @Override
    public ObservableList<Integer> getIds() throws SQLException {
        ObservableList<Integer> employeeIds = FXCollections.observableArrayList();
        List<EmployeeEntity> employeeEntityList = getAll();
        employeeEntityList.forEach(employeeEntity -> employeeIds.add(employeeEntity.getId()));
        return employeeIds;
    }

    @Override
    public EmployeeEntity search(Integer id) throws SQLException {
        EmployeeEntity employeeEntity = null;
        String sql = "Select * from employees where id='" + id + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            employeeEntity = new EmployeeEntity(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
        }
        return employeeEntity;
    }


}
