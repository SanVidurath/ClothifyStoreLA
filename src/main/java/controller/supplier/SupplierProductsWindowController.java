package controller.supplier;

import dto.Product;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.ProductService;

import java.sql.SQLException;
import java.util.List;

public class SupplierProductsWindowController {

    @FXML
    private Button btnReload;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colProductCode;

    @FXML
    private TableColumn<?, ?> colProductDecription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView tblSupplierProducts;

    @Inject
    ProductService productService;

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadData();
    }

    private void loadData(){
        ObservableList<Product> productsObservableList = FXCollections.observableArrayList();

        try {
            List<Product> productsList = productService.getAll();

            productsObservableList.addAll(productsList);
            tblSupplierProducts.setItems(productsObservableList);

            colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
            colProductCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colProductDecription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("quantityInStock"));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
