package repository.custom;

import entity.ProductDetailEntity;
import repository.SuperDao;

import java.sql.SQLException;

public interface ProductDetailDao extends SuperDao {
    boolean addProductDetail(ProductDetailEntity productDetailEntity) throws SQLException;
    boolean delete(Integer code) throws SQLException;
}
