package service.custom.impl;

import dto.Order;
import entity.OrderEntity;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import repository.custom.OrderDao;
import service.custom.OrderService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Inject
    OrderDao orderDao;

    @Override
    public boolean add(Order order) throws SQLException {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Order.class, OrderEntity.class)
                .addMappings(mapper -> mapper.map(Order::getOrderDetailList, OrderEntity::setOrderDetailEntityList));
        OrderEntity entity = modelMapper.map(order, OrderEntity.class);

        return orderDao.add(entity);
    }

    @Override
    public Order search(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<OrderEntity> all = orderDao.getAll();
        List<Order> allOrders = new ArrayList<>();
        all.forEach(orderEntity -> allOrders.add(new ModelMapper().map(orderEntity, Order.class)));
        return allOrders;
    }

    @Override
    public boolean update(Order service) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public List<Integer> getIds() throws SQLException {
        return orderDao.getIds();
    }
}
