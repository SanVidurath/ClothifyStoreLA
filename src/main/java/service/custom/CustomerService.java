package service.custom;

import dto.Customer;
import javafx.collections.ObservableList;
import service.CrudService;

import java.sql.SQLException;

public interface CustomerService extends CrudService<Customer, Integer> {
    ObservableList<Integer> getIds() throws SQLException;
}
