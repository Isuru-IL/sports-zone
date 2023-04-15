package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
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
import lk.ijse.sports_zone.dto.Employee;
import lk.ijse.sports_zone.dto.Inventory;
import lk.ijse.sports_zone.dto.tm.EmployeeTM;
import lk.ijse.sports_zone.dto.tm.InventoryTM;
import lk.ijse.sports_zone.model.EmployeeModel;
import lk.ijse.sports_zone.model.InventoryModel;
import lk.ijse.sports_zone.model.RepairModel;
import lk.ijse.sports_zone.util.AlertController;
import lk.ijse.sports_zone.util.NotificationController;
import lk.ijse.sports_zone.util.ValidateController;

public class InventoryFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpSupplier;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblInvalidQuantity;

    @FXML
    private Label lblInvalidUnitPrice;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colCatagory;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<InventoryTM> tblItem;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void searchIconOnMouseClickedAction(MouseEvent event) {
        String id = txtSearch.getText();

        try {
            Inventory inventory = InventoryModel.search(id);

            if(inventory != null){
                txtItemId.setText(inventory.getItemCode());
                txtItemName.setText(inventory.getItemName());
                txtCategory.setText(inventory.getCategory());
                txtBrand.setText(inventory.getBrand());
                txtUnitPrice.setText(String.valueOf(inventory.getUnitPrice()));
                txtQty.setText(String.valueOf(inventory.getQtyOnHand()));
            }else{
                AlertController.errormessage(id+"is invalid Id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void SearchBarOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            Inventory inventory = InventoryModel.search(id);

            if(inventory != null){
                txtItemId.setText(inventory.getItemCode());
                txtItemName.setText(inventory.getItemName());
                txtCategory.setText(inventory.getCategory());
                txtBrand.setText(inventory.getBrand());
                txtUnitPrice.setText(String.valueOf(inventory.getUnitPrice()));
                txtQty.setText(String.valueOf(inventory.getQtyOnHand()));
            }else{
                AlertController.errormessage(id+"is invalid Id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void SearchOnAction(ActionEvent event) {
        String id = txtItemId.getText();

        try {
            Inventory inventory = InventoryModel.search(id);

            if(inventory != null){
                txtItemId.setText(inventory.getItemCode());
                txtItemName.setText(inventory.getItemName());
                txtCategory.setText(inventory.getCategory());
                txtBrand.setText(inventory.getBrand());
                txtUnitPrice.setText(String.valueOf(inventory.getUnitPrice()));
                txtQty.setText(String.valueOf(inventory.getQtyOnHand()));
            }else{
                AlertController.errormessage(id+"is invalid Id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void tableOnMouseClicked(MouseEvent event) {
        TablePosition pos=tblItem.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<InventoryTM,?>> columns=tblItem.getColumns();

        txtItemId.setText(columns.get(0).getCellData(row).toString());
        txtItemName.setText(columns.get(1).getCellData(row).toString());
        txtCategory.setText(columns.get(2).getCellData(row).toString());
        txtBrand.setText(columns.get(3).getCellData(row).toString());
        txtUnitPrice.setText(columns.get(4).getCellData(row).toString());
        txtQty.setText(columns.get(5).getCellData(row).toString());
    }

    @FXML
    void txtSearchOnKeyTypedAction(KeyEvent event) throws SQLException {
        String searchValue = txtSearch.getText().trim();

        ObservableList<InventoryTM> obList= FXCollections.observableArrayList();

        List<Inventory> data = InventoryModel.getAll();

        for(Inventory inventory : data){
            obList.add(new InventoryTM(
                    inventory.getItemCode(),
                    inventory.getItemName(),
                    inventory.getCategory(),
                    inventory.getBrand(),
                    inventory.getUnitPrice(),
                    inventory.getQtyOnHand()
            ));
        }
        if (!searchValue.isEmpty()) {
            ObservableList<InventoryTM> filteredData = obList.filtered(new Predicate<InventoryTM>(){
                @Override
                public boolean test(InventoryTM inventoryTM) {
                    return String.valueOf(inventoryTM.getItemCode()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblItem.setItems(filteredData);
        } else {
            tblItem.setItems(obList);
        }
    }


    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to delete ?");
        if(result){
            try {
                boolean isDeleted= InventoryModel.delete(id);
                if(isDeleted){
                    setCellValueFactory();
                    getAll();
                    clearTxtField();
                    AlertController.okMassage("Item Deleted Successfully");

                }else {
                    AlertController.errormessage("Item Deleted Unsuccessfully");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Inventory inventory = new Inventory();

        if(txtItemId.getText().isEmpty() || txtItemName.getText().isEmpty() || txtCategory.getText().isEmpty() || txtBrand.getText().isEmpty()){
            AlertController.errormessage("Item details not saved.\nPlease make sure to fill out all the required fields.");
        }else {
            //if(customer.getCustId().isEmpty()){
            if (ValidateController.doubleValueCheck(txtUnitPrice.getText()) || ValidateController.intValueCheck(txtQty.getText())) {
                if (ValidateController.intValueCheck(txtQty.getText())) {
                    if (ValidateController.doubleValueCheck(txtUnitPrice.getText())) {

                        try {
                            inventory.setItemCode(txtItemId.getText());
                            inventory.setItemName(txtItemName.getText());
                            inventory.setCategory(txtCategory.getText());
                            inventory.setBrand(txtBrand.getText());
                            inventory.setUnitPrice(Double.valueOf(txtUnitPrice.getText()));
                            inventory.setQtyOnHand(Integer.valueOf(txtQty.getText()));
                            boolean isSaved = InventoryModel.save(inventory);

                            if(isSaved){
                                setCellValueFactory();
                                getAll();
                                clearTxtField();
                                AlertController.okMassage("Item Saved Successfully");

                            }else{
                                AlertController.errormessage("Item Saved Unsuccessfully");
                            }

                        } catch (Exception throwables) {
                            //throwables.printStackTrace();
                            AlertController.errormessage(throwables+"");
                        }
                    } else {
                        lblInvalidUnitPrice.setVisible(true);
                    }
                } else {
                    lblInvalidQuantity.setVisible(true);
                }
            } else {
                lblInvalidQuantity.setVisible(true);
                lblInvalidUnitPrice.setVisible(true);
            }
        }
//        else{
//                System.out.println(customer.getCustId());
//                lblInvalidCustId.setVisible(true);
//                //txtCustId.setStyle("-fx-text-fill: red");
//            }
    }


    @FXML
    void updateOnAction(ActionEvent event) {
        Inventory inventory = new Inventory();

        if(txtItemId.getText().isEmpty() || txtItemName.getText().isEmpty() || txtCategory.getText().isEmpty() || txtBrand.getText().isEmpty()){
            AlertController.errormessage("Item details not updated.\nPlease make sure to fill out all the required fields.");
        }else {
            //if(customer.getCustId().isEmpty()){
            if (ValidateController.doubleValueCheck(txtUnitPrice.getText()) || ValidateController.intValueCheck(txtQty.getText())) {
                if (ValidateController.intValueCheck(txtQty.getText())) {
                    if (ValidateController.doubleValueCheck(txtUnitPrice.getText())) {

                        boolean result = AlertController.okconfirmmessage("Are you sure you want to update ?");
                        if(result) {
                            try {
                                inventory.setItemCode(txtItemId.getText());
                                inventory.setItemName(txtItemName.getText());
                                inventory.setCategory(txtCategory.getText());
                                inventory.setBrand(txtBrand.getText());
                                inventory.setUnitPrice(Double.valueOf(txtUnitPrice.getText()));
                                inventory.setQtyOnHand(Integer.valueOf(txtQty.getText()));

                                boolean isUpdated = InventoryModel.update(inventory);
                                if (isUpdated) {
                                    setCellValueFactory();
                                    getAll();
                                    clearTxtField();
                                    AlertController.okMassage("Item Updated Successfully");
                                } else {
                                    AlertController.errormessage("Item Updated Unsuccessfully");
                                }
                            } catch (Exception throwables) {
                                //throwables.printStackTrace();
                                AlertController.errormessage(throwables + "");
                            }
                        }
                    } else {
                        lblInvalidUnitPrice.setVisible(true);
                    }
                } else {
                    lblInvalidQuantity.setVisible(true);
                }
            } else {
                lblInvalidQuantity.setVisible(true);
                lblInvalidUnitPrice.setVisible(true);
            }
        }
//        else{
//                System.out.println(customer.getCustId());
//                lblInvalidCustId.setVisible(true);
//                //txtCustId.setStyle("-fx-text-fill: red");
//            }
    }

    @FXML
    void txtQuantityOnMouseClickedAction(MouseEvent event) {
        lblInvalidQuantity.setVisible(false);
    }
    @FXML
    void txtUnitPriceOnMouseClickedAction(MouseEvent event) {
        lblInvalidUnitPrice.setVisible(false);
    }


    @FXML
    void initialize() {
        assert anchrpSupplier != null : "fx:id=\"anchrpSupplier\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert colBrand != null : "fx:id=\"colBrand\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert colCatagory != null : "fx:id=\"colCatagory\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert colName != null : "fx:id=\"colName\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert colQty != null : "fx:id=\"colQty\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert colUnitPrice != null : "fx:id=\"colUnitPrice\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert tblItem != null : "fx:id=\"tblItem\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert txtBrand != null : "fx:id=\"txtBrand\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert txtCategory != null : "fx:id=\"txtCatagory\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert txtItemId != null : "fx:id=\"txtItemId\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert txtItemName != null : "fx:id=\"txtItemName\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert txtQty != null : "fx:id=\"txtQty\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'inventory_form.fxml'.";
        assert txtUnitPrice != null : "fx:id=\"txtUnitPrice\" was not injected: check your FXML file 'inventory_form.fxml'.";

        genarateNextItemCode();
        setCellValueFactory();
        getAll();

        lblInvalidUnitPrice.setVisible(false);
        lblInvalidQuantity.setVisible(false);
    }

    private void genarateNextItemCode() {
        try {
            String id = InventoryModel.getNextItemCode();
            txtItemId.setText(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
    }

    private void getAll(){
        ObservableList<InventoryTM> oblist = FXCollections.observableArrayList();
        try {
            List<Inventory> allData = InventoryModel.getAll();

            for(Inventory inventory : allData){
                oblist.add(new InventoryTM(
                        inventory.getItemCode(),
                        inventory.getItemName(),
                        inventory.getCategory(),
                        inventory.getBrand(),
                        inventory.getUnitPrice(),
                        inventory.getQtyOnHand()
                ));
            }
            tblItem.setItems(oblist);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void clearTxtField(){
        txtItemId.setText("");
        txtItemName.setText("");
        txtCategory.setText("");
        txtBrand.setText("");
        txtUnitPrice.setText("");
        txtQty.setText("");
    }
}
