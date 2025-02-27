package config;

import com.google.inject.AbstractModule;
import repository.custom.CustomerDao;
import repository.custom.EmployeeDao;
import repository.custom.impl.CustomerDaoImpl;
import repository.custom.impl.EmployeeDaoImpl;
import service.custom.CustomerService;
import service.custom.EmployeeService;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.EmployeeServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(EmployeeService.class).to(EmployeeServiceImpl.class);
        bind(EmployeeDao.class).to(EmployeeDaoImpl.class);
        bind(CustomerService.class).to(CustomerServiceImpl.class);
        bind(CustomerDao.class).to(CustomerDaoImpl.class);
    }
}
