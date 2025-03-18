package controller.supplier;

import com.jfoenix.controls.JFXTextField;
import dto.ProductDetail;
import dto.Supplier;
import jakarta.inject.Inject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import service.custom.SupplierService;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class ModifySupplierFormController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox cmbSupplierId;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNo;

    Set<ProductDetail> productDetailSet = new HashSet<>();

    @Inject
    SupplierService supplierService;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            if (cmbSupplierId.getSelectionModel().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "supplier must be selected from dropdown").show();
            } else {
                if (supplierService.search(txtEmail.getText())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this supplier?");
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.isPresent() && buttonType.get().getText().equals("OK")) {

                        boolean isSupplierDeleted = supplierService.delete(txtEmail.getText());
                        if (isSupplierDeleted) {
                            new Alert(Alert.AlertType.INFORMATION, "supplier has been deleted successfully").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "some error has occurred, try again later.").show();
                        }

                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "supplier not found.").show();
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String nameText = txtName.getText().toLowerCase();
        String companyText = txtCompany.getText().toLowerCase();
        String emailText = txtEmail.getText().toLowerCase();
        String phoneNoText = txtPhoneNo.getText().toLowerCase();
        if (cmbSupplierId.getSelectionModel().isEmpty()||nameText.isEmpty() || companyText.isEmpty() || emailText.isEmpty() || phoneNoText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "all fields must be filled").show();
        } else {

            Supplier supplier = new Supplier(Integer.parseInt(String.valueOf(cmbSupplierId.getValue())), nameText, companyText, emailText, phoneNoText, productDetailSet);
            try {
                boolean isUpdatedSupplier = supplierService.update(supplier);
                if (isUpdatedSupplier) {
                    new Alert(Alert.AlertType.INFORMATION, "supplier updated successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "supplier not updated. Try again later.").show();
                }


            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }


    }

    @FXML
    void cmbShowSupplierIdsOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSupplierIds();

        cmbSupplierId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                searchSupplierData(newValue.toString());
            }
        });
    }

    private void searchSupplierData(String supplierId) {
        try {
            Supplier supplier = supplierService.search(Integer.parseInt(supplierId));

            txtName.setText(supplier.getName());
            txtPhoneNo.setText(supplier.getPhoneNo());
            txtEmail.setText(supplier.getEmail());
            txtCompany.setText(supplier.getCompany());
            Set<ProductDetail> productDetails = supplier.getProductDetails();
            productDetailSet.addAll(productDetails);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadSupplierIds() {
        try {
            ObservableList<Integer> supplierIds = supplierService.getIds();
            cmbSupplierId.setItems(supplierIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
