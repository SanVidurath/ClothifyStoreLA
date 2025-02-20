package service;

import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.SupplierServiceImpl;
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
        }
        return null;
    }
}
