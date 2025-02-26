package repository.custom.impl;

import db.DBConnection;
import dto.Product;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.ProductDao;
import repository.custom.ProductDetailDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {


    @Override
    public boolean add(ProductEntity entity) throws SQLException {
        String sql = "Insert into products(description,category,size,unit_price,quantity,supplier_id) values(?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, entity.getDescription());
            preparedStatement.setObject(2, entity.getCategory());
            preparedStatement.setObject(3, entity.getSize());
            preparedStatement.setObject(4, entity.getUnitPrice());
            preparedStatement.setObject(5, entity.getQuantityInStock());
            preparedStatement.setObject(6, entity.getSupplierId());
            boolean isAddedProduct = preparedStatement.executeUpdate() > 0;
            if (isAddedProduct ) {
                ProductEntity lastProductEntity = getLast();
                if (lastProductEntity != null) {
                    ProductDetailEntity productDetailEntity = new ProductDetailEntity(lastProductEntity.getCode(), lastProductEntity.getSupplierId(), lastProductEntity.getUnitPrice(), lastProductEntity.getQuantityInStock());
                    boolean isAddedProductDetail = new ProductDetailDaoImpl().addProductDetail(productDetailEntity);
                    if (isAddedProductDetail) {
                        connection.commit();
                        return true;
                    }
                }

            }
        } finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    @Override
    public ProductEntity search(Integer code) throws SQLException {
        ProductEntity productEntity = null;
        String sql = "Select * from products where code='" + code + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            productEntity = new ProductEntity(
                    Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    Double.parseDouble(resultSet.getString(5)),
                    Integer.parseInt(resultSet.getString(6)),
                    Integer.parseInt(resultSet.getString(7))
            );
        }
        return productEntity;
    }

    @Override
    public boolean search(ProductEntity productEntity) throws SQLException {
        List<ProductEntity> productEntities = getAll();
        for (ProductEntity entity:productEntities){
            if (entity.equals(productEntity)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ObservableList<Integer> getIds() throws SQLException {
        ObservableList<Integer> productIds = FXCollections.observableArrayList();
        List<ProductEntity> productEntities = getAll();
        productEntities.forEach(productEntity -> productIds.add(productEntity.getCode()));
        return productIds;
    }

    @Override
    public List<ProductEntity> getAll() throws SQLException {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        String sql = "Select * from products";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            ProductEntity productEntity = new ProductEntity(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), Double.parseDouble(resultSet.getString(5)), Integer.parseInt(resultSet.getString(6)), Integer.parseInt(resultSet.getString(7)));
            productEntityList.add(productEntity);
        }
        return productEntityList;
    }

    @Override
    public boolean update(ProductEntity entity) throws SQLException {
        String sql = "Update products set description=?,category=?,size=?,unit_price=?,quantity=? where code='" + entity.getCode() + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, entity.getDescription());
        preparedStatement.setObject(2, entity.getCategory());
        preparedStatement.setObject(3, entity.getSize());
        preparedStatement.setObject(4, entity.getUnitPrice());
        preparedStatement.setObject(5, entity.getQuantityInStock());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Integer code) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Delete from products where code='" + code + "'";

        try {
            connection.setAutoCommit(false);
            boolean isDeletedProductDetail = new ProductDetailDaoImpl().delete(code);
            if (isDeletedProductDetail) {
                boolean isDeletedProduct = connection.createStatement().executeUpdate(sql) > 0;
                if (isDeletedProduct) {
                    connection.commit();
                    return true;
                }
            }
        } finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    private ProductEntity getLast() throws SQLException {
        ProductEntity productEntity = null;
        String sql = "select * from products group by code order by code desc limit 1";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            productEntity = new ProductEntity(Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    Double.parseDouble(resultSet.getString(5)),
                    Integer.parseInt(resultSet.getString(6)),
                    Integer.parseInt(resultSet.getString(7)));
        }
        return productEntity;
    }

    public boolean updateStock(List<OrderDetailEntity> orderDetailEntityList) throws SQLException {
        for (OrderDetailEntity orderDetailEntity : orderDetailEntityList) {
            boolean isUpdateStock = updateStock(orderDetailEntity);
            if(!isUpdateStock){
                return false;
            }
        }
        return true;
    }

    public boolean updateStock(OrderDetailEntity orderDetailEntity) throws SQLException {
        String sql = "Update products set quantity=quantity-? where code=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,orderDetailEntity.getQuantityPurchased());
        preparedStatement.setObject(2,orderDetailEntity.getProductCode());
        return preparedStatement.executeUpdate()>0;
    }

    public boolean updateStock(OrderReturnEntity orderReturnEntity) throws SQLException {
        String sql = "Update products set quantity=quantity+? where code=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,orderReturnEntity.getQuantityReturned());
        preparedStatement.setObject(2,orderReturnEntity.getProductCode());
        return preparedStatement.executeUpdate()>0;
    }
}
