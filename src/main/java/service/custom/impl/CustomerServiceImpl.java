package service.custom.impl;

import dto.Customer;
import entity.CustomerEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.CustomerDao;
import service.ServiceFactory;
import service.SuperService;
import service.custom.CustomerService;
import util.DaoType;
import util.ServiceType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMERENTITY);

    @Override
    public boolean add(Customer customer) throws SQLException {
        return customerDao.add(new ModelMapper().map(customer, CustomerEntity.class));
    }

    @Override
    public Customer search(Integer id) throws SQLException {
        CustomerEntity customerEntity = customerDao.search(id);
        return new ModelMapper().map(customerEntity, Customer.class);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<CustomerEntity> customerEntityList = customerDao.getAll();
        List<Customer> customerList = new ArrayList<>();
        customerEntityList.forEach(customerEntity -> customerList.add(new ModelMapper().map(customerEntity, Customer.class)));
        return  customerList;
    }

    @Override
    public boolean update(Customer service) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        return false;
    }

    @Override
    public ObservableList<Integer> getIds() throws SQLException {
        return customerDao.getIds();
    }
}
