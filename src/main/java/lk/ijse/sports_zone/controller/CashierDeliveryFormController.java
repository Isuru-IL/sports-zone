package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Delivery;
import lk.ijse.sports_zone.dto.Supplier;
import lk.ijse.sports_zone.dto.tm.DeliveryTM;
import lk.ijse.sports_zone.dto.tm.SupplierTM;
import lk.ijse.sports_zone.dto.tm.VehicleTM;
import lk.ijse.sports_zone.model.DeliveryModel;
import lk.ijse.sports_zone.model.EmployeeModel;
import lk.ijse.sports_zone.model.SupplierModel;
import lk.ijse.sports_zone.model.VehicleModel;
import lk.ijse.sports_zone.util.AlertController;

public class CashierDeliveryFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpDelivery;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbEmpId;

    @FXML
    private ComboBox<String> cmbVehiId;

    @FXML
    private TableColumn<?, ?> colDeliveryDate;

    @FXML
    private TableColumn<?, ?> colDeliveryId;

    @FXML
    private TableColumn<?, ?> colDeliveryStatus;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private RadioButton radioBtnNo;

    @FXML
    private RadioButton radioBtnYes;

    @FXML
    private TableView<DeliveryTM> tblDelivery;

    @FXML
    private DatePicker txtDeliveryDate;

    @FXML
    private Label lblDeliveryId;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtSearch;

    private Delivery delivery;

    @FXML
    void SearchBarOnAction(ActionEvent event) {
        try {
            delivery = DeliveryModel.search(txtSearch.getText());
            if(delivery != null){
                lblDeliveryId.setText(delivery.getDeliveryId());
                cmbEmpId.setValue(delivery.getEmpId());
                cmbVehiId.setValue(delivery.getVehiId());
                txtLocation.setText(delivery.getLocation());
                txtDeliveryDate.setValue(LocalDate.parse(delivery.getDeliveryDate()));

                if(delivery.getDeliveryStaus().equals("Yes")){
                    radioBtnYes.setSelected(true);
                }else if(delivery.getDeliveryStaus().equals("No")){
                    radioBtnNo.setSelected(true);
                }else {
                    radioBtnNo.setSelected(false);
                    radioBtnYes.setSelected(false);
                }

            }else{
                AlertController.errormessage("Invalid Id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbVehicleIdOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = lblDeliveryId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to delete ?");
        if(result) {
            try {
                boolean isDeleted = DeliveryModel.delete(id);
                if (isDeleted) {
                    setCellValueFactory();
                    getAll();
                    clearTxtField();
                    AlertController.okMassage("Delete successful");
                }

            } catch (SQLException throwables) {
                //throwables.printStackTrace();
                System.out.println("delivery delete = " + throwables);
            }
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        if(radioBtnYes.isSelected() || radioBtnNo.isSelected()){
            delivery.setDeliveryId(lblDeliveryId.getText());
            delivery.setEmpId(cmbEmpId.getValue());
            delivery.setVehiId(cmbVehiId.getValue());
            delivery.setLocation(txtLocation.getText());
            delivery.setDeliveryDate(String.valueOf(txtDeliveryDate.getValue()));

//            System.out.println(delivery.getEmpId());
//            System.out.println(delivery.getVehiId());
//            System.out.println(delivery.getLocation());
//            System.out.println(delivery.getDeliveryDate());
//            System.out.println(delivery.getDeliveryStaus());

            boolean result = AlertController.okconfirmmessage("Are you sure you want to update ?");
            if (result) {
                try {
                    boolean isUpdated = DeliveryModel.update(delivery);
                    if (isUpdated) {
                        setCellValueFactory();
                        getAll();
                        clearTxtField();
                        AlertController.okMassage("Update successful");
                    }else{
                        AlertController.errormessage("Update unsuccessful");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }else{
            AlertController.errormessage("Please select delivery status");
        }
    }

    @FXML
    void radioBtnNoOnAction(ActionEvent event) {
        if(radioBtnNo.isSelected()){
            delivery.setDeliveryStaus("No");
        }
    }

    @FXML
    void radioBtnYesOnAction(ActionEvent event) {
        if(radioBtnYes.isSelected()){
            delivery.setDeliveryStaus("Yes");
        }
    }

    @FXML
    void searchIconOnMouseClickedAction(MouseEvent event) {
        try {
            delivery = DeliveryModel.search(txtSearch.getText());
            if(delivery != null){
                lblDeliveryId.setText(delivery.getDeliveryId());
                cmbEmpId.setValue(delivery.getEmpId());
                cmbVehiId.setValue(delivery.getVehiId());
                txtLocation.setText(delivery.getLocation());
                txtDeliveryDate.setValue(LocalDate.parse(delivery.getDeliveryDate()));

                if(delivery.getDeliveryStaus().equals("Yes")){
                    radioBtnYes.setSelected(true);
                }else if(delivery.getDeliveryStaus().equals("No")){
                    radioBtnNo.setSelected(true);
                }else {
                    radioBtnNo.setSelected(false);
                    radioBtnYes.setSelected(false);
                }

            }else{
                AlertController.errormessage("Invalid Id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void tableOnMouseClicked(MouseEvent event) {
        TablePosition pos=tblDelivery.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<DeliveryTM,?>> columns=tblDelivery.getColumns();

        lblDeliveryId.setText(columns.get(0).getCellData(row).toString());
        cmbEmpId.setValue(columns.get(1).getCellData(row).toString());
        cmbVehiId.setValue(columns.get(3).getCellData(row).toString());
        txtLocation.setText(columns.get(4).getCellData(row).toString());
        txtDeliveryDate.setValue(LocalDate.parse(columns.get(5).getCellData(row).toString()));
    }

    @FXML
    void txtSearchOnKeyTypedAction(KeyEvent event) {
        String searchValue = txtSearch.getText().trim();

        try {
            ObservableList<DeliveryTM> obList= DeliveryModel.getAll();

            if (!searchValue.isEmpty()) {
                ObservableList<DeliveryTM> filteredData = obList.filtered(new Predicate<DeliveryTM>(){
                    @Override
                    public boolean test(DeliveryTM deliveryTM) {
                        return String.valueOf(deliveryTM.getDeliveryId()).toLowerCase().contains(searchValue.toLowerCase());
                    }
                });
                tblDelivery.setItems(filteredData);
            } else {
                tblDelivery.setItems(obList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        assert anchrpDelivery != null : "fx:id=\"anchrpDelivery\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert cmbEmpId != null : "fx:id=\"cmbEmpId\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert cmbVehiId != null : "fx:id=\"cmbVehiId\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert colDeliveryDate != null : "fx:id=\"colDeliveryDate\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert colDeliveryId != null : "fx:id=\"colDeliveryId\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert colDeliveryStatus != null : "fx:id=\"colDeliveryStatus\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert colDueDate != null : "fx:id=\"colDueDate\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert colEmployeeId != null : "fx:id=\"colEmployeeId\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert colLocation != null : "fx:id=\"colLocation\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert colOrderId != null : "fx:id=\"colOrderId\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert colVehicleId != null : "fx:id=\"colVehicleId\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert radioBtnNo != null : "fx:id=\"radioBtnNo\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert radioBtnYes != null : "fx:id=\"radioBtnYes\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert tblDelivery != null : "fx:id=\"tblDelivery\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert txtDeliveryDate != null : "fx:id=\"txtDeliveryDate\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert txtLocation != null : "fx:id=\"txtLocation\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";

        delivery = new Delivery();

        loadEmployeeIds();
        loadVehicleIds();

        setCellValueFactory();
        getAll();
    }

    private void loadVehicleIds() {
        try {
            ObservableList<String> vehiIds = FXCollections.observableArrayList();
            List<String> ids = VehicleModel.loadVehiIds();

            for(String id : ids){
                vehiIds.add(id);
            }

            cmbVehiId.setItems(vehiIds);
        } catch (SQLException throwables) {
            AlertController.errormessage("DeliveryFC loadVehicleIds");
        }
    }

    private void loadEmployeeIds() {
        try {
            ObservableList<String> empIds = FXCollections.observableArrayList();
            List<String> ids = EmployeeModel.loadEmpIds();

            for(String id : ids){
                empIds.add(id);
            }

            cmbEmpId.setItems(empIds);
        } catch (SQLException throwables) {
            AlertController.errormessage("DeliveryFC loadEmployeeIds");
        }
    }

    private void setCellValueFactory() {
        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehiId"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("deliveryStaus"));
    }

    private void clearTxtField(){
        lblDeliveryId.setText("");
        cmbEmpId.setValue(null);
        cmbVehiId.setValue(null);
        txtLocation.setText("");
        txtDeliveryDate.setValue(null);
        radioBtnYes.setSelected(false);
        radioBtnNo.setSelected(false);
    }

    private void getAll(){
        try {
            ObservableList<DeliveryTM> oblist = DeliveryModel.getAll();
            tblDelivery.setItems(oblist);
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("Delivery getAll = "+throwables);
        }
    }

}
