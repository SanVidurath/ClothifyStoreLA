package service.custom.impl;

import dto.Order;
import dto.OrderDetail;
import entity.OrderEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.OrderDao;
import service.custom.OrderService;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERENTITY);

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
        return List.of();
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
