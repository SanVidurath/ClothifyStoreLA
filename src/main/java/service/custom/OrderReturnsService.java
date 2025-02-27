package service.custom;

import dto.OrderReturn;
import service.CrudService;

import java.sql.SQLException;

public interface OrderReturnsService extends CrudService<OrderReturn, Integer> {
    boolean search(Integer orderId, Integer productCode) throws SQLException;
}
