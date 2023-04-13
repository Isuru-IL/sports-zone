package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Supplier;
import lk.ijse.sports_zone.dto.Vehicle;
import lk.ijse.sports_zone.dto.tm.SupplierTM;
import lk.ijse.sports_zone.dto.tm.VehicleTM;
import lk.ijse.sports_zone.model.SupplierModel;
import lk.ijse.sports_zone.model.VehicleModel;
import lk.ijse.sports_zone.util.AlertController;

public class AdminVehicleFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ancrpVehicle;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colVehiNo;

    @FXML
    private TableColumn<?, ?> colVehiType;

    @FXML
    private TableColumn<?, ?> colvehiId;

    @FXML
    private TableView<VehicleTM> tblvehicle;

    @FXML
    private TextField txtVehiId;

    @FXML
    private TextField txtVehiNo;

    @FXML
    private TextField txtVehiType;

    private Vehicle vehicle;

    @FXML
    void SearchOnAction(ActionEvent event) {
        String id = txtVehiId.getText();

        try {
            vehicle = VehicleModel.search(id);

            if(vehicle != null){
                txtVehiId.setText(vehicle.getVehiId());
                txtVehiNo.setText(vehicle.getVehiNo());
                txtVehiType.setText(vehicle.getVehiType());
            }else{
                AlertController.errormessage("Invalid ID");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            AlertController.exceptionMessage("Somthing went wrong");
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtVehiId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update ?");
        if(result) {
            try {
                boolean isDeleted = VehicleModel.delete(id);
                if (isDeleted) {
                    setCellValueFactory();
                    getAll();
                    clearTxtField();
                    AlertController.okMassage("Delete successful");
                }

            } catch (SQLException throwables) {
                //throwables.printStackTrace();
                System.out.println("Vehicle delete = " + throwables);
            }
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        vehicle.setVehiId(txtVehiId.getText());
        vehicle.setVehiType(txtVehiType.getText());
        vehicle.setVehiNo(txtVehiNo.getText());

        try {
            boolean isSaved = VehicleModel.save(vehicle);
            if(isSaved){
                setCellValueFactory();
                getAll();
                clearTxtField();
                AlertController.okMassage("Saved successfully");
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("Vehicle save = "+throwables);
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        vehicle.setVehiId(txtVehiId.getText());
        vehicle.setVehiNo(txtVehiNo.getText());
        vehicle.setVehiType(txtVehiType.getText());

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update ?");
        if (result) {
            try {
                boolean isUpdated = VehicleModel.update(vehicle);
                if (isUpdated) {
                    setCellValueFactory();
                    getAll();
                    clearTxtField();
                    AlertController.okMassage("Update successful");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @FXML
    void tableOnMouseClicked(MouseEvent event) {
        TablePosition pos=tblvehicle.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<VehicleTM,?>> columns=tblvehicle.getColumns();

        txtVehiId.setText(columns.get(0).getCellData(row).toString());
        txtVehiType.setText(columns.get(1).getCellData(row).toString());
        txtVehiNo.setText(columns.get(2).getCellData(row).toString());
    }

    private void setCellValueFactory() {
        colvehiId.setCellValueFactory(new PropertyValueFactory<>("vehiId"));
        colVehiType.setCellValueFactory(new PropertyValueFactory<>("vehiType"));
        colVehiNo.setCellValueFactory(new PropertyValueFactory<>("vehiNo"));
    }

    private void getAll(){
        try {
            ObservableList<VehicleTM> oblist = VehicleModel.getAll();
            tblvehicle.setItems(oblist);
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("Vehicle getAll = "+throwables);
        }
    }

    private void clearTxtField(){
        txtVehiId.setText("");
        txtVehiNo.setText("");
        txtVehiType.setText("");
    }

    @FXML
    void initialize() {
        vehicle = new Vehicle();

        setCellValueFactory();
        getAll();
    }

}
