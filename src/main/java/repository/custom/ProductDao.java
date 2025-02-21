package repository.custom;

import entity.ProductEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

import java.sql.SQLException;

public interface ProductDao extends CrudDao<ProductEntity, Integer> {
    boolean search(ProductEntity productEntity) throws SQLException;
    ObservableList<Integer> getIds() throws SQLException;
}
