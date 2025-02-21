package repository.custom.impl;

import db.DBConnection;
import entity.OrderDetailEntity;
import repository.custom.OrderDetailDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {

    public boolean add(List<OrderDetailEntity> orderDetailList) throws SQLException {
        for (OrderDetailEntity orderDetailEntity : orderDetailList) {
            boolean isAddedOrderDetail = add(orderDetailEntity);
            if(!isAddedOrderDetail){
                return false;
            }
        }
        return true;
    }

    public boolean add(OrderDetailEntity orderDetailEntity) throws SQLException {
        String sql = "Insert into orderdetail values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, new OrderDaoImpl().getLastId());
        preparedStatement.setObject(2, orderDetailEntity.getProductCode());
        preparedStatement.setObject(3, orderDetailEntity.getUnitPrice());
        preparedStatement.setObject(4, orderDetailEntity.getQuantityPurchased());
        return preparedStatement.executeUpdate() > 0;
    }

    public OrderDetailEntity getOrderDetail(Object orderId, Object productCode) throws SQLException {
        OrderDetailEntity orderDetailEntity = null;
        String sql = "Select * from orderdetail where order_id='"+orderId+"' and product_code='"+productCode+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()){
            orderDetailEntity = new OrderDetailEntity(
                    Integer.parseInt(resultSet.getString(1)),
                    Integer.parseInt(resultSet.getString(2)),
                    Double.parseDouble(resultSet.getString(3)),
                    Integer.parseInt(resultSet.getString(4))
            );
        }
        return orderDetailEntity;
    }

    public List<OrderDetailEntity> getAll() throws SQLException {
        List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();
        String sql = "Select * from orderdetail";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while(resultSet.next()){
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity(
                    Integer.parseInt(resultSet.getString(1)),
                    Integer.parseInt(resultSet.getString(2)),
                    Double.parseDouble(resultSet.getString(3)),
                    Integer.parseInt(resultSet.getString(4))
            );
            orderDetailEntityList.add(orderDetailEntity);
        }
        return orderDetailEntityList;
    }

}
