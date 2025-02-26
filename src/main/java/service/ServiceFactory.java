package service;

import service.custom.impl.*;
import util.ServiceType;

public class ServiceFactory{
    private static ServiceFactory instance;
    private ServiceFactory(){

    }

    public static ServiceFactory getInstance() {
        if(instance==null){
            instance=new ServiceFactory();
        }
        return instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case EMPLOYEE:return (T) new EmployeeServiceImpl();
            case SUPPLIER:return (T) new SupplierServiceImpl();
            case CUSTOMER:return (T) new CustomerServiceImpl();
            case PRODUCT:return (T) new ProductServiceImpl();
            case ORDER:return (T) new OrderServiceImpl();
            case ORDERDETAIL:return (T) new OrderDetailServiceImpl();
            case ORDERRETURNS:return (T) new OrderReturnsServiceImpl();
        }
        return null;
    }
}
