package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class UserDashboardWindowController {

    @FXML
    private AnchorPane ancPaneLoadContainer;

    @FXML
    private Button btnLoadAddProductsForm;

    @FXML
    private Button btnLoadCustomerForm;

    @FXML
    private Button btnLoadModifyProductsForm;

    @FXML
    private Button btnLoadOrderAndReturns;

    @FXML
    private Button btnLoadOrderForm;

    @FXML
    private Button btnLoadSupplierForm;

    @FXML
    private Button btnLoadViewSupplierProducts;

    @FXML
    void btnLoadAddProductsFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoadCustomerFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoadModifyProductsFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoadOrderAndReturnsOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoadOrderFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnLoadSupplierFormOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/supplier_form.fxml");

        assert resource!=null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        ancPaneLoadContainer.getChildren().clear();
        ancPaneLoadContainer.getChildren().add(load);
    }

    @FXML
    void btnLoadViewSupplierProductsOnAction(ActionEvent event) {

    }

}
