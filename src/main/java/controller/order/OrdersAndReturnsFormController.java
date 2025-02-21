package controller.order;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.OrderService;
import service.custom.ProductService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrdersAndReturnsFormController implements Initializable {

    @FXML
    private Button btnReload;

    @FXML
    private Button btnReloadData;

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox cmbOrderId;

    @FXML
    private ComboBox cmbProductCode;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQuantityPurchased;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<?> tblViewAllOrders;

    @FXML
    private JFXTextField txtQuantityPurchased;

    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);

    @FXML
    void btnReloadDataOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowOrderIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbShowProductCodeOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOrderIds();
        loadProductCodes();
        setTimeAndDate();
        btnReturn.setDisable(true);


    }

    private void setTimeAndDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        lblDate.setText(format);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(padWithZeros(now.getHour(), now.getMinute(), now.getSecond()));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private String padWithZeros(int hour, int min, int sec) {
        String newHour = String.valueOf(hour).length() == 1 ? "0" + hour : String.valueOf(hour);
        String newMin = String.valueOf(min).length() == 1 ? "0" + min : String.valueOf(min);
        String newSec = String.valueOf(sec).length() == 1 ? "0" + sec : String.valueOf(sec);
        return newHour + ":" + newMin + ":" + newSec;
    }

    private void loadProductCodes(){
        try {
            ObservableList<Integer> ids = productService.getIds();
            cmbProductCode.setItems(ids);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadOrderIds(){
        try {
            List<Integer> ids = orderService.getIds();
            ObservableList<Integer> orderIds = FXCollections.observableArrayList();
            orderIds.addAll(ids);
            cmbOrderId.setItems(orderIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
