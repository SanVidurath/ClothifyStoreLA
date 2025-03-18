package controller.supplier;

import com.jfoenix.controls.JFXTextField;
import dto.ProductDetail;
import dto.Supplier;
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
import service.custom.SupplierService;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierFormController{

    @FXML
    private Button btnAdd;



    @FXML
    private Button btnReload;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private TableView tblSuppliers;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNo;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Inject
    SupplierService supplierService;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String nameText = txtName.getText().toLowerCase();
        String companyText = txtCompany.getText().toLowerCase();
        String emailText = txtEmail.getText().toLowerCase();
        String phoneNoText = txtPhoneNo.getText().toLowerCase();

        if (nameText.isEmpty() || companyText.isEmpty() || emailText.isEmpty() || phoneNoText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "all fields must be filled").show();
        } else {
            if (!validateEmail(emailText)) {
                new Alert(Alert.AlertType.ERROR, "not a valid email address").show();
            } else {
                Set<ProductDetail> productDetailSet = new HashSet<>();

                Supplier supplier = new Supplier(1, nameText, companyText, emailText, phoneNoText, productDetailSet);
                try {
                    boolean isAddedSupplier = supplierService.add(supplier);
                    if (isAddedSupplier) {
                        new Alert(Alert.AlertType.INFORMATION, "supplier added successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "failed to add supplier, try again later").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }
    }

    private boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadData();
    }

    private void loadData() {
        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
        try {
            List<Supplier> supplierList = supplierService.getAll();
            supplierObservableList.addAll(supplierList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        tblSuppliers.setItems(supplierObservableList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }


}
