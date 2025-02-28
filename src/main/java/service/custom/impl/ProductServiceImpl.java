package service.custom.impl;

import dto.Product;
import entity.ProductEntity;
import jakarta.inject.Inject;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.ProductDao;
import service.custom.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Inject
    ProductDao productDao;

    @Override
    public boolean add(Product product) throws SQLException {
        return productDao.add(new ModelMapper().map(product, ProductEntity.class));
    }

    @Override
    public Product search(Integer productCode) throws SQLException {
        ProductEntity productEntity = productDao.search(productCode);
        if(productEntity!=null){
            return new ModelMapper().map(productEntity, Product.class);
        }else{
            return null;
        }
    }

    @Override
    public boolean search(Product product) throws SQLException {
        return productDao.search(new ModelMapper().map(product, ProductEntity.class));
    }

    @Override
    public ObservableList<Integer> getIds() throws SQLException {
        return productDao.getIds();
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<ProductEntity> productEntities = productDao.getAll();
        List<Product> productList = new ArrayList<>();
        for (ProductEntity productEntity:productEntities){
            Product map = new ModelMapper().map(productEntity, Product.class);
            productList.add(map);
        }

        return productList;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return productDao.update(new ModelMapper().map(product,ProductEntity.class));
    }

    @Override
    public boolean delete(Integer productCode) throws SQLException {
        return productDao.delete(productCode);
    }
}
