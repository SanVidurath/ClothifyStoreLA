package service.custom;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import service.CrudService;

import java.sql.SQLException;

public interface OrderDetailService extends CrudService<OrderDetail, Integer> {
    OrderDetail getOrderDetail(Object orderId, Object productCode) throws SQLException;
}
