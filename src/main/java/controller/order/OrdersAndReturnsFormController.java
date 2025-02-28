package controller.order;

import com.jfoenix.controls.JFXTextField;
import dto.BaseOrderOrderDetail;
import dto.Order;
import dto.OrderDetail;
import dto.OrderReturn;
import jakarta.inject.Inject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import service.custom.OrderDetailService;
import service.custom.OrderReturnsService;
import service.custom.OrderService;
import service.custom.ProductService;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrdersAndReturnsFormController implements Initializable {

    @FXML
    private TableView tblOrderReturns;

    @FXML
    private TableColumn colProductCode;

    @FXML
    private Button btnReloadOrderReturns;

    @FXML
    private TableColumn colOrderIdOR;

    @FXML
    private TableColumn colQuantityPurchasedOR;

    @FXML
    private TableColumn colDateOR;

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
    private TableView tblViewAllOrders;

    @FXML
    private JFXTextField txtQuantityPurchased;

    @Inject
    ProductService productService;

    @Inject
    OrderService orderService;

    @Inject
    OrderDetailService orderDetailService;

    @Inject
    OrderReturnsService orderReturnsService;

    @FXML
    void btnReloadDataOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        ObservableList<BaseOrderOrderDetail> baseOrderOrderDetailObservableList = FXCollections.observableArrayList();

        try {
            List<Order> allOrders = orderService.getAll();
            List<OrderDetail> allOrderDetails = orderDetailService.getAll();

            for (Order order : allOrders) {
                for (OrderDetail orderDetail : allOrderDetails) {
                    if (orderDetail.getOrderId().equals(order.getId())) {
                        BaseOrderOrderDetail baseOrderOrderDetail = new BaseOrderOrderDetail(
                                order.getId(),
                                orderDetail.getProductCode(),
                                order.getEmployeeId(),
                                order.getEmployeeName(),
                                order.getCustomerId(),
                                orderDetail.getUnitPrice(),
                                order.getDate(),
                                orderDetail.getQuantityPurchased(),
                                order.getPaymentType()
                        );
                        baseOrderOrderDetailObservableList.add(baseOrderOrderDetail);
                    }
                }
            }

            tblViewAllOrders.setItems(baseOrderOrderDetailObservableList);
            colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
            colProductId.setCellValueFactory(new PropertyValueFactory<>("productCode"));
            colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colQuantityPurchased.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        if (cmbOrderId.getSelectionModel().isEmpty() || cmbProductCode.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "all combo boxes must be selected").show();
        } else {
            OrderDetail orderDetail = checkOrderDetail();
            try {
                boolean searched = orderReturnsService.search(Integer.parseInt(String.valueOf(cmbOrderId.getValue())), Integer.parseInt(String.valueOf(cmbProductCode.getValue())));
                if (orderDetail == null) {
                    new Alert(Alert.AlertType.ERROR, "incorrect product_code and order_id combo").show();
                } else if (searched) {
                    new Alert(Alert.AlertType.ERROR, "order has already been returned").show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "do you want to return this order?");
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.isPresent() && buttonType.get().getText().equals("OK")) {
                        OrderDetail orderDetailDetails = orderDetailService.getOrderDetail(Integer.parseInt(String.valueOf(cmbOrderId.getValue())), Integer.parseInt(String.valueOf(cmbProductCode.getValue())));
                        OrderReturn orderReturn = new OrderReturn(
                                Integer.parseInt(String.valueOf(cmbOrderId.getValue())),
                                Integer.parseInt(String.valueOf(cmbProductCode.getValue())),
                                orderDetailDetails.getQuantityPurchased(),
                                lblDate.getText()
                        );

                        boolean isAddedOrderReturn = orderReturnsService.add(orderReturn);
                        if (isAddedOrderReturn) {
                            new Alert(Alert.AlertType.INFORMATION, "order return has been successful").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "order return has been unsuccessful").show();
                        }

                    }
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }


        }

    }

    private OrderDetail checkOrderDetail() {
        OrderDetail orderDetail = null;
        try {
            orderDetail = orderDetailService.getOrderDetail(Integer.parseInt(String.valueOf(cmbOrderId.getValue())), Integer.parseInt(String.valueOf(cmbProductCode.getValue())));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return orderDetail;
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


    }

    private void setTimeAndDate() {
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

    private void loadProductCodes() {
        try {
            ObservableList<Integer> ids = productService.getIds();
            cmbProductCode.setItems(ids);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadOrderIds() {
        try {
            List<Integer> ids = orderService.getIds();
            ObservableList<Integer> orderIds = FXCollections.observableArrayList();
            orderIds.addAll(ids);
            cmbOrderId.setItems(orderIds);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnReloadOrderReturnsOnAction(ActionEvent actionEvent) {
        try {
            List<OrderReturn> all = orderReturnsService.getAll();
            ObservableList<OrderReturn> allOrderReturns = FXCollections.observableArrayList();
            allOrderReturns.addAll(all);

            tblOrderReturns.setItems(allOrderReturns);
            colOrderIdOR.setCellValueFactory(new PropertyValueFactory<>("orderId"));
            colProductCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
            colQuantityPurchasedOR.setCellValueFactory(new PropertyValueFactory<>("quantityReturned"));
            colDateOR.setCellValueFactory(new PropertyValueFactory<>("date"));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
