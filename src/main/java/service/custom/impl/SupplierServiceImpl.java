package service.custom.impl;

import dto.Employee;
import dto.Supplier;
import entity.EmployeeEntity;
import entity.SupplierEntity;
import org.apache.poi.ss.formula.functions.Mode;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIERENTITY);

    @Override
    public boolean add(Supplier supplier) throws SQLException {
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return supplierDao.add(supplierEntity);
    }

    @Override
    public Supplier search(String email) throws SQLException {
        SupplierEntity supplierEntity = supplierDao.search(email);
        if(supplierEntity!=null){
            return new ModelMapper().map(supplierEntity,Supplier.class);
        }else{
            return  null;
        }

    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<SupplierEntity> supplierEntityList = supplierDao.getAll();
        List<Supplier> supplierList = new ArrayList<>();
        for (SupplierEntity supplierEntity:supplierEntityList){
            Supplier supplier = new ModelMapper().map(supplierEntity, Supplier.class);
            supplierList.add(supplier);
        }

        return supplierList;
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return supplierDao.update(supplierEntity);
    }

    @Override
    public boolean delete(String email) throws SQLException {
        return supplierDao.delete(email);
    }
}
