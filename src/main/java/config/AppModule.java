package config;

import com.google.inject.AbstractModule;
import repository.custom.*;
import repository.custom.impl.*;
import service.custom.*;
import service.custom.impl.*;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(CustomerService.class).to(CustomerServiceImpl.class);
        bind(CustomerDao.class).to(CustomerDaoImpl.class);

        bind(EmployeeService.class).to(EmployeeServiceImpl.class);
        bind(EmployeeDao.class).to(EmployeeDaoImpl.class);

        bind(OrderDetailService.class).to(OrderDetailServiceImpl.class);
        bind(OrderDetailDao.class).to(OrderDetailDaoImpl.class);

        bind(OrderReturnsService.class).to(OrderReturnsServiceImpl.class);
        bind(OrderReturnsDao.class).to(OrderReturnsDaoImpl.class);

        bind(OrderService.class).to(OrderServiceImpl.class);
        bind(OrderDao.class).to(OrderDaoImpl.class);

        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(ProductDao.class).to(ProductDaoImpl.class);

        bind(ProductDetailDao.class).to(ProductDetailDaoImpl.class);

        bind(SupplierService.class).to(SupplierServiceImpl.class);
        bind(SupplierDao.class).to(SupplierDaoImpl.class);
    }
}
