package repository.custom;

import entity.OrderDetailEntity;
import repository.CrudDao;
import repository.SuperDao;

import java.sql.SQLException;

public interface OrderDetailDao extends CrudDao<OrderDetailEntity, Integer> {
    OrderDetailEntity getOrderDetail(Object orderId, Object productCode) throws SQLException;
}
