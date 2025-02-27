package repository.custom;

import entity.OrderReturnEntity;
import repository.CrudDao;

import java.sql.SQLException;

public interface OrderReturnsDao extends CrudDao<OrderReturnEntity, Integer> {
    boolean search(Integer orderId, Integer productCode) throws SQLException;
}
