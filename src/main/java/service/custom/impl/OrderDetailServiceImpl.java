package service.custom.impl;

import dto.OrderDetail;
import entity.OrderDetailEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.OrderDetailDao;
import service.custom.OrderDetailService;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailDao orderDetailDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILENTITY);

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
        List<OrderDetailEntity> all = orderDetailDao.getAll();
        List<OrderDetail> allOrderDetails = new ArrayList<>();
        all.forEach(orderDetailEntity -> allOrderDetails.add(new ModelMapper().map(orderDetailEntity, OrderDetail.class)));
        return allOrderDetails;
    }

    @Override
    public boolean update(OrderDetail service) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public OrderDetail getOrderDetail(Object orderId, Object productCode) throws SQLException {
        OrderDetailEntity orderDetailEntity = orderDetailDao.getOrderDetail(orderId, productCode);
        return orderDetailEntity!=null?new ModelMapper().map(orderDetailEntity, OrderDetail.class):null;
    }
}
