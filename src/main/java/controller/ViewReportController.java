package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class ViewReportController {

    @FXML
    private Button btnViewCustomerReports;

    @FXML
    private Button btnViewOrderReport;

    @FXML
    private Button btnViewProductReport;

    @FXML
    private Button btnViewSupplierReports;

    @FXML
    void btnViewCustomerReportsOnAction(ActionEvent event) {
        try {
//            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/customer_report_clothify.jrxml");

            InputStream reportStream = getClass().getResourceAsStream("/reports/customer_report_clothify.jrxml");
            if (reportStream == null) {
                new Alert(Alert.AlertType.ERROR, "Report file not found in classpath!").show();
                return;
            }
            JasperDesign jasperDesign = JRXmlLoader.load(reportStream);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnViewOrderReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewProductReportOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewSupplierReportsOnAction(ActionEvent event) {
        try {
            InputStream reportStream = getClass().getResourceAsStream("/reports/clothifystore_suppliers.jrxml");
            if (reportStream == null) {
                new Alert(Alert.AlertType.ERROR, "Report file not found in classpath!").show();
                return;
            }
            JasperDesign jasperDesign = JRXmlLoader.load(reportStream);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}

