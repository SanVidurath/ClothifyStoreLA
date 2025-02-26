package repository;

import repository.custom.impl.*;
import util.DaoType;

public class DaoFactory{
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance() {
        if(instance==null){
            instance = new DaoFactory();
        }
        return instance;
    }

    public <T extends SuperDao> T getDaoType(DaoType daoType){
        switch (daoType){
            case EMPLOYEEENTITY:return (T) new EmployeeDaoImpl();
            case SUPPLIERENTITY:return (T) new SupplierDaoImpl();
            case CUSTOMERENTITY:return (T) new CustomerDaoImpl();
            case PRODUCTENTITY:return (T) new ProductDaoImpl();
            case ORDERENTITY:return (T) new OrderDaoImpl();
            case ORDERDETAILENTITY:return (T) new OrderDetailDaoImpl();
            case ORDERRETURNSENTITY:return (T) new OrderReturnsDaoImpl();
        }
        return null;
    }
}
