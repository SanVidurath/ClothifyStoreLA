package service.custom.impl;

import dto.OrderReturn;
import entity.OrderReturnEntity;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import repository.custom.OrderReturnsDao;
import service.custom.OrderReturnsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderReturnsServiceImpl implements OrderReturnsService {
    @Inject
    OrderReturnsDao orderReturnsDao;

    @Override
    public boolean add(OrderReturn orderReturn) throws SQLException {
        return orderReturnsDao.add(new ModelMapper().map(orderReturn, OrderReturnEntity.class));
    }

    @Override
    public OrderReturn search(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<OrderReturn> getAll() throws SQLException {
        List<OrderReturnEntity> all = orderReturnsDao.getAll();
        List<OrderReturn> allOrderReturns = new ArrayList<>();
        all.forEach(orderReturnEntity -> allOrderReturns.add(new ModelMapper().map(orderReturnEntity, OrderReturn.class)));
        return allOrderReturns;
    }

    @Override
    public boolean update(OrderReturn service) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public boolean search(Integer orderId, Integer productCode) throws SQLException {
        return orderReturnsDao.search(orderId,productCode);
    }
}
