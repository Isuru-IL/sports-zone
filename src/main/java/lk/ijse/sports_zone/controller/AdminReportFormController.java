package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.db.DBConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class AdminReportFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpReport;

    @FXML
    private JFXButton btnSupplierLoadDetail;

    @FXML
    private JFXButton btnEmployeeDetail;

    @FXML
    void btnSupplierLoadDetailOnAction(ActionEvent event){
            InputStream resource = this.getClass().getResourceAsStream("/report/SupplyLoadDetails.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnEmployeeDetailOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/report/EmployeeDetails.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert anchrpReport != null : "fx:id=\"anchrpReport\" was not injected: check your FXML file 'adminReport_form.fxml'.";
        assert btnSupplierLoadDetail != null : "fx:id=\"btnSupplierLoadDetail\" was not injected: check your FXML file 'adminReport_form.fxml'.";

    }

}
