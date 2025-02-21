package service.custom;

import dto.Product;
import javafx.collections.ObservableList;
import service.CrudService;

import java.sql.SQLException;

public interface ProductService extends CrudService<Product, Integer> {
    boolean search(Product product) throws SQLException;
    ObservableList<Integer> getIds() throws SQLException;
}
