package service.custom.impl;

import dto.OrderReturn;
import entity.OrderReturnEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.OrderReturnsDao;
import service.ServiceFactory;
import service.SuperService;
import service.custom.OrderReturnsService;
import util.DaoType;
import util.ServiceType;

import javax.management.modelmbean.ModelMBean;
import java.sql.SQLException;
import java.util.List;

public class OrderReturnsServiceImpl implements OrderReturnsService {
    OrderReturnsDao orderReturnsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERRETURNSENTITY);

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
        return List.of();
    }

    @Override
    public boolean update(OrderReturn service) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }
}
