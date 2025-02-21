package repository.custom;

import entity.CustomerEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<CustomerEntity, Integer> {
    ObservableList<Integer> getIds() throws SQLException;
}
