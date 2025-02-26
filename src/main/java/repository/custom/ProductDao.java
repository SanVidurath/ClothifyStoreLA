package repository.custom;

import entity.OrderDetailEntity;
import entity.OrderReturnEntity;
import entity.ProductEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity, Integer> {
    boolean search(ProductEntity productEntity) throws SQLException;
    ObservableList<Integer> getIds() throws SQLException;
    boolean updateStock(List<OrderDetailEntity> orderDetailEntityList) throws SQLException;
    boolean updateStock(OrderDetailEntity orderDetailEntity) throws SQLException;
    boolean updateStock(OrderReturnEntity orderReturnEntity) throws SQLException;
}
