package service.custom.impl;

import dto.OrderDetail;
import service.custom.OrderDetailService;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public boolean add(OrderDetail service) throws SQLException {
        return false;
    }

    @Override
    public OrderDetail search(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(OrderDetail service) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }
}
