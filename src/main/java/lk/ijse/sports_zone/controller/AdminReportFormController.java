package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.db.DBConnection;
import lk.ijse.sports_zone.model.CashierOrderModel;
import lk.ijse.sports_zone.util.ValidateController;
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
    private AreaChart<String, Double> areaChart;

    @FXML
    private TextField txtyear;

    @FXML
    private Label lblwrongyearformat;

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
    void arechartOnMouseClicked(MouseEvent event) {
        txtyear.setText("");
        txtyear.setStyle("-fx-text-fill: black");
        lblwrongyearformat.setVisible(false);
    }

    @FXML
    void txtyearOnAction(ActionEvent event) {
        lblwrongyearformat.setVisible(false);
        if (!txtyear.getText().isEmpty()) {
            if (ValidateController.yearCheck(txtyear.getText())) {
                setDataToAreaChart();
                areaChart.setData(FXCollections.observableArrayList());
                setDataToAreaChart();
            } else {
                txtyear.setStyle("-fx-text-fill: red");
                lblwrongyearformat.setText("Wrong Year Format");
                lblwrongyearformat.setVisible(true);
            }
        }else{
            lblwrongyearformat.setText("Please enter an year first");
            lblwrongyearformat.setVisible(true);
        }
    }

    List<XYChart.Data<String, Double>> data;
    public void setDataToAreaChart() {
        String year=txtyear.getText();
        try {
            data = CashierOrderModel.getDataToAreaChart(year);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        XYChart.Series<String, Double> series = new XYChart.Series<>(year, FXCollections.observableArrayList(data));

        areaChart.getData().add(series);
    }

    @FXML
    void txtyearOnKeyTyped(KeyEvent event) {
        txtyear.setStyle("-fx-text-fill: black");
    }

    @FXML
    void txtyearOnMouseClicked(MouseEvent event) {
        txtyear.setStyle("-fx-text-fill: black");
        lblwrongyearformat.setVisible(false);
    }

    @FXML
    void initialize() {
        assert anchrpReport != null : "fx:id=\"anchrpReport\" was not injected: check your FXML file 'adminReport_form.fxml'.";
        assert btnSupplierLoadDetail != null : "fx:id=\"btnSupplierLoadDetail\" was not injected: check your FXML file 'adminReport_form.fxml'.";


        try {
            data = CashierOrderModel.getDataToAreaChart(String.valueOf(2023));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        XYChart.Series<String, Double> series = new XYChart.Series<>(String.valueOf(2023), FXCollections.observableArrayList(data));
        areaChart.getData().add(series);
    }

}
