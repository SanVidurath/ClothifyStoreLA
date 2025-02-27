package repository.custom.impl;

import db.DBConnection;
import entity.OrderDetailEntity;
import entity.OrderReturnEntity;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.OrderReturnsDao;
import repository.custom.ProductDao;
import util.DaoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderReturnsDaoImpl implements OrderReturnsDao {
    ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCTENTITY);

    @Override
    public boolean add(OrderReturnEntity entity) throws SQLException {
        String sql = "Insert into orderreturns values (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,entity.getOrderId());
            preparedStatement.setObject(2,entity.getProductCode());
            preparedStatement.setObject(3,entity.getQuantityReturned());
            preparedStatement.setObject(4,entity.getDate());
            boolean isAddedOrderReturn = preparedStatement.executeUpdate() > 0;
            if(isAddedOrderReturn){
                boolean isUpdatedStock = productDao.updateStock(entity);
                if(isUpdatedStock){
                    connection.commit();
                    return true;
                }
            }
        }finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    @Override
    public OrderReturnEntity search(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<OrderReturnEntity> getAll() throws SQLException {
        List<OrderReturnEntity> orderReturnEntityList = new ArrayList<>();
        String sql = "Select * from orderreturns";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while(resultSet.next()){
            OrderReturnEntity orderReturnEntity = new OrderReturnEntity(
                    Integer.parseInt(resultSet.getString(1)),
                    Integer.parseInt(resultSet.getString(2)),
                    Integer.parseInt(resultSet.getString(3)),
                    (resultSet.getString(4))
            );
            orderReturnEntityList.add(orderReturnEntity);
        }
        return orderReturnEntityList;
    }

    @Override
    public boolean update(OrderReturnEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public boolean search(Integer orderId, Integer productCode) throws SQLException {
        String sql = "select * from orderreturns where order_id='"+orderId+"' and product_code='"+productCode+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        return resultSet.next();
    }
}
