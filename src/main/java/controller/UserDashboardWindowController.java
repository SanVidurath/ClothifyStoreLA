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

    public Button btnLoadModifySupplierForm;
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
        URL resource = this.getClass().getResource("/view/add_products_form.fxml");

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
    void btnLoadCustomerFormOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/add_customer_form.fxml");

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
    void btnLoadModifyProductsFormOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/modify_products_window.fxml");

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
    void btnLoadOrderAndReturnsOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/orders_and_returns_form.fxml");

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
    void btnLoadOrderFormOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/order_form.fxml");

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
        URL resource = this.getClass().getResource("/view/supplier_products_window.fxml");

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

    public void btnLoadModifySupplierFormOnAction(ActionEvent actionEvent) {
        URL resource = this.getClass().getResource("/view/modify_supplier_form.fxml");

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
}
