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

public class AdminDashboardWindowController {


    @FXML
    private AnchorPane ancPaneLoadContainer;

    @FXML
    private Button btnLoadChangeEmailForm;

    @FXML
    private Button btnLoadEmployeeForm;

    @FXML
    private Button btnLoadViewReports;

    @FXML
    private Button btnLoadModifyEmployee;


    @FXML
    void btnLoadEmployeeFormOnAction(ActionEvent event) {
        URL resource = this.getClass().getResource("/view/employee_form.fxml");

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
    void btnLoadViewReportsOnAction(ActionEvent event) {

    }

    public void btnLoadModifyEmployeeOnAction(ActionEvent actionEvent) {
        URL resource = this.getClass().getResource("/view/modify_employees_form.fxml");

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
