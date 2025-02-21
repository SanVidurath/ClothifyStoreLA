
package repository.custom.impl;

import db.DBConnection;
import dto.OrderDetail;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private final List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();

    @Override
    public boolean add(OrderEntity entity) throws SQLException {
        String sql = "Insert into orders(date,employee_id,employee_name,customer_id,total,payment_type) values (?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, entity.getDate());
            preparedStatement.setObject(2, entity.getEmployeeId());
            preparedStatement.setObject(3, entity.getEmployeeName());
            preparedStatement.setObject(4, entity.getCustomerId());
            preparedStatement.setObject(5, entity.getTotal());
            preparedStatement.setObject(6, entity.getPaymentType());
            boolean isAddedOrder = preparedStatement.executeUpdate() > 0;
            if (isAddedOrder) {
                boolean isAddedOrderDetail = new OrderDetailDaoImpl().add(entity.getOrderDetailEntityList());
                if (isAddedOrderDetail) {
                    boolean isUpdatedStock = new ProductDaoImpl().updateStock(entity.getOrderDetailEntityList());
                    if (isUpdatedStock) {
                        connection.commit();
                        return true;
                    }
                }
            }
        } finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    @Override
    public OrderEntity search(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderEntity entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    public Integer getLastId() throws SQLException {
        OrderEntity last = getLast();
        return last.getId();
    }

    public OrderEntity getLast() throws SQLException {
        OrderEntity orderEntity = null;
        ArrayList<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();
        String sql = "Select * from orders order by id desc limit 1";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            orderEntity = new OrderEntity(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    Integer.parseInt(resultSet.getString(3)),
                    resultSet.getString(4),
                    Integer.parseInt(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    resultSet.getString(7),
                    orderDetailEntityList
            );
        }
        return orderEntity;
    }

    @Override
    public List<OrderEntity> getAll() throws SQLException {
        List<OrderEntity> orderEntityList = new ArrayList<>();
        String sql = "Select * from orders";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while(resultSet.next()){
            OrderEntity orderEntity = new OrderEntity(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    Integer.parseInt(resultSet.getString(3)),
                    resultSet.getString(4),
                    Integer.parseInt(resultSet.getString(5)),
                    Double.parseDouble(resultSet.getString(6)),
                    resultSet.getString(7),
                    orderDetailEntityList);
            orderEntityList.add(orderEntity);
        }
        return orderEntityList;
    }

    @Override
    public List<Integer> getIds() throws SQLException {
        List<OrderEntity> orderEntityList = getAll();
        List<Integer> orderIds = new ArrayList<>();
        orderEntityList.forEach(order -> orderIds.add(order.getId()));
        return orderIds;
    }
}





