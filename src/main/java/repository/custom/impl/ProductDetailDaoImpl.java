package repository.custom.impl;

import db.DBConnection;
import entity.ProductDetailEntity;
import repository.custom.ProductDetailDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDetailDaoImpl implements ProductDetailDao {
    public boolean addProductDetail(ProductDetailEntity productDetailEntity) throws SQLException {
        String sql = "Insert into productdetail values (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,productDetailEntity.getProdCode());
        preparedStatement.setObject(2,productDetailEntity.getSupId());
        preparedStatement.setObject(3,productDetailEntity.getUnitPrice());
        preparedStatement.setObject(4,productDetailEntity.getQtySupplied());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean delete(Integer code) throws SQLException {
        String sql = "Delete from productdetail where product_code='"+code+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.createStatement().executeUpdate(sql)>0;
    }
}
