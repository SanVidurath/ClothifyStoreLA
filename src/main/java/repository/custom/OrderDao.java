package repository.custom;

import entity.OrderEntity;
import repository.CrudDao;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends CrudDao<OrderEntity, Integer> {
    Integer getLastId() throws SQLException;
    OrderEntity getLast() throws SQLException;
    List<Integer> getIds() throws SQLException;
}
