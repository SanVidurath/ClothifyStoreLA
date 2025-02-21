package service.custom;

import dto.Order;
import service.CrudService;

import java.sql.SQLException;
import java.util.List;

public interface OrderService extends CrudService<Order, Integer> {
    List<Integer> getIds() throws SQLException;
}
