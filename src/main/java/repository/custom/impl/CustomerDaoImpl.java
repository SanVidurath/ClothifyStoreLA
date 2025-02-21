package repository.custom.impl;

import db.DBConnection;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean add(CustomerEntity entity) throws SQLException {
        String sql = "Insert into customers(name,email,phone_number) values(?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,entity.getName());
        preparedStatement.setObject(2,entity.getEmail());
        preparedStatement.setObject(3,entity.getPhoneNumber());
        return preparedStatement.executeUpdate()>0;
    }

    @Override
    public CustomerEntity search(Integer id) throws SQLException {
        CustomerEntity customerEntity=null;
        String sql = "Select * from customers where id='"+id+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()){
            customerEntity = new CustomerEntity(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return customerEntity;
    }

    @Override
    public List<CustomerEntity> getAll() throws SQLException {
        ArrayList<CustomerEntity> customerList = new ArrayList<>();
        String sql = "Select * from customers";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            CustomerEntity customer = new CustomerEntity(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public boolean update(CustomerEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        return false;
    }

    @Override
    public ObservableList<Integer> getIds() throws SQLException {
        ObservableList<Integer> customerIds = FXCollections.observableArrayList();
        List<CustomerEntity> customerEntityList = getAll();
        customerEntityList.forEach(customerEntity -> customerIds.add(customerEntity.getId()));
        return customerIds;
    }
}
