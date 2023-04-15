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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sports_zone.dto.Customer;
import lk.ijse.sports_zone.dto.Repair;
import lk.ijse.sports_zone.dto.tm.CustomerTM;
import lk.ijse.sports_zone.dto.tm.RepairTM;
import lk.ijse.sports_zone.model.CashierOrderModel;
import lk.ijse.sports_zone.model.CustomerModel;
import lk.ijse.sports_zone.model.RepairModel;
import lk.ijse.sports_zone.util.AlertController;
import lk.ijse.sports_zone.util.ValidateController;

public class CashierRepairFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpRepair;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ComboBox<String> cmbCustId;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colItem;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colRepairId;

    @FXML
    private ImageView imgSearch;

    @FXML
    private TableView<RepairTM> tblRepair;

    @FXML
    private Label lblInvalidPrice;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtRepairId;

    @FXML
    private TextField txtRepairitem;

    @FXML
    private TextField txtSearch;

    private Repair repair;

    private void loadCustomerIds() {
        try {
            ObservableList<String> custIds = FXCollections.observableArrayList();
            List<String> ids = CustomerModel.loadCustomerIds();

            for(String id : ids){
                custIds.add(id);
            }

            cmbCustId.setItems(custIds);
        } catch (Exception throwables) {
            System.out.println(throwables);
            //AlertController.exceptionMessage("Something went wrong");
        }
    }

    private void genarateNextRepairId() {
        try {
            String id = RepairModel.getNextOrderId();
            txtRepairId.setText(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void SearchBarOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            Repair repair = RepairModel.search(id);
            if(repair != null){
                txtRepairId.setText(repair.getRepairId());
                txtRepairitem.setText(repair.getRepairItem());
                txtDate.setValue(LocalDate.parse(repair.getDate()));
                cmbCustId.setValue(repair.getCustId());
                txtPrice.setText(String.valueOf(repair.getPrice()));
            }else{
                AlertController.errormessage(id+" is invalid ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }
    }

    @FXML
    void SearchOnAction(ActionEvent event) {
        String id = txtRepairId.getText();

        try {
            Repair repair = RepairModel.search(id);
            if(repair != null){
                txtRepairId.setText(repair.getRepairId());
                txtRepairitem.setText(repair.getRepairItem());
                txtDate.setValue(LocalDate.parse(repair.getDate()));
                cmbCustId.setValue(repair.getCustId());
                txtPrice.setText(String.valueOf(repair.getPrice()));
            }else{
                AlertController.errormessage(id+" is invalid ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }
    }

    @FXML
    void cmbCustIdOnAction(ActionEvent event) {

    }


    @FXML
    void searchIconOnMouseClickedAction(MouseEvent event) {
        String id = txtSearch.getText();

        try {
            Repair repair = RepairModel.search(id);
            if(repair != null){
                txtRepairId.setText(repair.getRepairId());
                txtRepairitem.setText(repair.getRepairItem());
                txtDate.setValue(LocalDate.parse(repair.getDate()));
                cmbCustId.setValue(repair.getCustId());
                txtPrice.setText(String.valueOf(repair.getPrice()));
            }else{
                AlertController.errormessage(id+" is invalid ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }
    }

    @FXML
    void tableOnMouseClicked(MouseEvent event) {
        TablePosition pos=tblRepair.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<RepairTM,?>> columns=tblRepair.getColumns();

        txtRepairId.setText(columns.get(0).getCellData(row).toString());
        cmbCustId.setValue(columns.get(1).getCellData(row).toString());
        txtRepairitem.setText(columns.get(2).getCellData(row).toString());
        txtPrice.setText(columns.get(3).getCellData(row).toString());
        txtDate.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));
    }

    @FXML
    void txtSearchOnKeyTypedAction(KeyEvent event) {
        String searchValue = txtSearch.getText().trim();

        ObservableList<RepairTM> obList= FXCollections.observableArrayList();

        List<RepairTM> data = null;
        try {
            data = RepairModel.getAll();

            for(RepairTM repairTM : data){
                obList.add(new RepairTM(
                        repairTM.getRepairId(),
                        repairTM.getCustId(),
                        repairTM.getRepairitem(),
                        repairTM.getDate(),
                        repairTM.getPrice()
                ));
            }
            if (!searchValue.isEmpty()) {
                ObservableList<RepairTM> filteredData = obList.filtered(new Predicate<RepairTM>(){
                    @Override
                    public boolean test(RepairTM repairTM) {
                        return String.valueOf(repairTM.getRepairId()).toLowerCase().contains(searchValue.toLowerCase());
                    }
                });
                tblRepair.setItems(filteredData);
            } else {
                tblRepair.setItems(obList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtRepairId.getText();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to delete ?");
        if(result){
            try {
                boolean isDelete = RepairModel.delete(id);
                if(isDelete){
                    setCellValueFactory();
                    getAll();
                    clearTxtField();
                    genarateNextRepairId();
                    AlertController.okMassage("Deleted Successfully");
                }
            } catch (SQLException throwables) {
                //throwables.printStackTrace();
                System.out.println(throwables);
            }
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {

        if(txtRepairId.getText().isEmpty() || txtRepairitem.getText().isEmpty() || cmbCustId.getValue().isEmpty()){
            AlertController.errormessage("Repair details not saved.\nPlease make sure to fill out all the required fields.");
        }else{
            if(ValidateController.doubleValueCheck(txtPrice.getText())){
                try {
                    repair.setRepairId(txtRepairId.getText());
                    repair.setCustId(cmbCustId.getValue());
                    repair.setRepairItem(txtRepairitem.getText());
                    repair.setDate(String.valueOf(txtDate.getValue()));
                    repair.setPrice(Double.valueOf(txtPrice.getText()));
                    boolean isSaved = RepairModel.save(repair);
                    if(isSaved){
                        setCellValueFactory();
                        getAll();
                        clearTxtField();
                        genarateNextRepairId();
                        AlertController.okMassage("Saved Successfully");
                    }else{
                        AlertController.errormessage("Saved Unsuccessfully");
                    }
                } catch (Exception throwables) {
                    //throwables.printStackTrace();
                    AlertController.errormessage(throwables+"");
                    System.out.println("Repair save "+throwables);
                }
            }else{
                lblInvalidPrice.setVisible(true);
            }
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {

        if(txtRepairId.getText().isEmpty() || txtRepairitem.getText().isEmpty() || cmbCustId.getValue().isEmpty()){
            AlertController.errormessage("Repair details not updated.\nPlease make sure to fill out all the required fields.");
        }else {
            if (ValidateController.doubleValueCheck(txtPrice.getText())) {
                boolean result = AlertController.okconfirmmessage("Are you sure you want to update ?");
                if (result) {
                    repair.setRepairId(txtRepairId.getText());
                    repair.setCustId(cmbCustId.getValue());
                    repair.setRepairItem(txtRepairitem.getText());
                    repair.setDate(String.valueOf(txtDate.getValue()));
                    repair.setPrice(Double.valueOf(txtPrice.getText()));

                    try {
                        boolean isUpdated = RepairModel.update(repair);
                        if (isUpdated) {
                            setCellValueFactory();
                            getAll();
                            clearTxtField();
                            genarateNextRepairId();
                            AlertController.okMassage("updated Successfully");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        System.out.println(throwables);
                    }
                }
            } else {
                lblInvalidPrice.setVisible(true);
            }
        }
    }

    @FXML
    void txtPriceOnMouseClickedAction(MouseEvent event) {
        lblInvalidPrice.setVisible(false);
    }


    private void getAll() {
        try {
            ObservableList<RepairTM> obList = RepairModel.getAll();
            tblRepair.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colRepairId.setCellValueFactory(new PropertyValueFactory<>("repairId"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("repairitem"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void clearTxtField() {
        txtRepairId.setText("");
        txtRepairitem.setText("");
        txtPrice.setText("");
        txtSearch.setText("");
        txtDate.setValue(null);
        cmbCustId.setValue(null);
    }

    @FXML
    void initialize() {
        repair = new Repair();

        loadCustomerIds();
        genarateNextRepairId();
        setCellValueFactory();
        getAll();

        lblInvalidPrice.setVisible(false);
    }

}
