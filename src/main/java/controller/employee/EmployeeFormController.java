package controller.employee;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jasypt.util.text.BasicTextEncryptor;
import service.ServiceFactory;
import service.SuperService;
import service.custom.EmployeeService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeFormController implements Initializable {

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
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private TableView tblEmployees;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtPhoneNo;

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String nameText = txtName.getText();
        String addressText = txtAddress.getText();
        String emailText = txtEmail.getText();
        String phoneNoText = txtPhoneNo.getText();
        String passwordText = txtPassword.getText();
        String confirmPasswordText = txtConfirmPassword.getText();
        if(nameText.isEmpty()||addressText.isEmpty()||emailText.isEmpty()||phoneNoText.isEmpty()||passwordText.isEmpty()||confirmPasswordText.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"all fields must be filled").show();
        }else{
            if(!validateEmail(emailText)){
                new Alert(Alert.AlertType.ERROR,"not a valid email address").show();
            }else if(!passwordText.equals(confirmPasswordText)){
                new Alert(Alert.AlertType.ERROR,"password fields do not match").show();
            }else{
                BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                basicTextEncryptor.setPassword(emailText);
                String encrypted = basicTextEncryptor.encrypt(passwordText);
                Employee employee = new Employee(1,nameText, addressText, emailText, phoneNoText, encrypted);
                try {

                    boolean isAddedEmployee = employeeService.add(employee);
                    if(isAddedEmployee){
                        new Alert(Alert.AlertType.INFORMATION,"employee added successfully").show();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"failed to add employee, try again later.").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
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
        Employee employee= null;
        try {
            employee = employeeService.search(txtEmail.getText());
            if(employee==null){
                new Alert(Alert.AlertType.ERROR,"check email and try again").show();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "do you want to delete this employee?");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.isPresent() && buttonType.get().getText().equals("OK")){
                    boolean isEmployeeDeleted = employeeService.delete(employee.getEmail());
                    if(isEmployeeDeleted){
                        new Alert(Alert.AlertType.INFORMATION,"employee has been deleted successfully").show();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"some error has occured, try again later.").show();
                    }

                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadData();
    }

    private void loadData(){
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        try {
            List<Employee> employeeList = employeeService.getAll();
            employeeObservableList.addAll(employeeList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        tblEmployees.setItems(employeeObservableList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String emailText = txtEmail.getText();
        if(emailText.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"email must be given to search for an employee").show();
        }else{
            try {
                Employee employee = employeeService.search(emailText);
                if(employee==null){
                    new Alert(Alert.AlertType.ERROR,"employee not found. Check email and try again.").show();
                }else{
                    txtName.setText(employee.getName());
                    txtAddress.setText(employee.getAddress());
                    txtPhoneNo.setText(employee.getPhoneNo());
                    btnUpdate.setDisable(false);
                    btnDelete.setDisable(false);
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String nameText = txtName.getText();
        String addressText = txtAddress.getText();
        String emailText = txtEmail.getText();
        String phoneNoText = txtPhoneNo.getText();
        String passwordText = txtPassword.getText();
        String confirmPasswordText = txtConfirmPassword.getText();
        if(nameText.isEmpty()||addressText.isEmpty()||emailText.isEmpty()||phoneNoText.isEmpty()||passwordText.isEmpty()||confirmPasswordText.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"all fields must be filled").show();
        }else{
            if(!passwordText.equals(confirmPasswordText)){
                new Alert(Alert.AlertType.ERROR,"password fields do not match").show();
            }else{
                BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                basicTextEncryptor.setPassword(emailText);
                String encrypted = basicTextEncryptor.encrypt(passwordText);
                Employee employee = new Employee(1,nameText, addressText, emailText, phoneNoText, encrypted);
                try {
                    Employee searchedEmployee = employeeService.search(emailText);
                    if(searchedEmployee==null){
                        new Alert(Alert.AlertType.ERROR, "check email and try again later.").show();
                    }else{
                        boolean isUpdatedEmployee = employeeService.update(employee);
                        if(isUpdatedEmployee){
                            new Alert(Alert.AlertType.INFORMATION, "employee updated successfully").show();
                        }else{
                            new Alert(Alert.AlertType.ERROR, "employee not updated. Try again later.").show();
                        }
                    }

                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }
}
