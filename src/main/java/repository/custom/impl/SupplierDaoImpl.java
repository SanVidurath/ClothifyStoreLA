package repository.custom.impl;

import db.DBConnection;
import entity.EmployeeEntity;
import entity.SupplierEntity;
import repository.custom.SupplierDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean add(SupplierEntity entity) throws SQLException {
        String sql = "Insert into suppliers(name,company,email,phone_number) values (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, entity.getName());
        preparedStatement.setObject(2, entity.getCompany());
        preparedStatement.setObject(3, entity.getEmail());
        preparedStatement.setObject(4, entity.getPhoneNo());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public SupplierEntity search(String email) throws SQLException {
        SupplierEntity supplierEntity = null;
        String sql = "Select * from suppliers where email='" + email + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            supplierEntity = new SupplierEntity(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        }
        return supplierEntity;
    }

    @Override
    public List<SupplierEntity> getAll() throws SQLException {
        ArrayList<SupplierEntity> supplierEntityArrayListList = new ArrayList<>();
        String sql = "Select * from suppliers";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            SupplierEntity supplierEntity = new SupplierEntity(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            supplierEntityArrayListList.add(supplierEntity);
        }
        return supplierEntityArrayListList;
    }

    @Override
    public boolean update(SupplierEntity entity) throws SQLException {
        String sql = "Update suppliers set name=?,company=?,phone_number=? where email='" + entity.getEmail() + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, entity.getName());
        preparedStatement.setObject(2, entity.getCompany());
        preparedStatement.setObject(3, entity.getPhoneNo());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String email) throws SQLException {
        String sql = "Delete from suppliers where email='"+email+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeUpdate(sql)>0;
    }
}
