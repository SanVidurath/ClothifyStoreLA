package repository.custom.impl;

import db.DBConnection;
import entity.OrderReturnEntity;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.OrderReturnsDao;
import repository.custom.ProductDao;
import util.DaoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return List.of();
    }

    @Override
    public boolean update(OrderReturnEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }
}
