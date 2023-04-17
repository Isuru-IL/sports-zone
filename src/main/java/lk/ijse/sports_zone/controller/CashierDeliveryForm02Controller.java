package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Delivery;
import lk.ijse.sports_zone.model.*;
import lk.ijse.sports_zone.util.AlertController;

public class CashierDeliveryForm02Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpDelivery02;

    @FXML
    private JFXButton btnSave;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private ComboBox<String> cmbVehiId;

    @FXML
    private DatePicker dtpickerDueDate;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private TextField txtDeliveryId;

    @FXML
    private TextField txtLocation;

    private Delivery delivery;


    @FXML
    void cmbCustIdOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        delivery.setDeliveryId(txtDeliveryId.getText());
        delivery.setEmpId(cmbEmployeeId.getValue());
        delivery.setOrderId(lblOrderId.getText());
        delivery.setVehiId(cmbVehiId.getValue());
        delivery.setLocation(txtLocation.getText());
        delivery.setDeliveryDate(lblDate.getText());
        delivery.setDueDate(String.valueOf(dtpickerDueDate.getValue()));

//        String deliveryId = txtDeliveryId.getText();
//        String empId = cmbEmployeeId.getValue();
//        String orderId = lblOrderId.getText();
//        String vehiId = cmbVehiId.getValue();
//        String location = txtLocation.getText();
//        String deliveryDate = lblDate.getText();
//        String dueDate = String.valueOf(dtpickerDueDate.getValue());
//
//        Delivery delivery = new Delivery(deliveryId, empId, orderId, vehiId, location, deliveryDate, dueDate);
//
//        System.out.println(empId);
//        System.out.println(orderId);
//        System.out.println(vehiId);
        try {
            PlaceOrderModel.saveDelivery(delivery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("delivery "+throwables);
        }

        anchrpDelivery02.getScene().getWindow().hide();

    }

    private void loadEmployeeIds(){
        try {
            ObservableList<String> empIds = FXCollections.observableArrayList();
            List<String> ids = EmployeeModel.loadEmpIds();

            for(String id : ids){
                empIds.add(id);
            }

            cmbEmployeeId.setItems(empIds);
        } catch (SQLException throwables) {
            AlertController.exceptionMessage("loadEmployeeIds() "+throwables);
        }
    }

    private void loadVehicleIds() {
        ObservableList<String> vehiIds = FXCollections.observableArrayList();
        List<String> ids = null;
        try {
            ids = VehicleModel.loadVehiIds();

            for(String id : ids){
                vehiIds.add(id);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            AlertController.exceptionMessage("loadVehicleIds() "+e);
        }

        cmbVehiId.setItems(vehiIds);
    }

    private void generateNextOrderId() {
        try {
            String id = CashierOrderModel.getNextOrderId();
            lblOrderId.setText(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void generateNextDeliveryId() {
        try {
            String id = DeliveryModel.getNextDeliveryId();
            txtDeliveryId.setText(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert anchrpDelivery02 != null : "fx:id=\"anchrpDelivery02\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";
        assert cmbEmployeeId != null : "fx:id=\"cmbEmployeeId\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";
        assert cmbVehiId != null : "fx:id=\"cmbVehiId\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";
        assert dtpickerDueDate != null : "fx:id=\"dtpickerDueDate\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";
        assert lblOrderId != null : "fx:id=\"lblOrderId\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";
        assert txtDeliveryId != null : "fx:id=\"txtDeliveryId\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";
        assert txtLocation != null : "fx:id=\"txtLocation\" was not injected: check your FXML file 'cashierDelivery02_form.fxml'.";

        delivery = new Delivery();

        loadEmployeeIds();
        loadVehicleIds();
        generateNextOrderId();
        generateNextDeliveryId();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }
}
