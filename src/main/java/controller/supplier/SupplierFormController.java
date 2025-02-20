package controller.supplier;

import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.SuperService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

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

    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String nameText = txtName.getText();
        String companyText = txtCompany.getText();
        String emailText = txtEmail.getText();
        String phoneNoText = txtPhoneNo.getText();

        if (nameText.isEmpty() || companyText.isEmpty() || emailText.isEmpty() || phoneNoText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "all fields must be filled").show();
        } else {
            if (!validateEmail(emailText)) {
                new Alert(Alert.AlertType.ERROR, "not a valid email address").show();
            } else {

                Supplier supplier = new Supplier(1, nameText, companyText, emailText, phoneNoText);
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
        Supplier supplier = null;
        try {
            supplier = supplierService.search(txtEmail.getText());
            if (supplier == null) {
                new Alert(Alert.AlertType.ERROR, "check email and try again").show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this supplier?");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.isPresent() && buttonType.get().getText().equals("OK")) {
                    boolean isDeletedSupplier = supplierService.delete(supplier.getEmail());
                    if (isDeletedSupplier) {
                        new Alert(Alert.AlertType.INFORMATION, "supplier has been deleted successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "some error has occurred, try again later.").show();
                    }

                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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
        String emailText = txtEmail.getText();
        if (emailText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "email must be given to search for a supplier").show();
        } else {
            try {
                Supplier supplier = supplierService.search(emailText);
                if (supplier == null) {
                    new Alert(Alert.AlertType.ERROR, "supplier not found. Check email and try again.").show();
                } else {
                    txtName.setText(supplier.getName());
                    txtCompany.setText(supplier.getCompany());
                    txtPhoneNo.setText(supplier.getPhoneNo());
                    btnUpdate.setDisable(false);
                    btnDelete.setDisable(false);
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String nameText = txtName.getText();
        String companyText = txtCompany.getText();
        String emailText = txtEmail.getText();
        String phoneNoText = txtPhoneNo.getText();
        if (nameText.isEmpty() || companyText.isEmpty() || emailText.isEmpty() || phoneNoText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "all fields must be filled").show();
        } else {

            Supplier supplier = new Supplier(1, nameText, companyText, emailText, phoneNoText);
            try {
                Supplier searchedSupplier = supplierService.search(emailText);
                if (searchedSupplier == null) {
                    new Alert(Alert.AlertType.ERROR, "check email and try again later.").show();
                } else {
                    boolean isUpdatedSupplier = supplierService.update(supplier);
                    if (isUpdatedSupplier) {
                        new Alert(Alert.AlertType.INFORMATION, "supplier updated successfully").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "supplier not updated. Try again later.").show();
                    }
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }
}
