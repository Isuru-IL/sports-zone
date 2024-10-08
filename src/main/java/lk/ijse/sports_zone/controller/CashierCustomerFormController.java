package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sports_zone.dto.Customer;
import lk.ijse.sports_zone.dto.Employee;
import lk.ijse.sports_zone.dto.tm.CustomerTM;
import lk.ijse.sports_zone.dto.tm.EmployeeTM;
import lk.ijse.sports_zone.model.CustomerModel;
import lk.ijse.sports_zone.model.EmployeeModel;
import lk.ijse.sports_zone.model.RepairModel;
import lk.ijse.sports_zone.util.*;

public class CashierCustomerFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpCashierButton;

    @FXML
    private AnchorPane anchrpCashierCustomerMain;

    @FXML
    private AnchorPane anchrpCustomer;

    @FXML
    private JFXButton btnCashierInventory;

    @FXML
    private ImageView btnCashierLogout;

    @FXML
    private JFXButton btnCashierOrders;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnDelivery;

    @FXML
    private JFXButton btnRepair;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSupplies;

    @FXML
    private ImageView buttonDashBoard;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private Label lblInvalidContacktNo;

    @FXML
    private Label lblInvalidCustId;

    @FXML
    private Label lblInvalidEmail;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSearch;

    @FXML
    void SearchBarOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            Customer customer = CustomerModel.search(id);
            if(customer != null){
                txtCustId.setText(customer.getCustId());
                txtCustName.setText(customer.getCustName());
                txtContactNo.setText(customer.getContactNo());
                txtAddress.setText(customer.getAddress());
                txtEmail.setText(customer.getEmail());
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
        String id = txtCustId.getText();

        try {
            Customer customer = CustomerModel.search(id);
            if(customer != null){
                txtCustId.setText(customer.getCustId());
                txtCustName.setText(customer.getCustName());
                txtContactNo.setText(customer.getContactNo());
                txtAddress.setText(customer.getAddress());
                txtEmail.setText(customer.getEmail());
            }else{
                AlertController.errormessage(id+" is invalid ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }
    }

    @FXML
    void searchIconOnClickedAction(MouseEvent event) {
        String id = txtSearch.getText();

        try {
            Customer customer = CustomerModel.search(id);
            if(customer != null){
                txtCustId.setText(customer.getCustId());
                txtCustName.setText(customer.getCustName());
                txtContactNo.setText(customer.getContactNo());
                txtAddress.setText(customer.getAddress());
                txtEmail.setText(customer.getEmail());
            }else{
                AlertController.errormessage(id+" is invalid ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }
    }

    @FXML
    void txtSearchKeyTypedOnAction(KeyEvent event) throws SQLException {
        String searchValue = txtSearch.getText().trim();

        ObservableList<CustomerTM> obList= FXCollections.observableArrayList();

        List<CustomerTM> data = CustomerModel.getAll();

        for(CustomerTM customerTM : data){
            obList.add(new CustomerTM(
                    customerTM.getCustId(),
                    customerTM.getCustName(),
                    customerTM.getContactNo(),
                    customerTM.getAddress(),
                    customerTM.getEmail()
            ));
        }
        if (!searchValue.isEmpty()) {
            ObservableList<CustomerTM> filteredData = obList.filtered(new Predicate<CustomerTM>(){
                @Override
                public boolean test(CustomerTM customerTM) {
                    return String.valueOf(customerTM.getCustId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblCustomer.setItems(filteredData);
        } else {
            tblCustomer.setItems(obList);
        }
    }

    @FXML
    void tabelOnMouseClickedAction(MouseEvent event) {
        btnSave.setDisable(true);

        TablePosition pos=tblCustomer.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<CustomerTM,?>> columns=tblCustomer.getColumns();

        txtCustId.setText(columns.get(0).getCellData(row).toString());
        txtCustName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtContactNo.setText(columns.get(3).getCellData(row).toString());
        txtEmail.setText(columns.get(4).getCellData(row).toString());

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        if(txtCustId.getText().isEmpty() || txtCustName.getText().isEmpty()){
            AlertController.errormessage("Invalid Id");
        }else {
            String id = txtCustId.getText();
            boolean result = AlertController.okconfirmmessage("Are you sure you want to delete ?");
            if (result) {
                try {
                    boolean isDelete = CustomerModel.delete(id);
                    if (isDelete) {
                        setCellValueFactory();
                        getAll();
                        clearTxtField();
                        genarateNextCustId();
                        btnSave.setDisable(false);
                        AlertController.okMassage("Customer Deleted Successfully");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println(throwables);
                }
            }
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Customer customer = new Customer();

        customer.setCustId(txtCustId.getText());
        customer.setCustName(txtCustName.getText());
        customer.setContactNo(txtContactNo.getText());
        customer.setAddress(txtAddress.getText());
        customer.setEmail(txtEmail.getText());

        if(customer.getCustId().isEmpty() || customer.getCustName().isEmpty() || customer.getContactNo().isEmpty()){
            AlertController.errormessage("Customer details not saved.\nPlease make sure to fill out all the required fields.");
        }else {
            //if(customer.getCustId().isEmpty()){
                if (ValidateController.emailCheck(customer.getEmail()) || ValidateController.contactCheck(customer.getContactNo())) {
                    if (ValidateController.contactCheck(customer.getContactNo())) {
                        if (ValidateController.emailCheck(customer.getEmail()) || customer.getEmail().isEmpty()) {


                            try {
                                boolean isSaved = CustomerModel.save(customer);
                                if(isSaved){
                                    setCellValueFactory();
                                    getAll();
                                    clearTxtField();
                                    genarateNextCustId();
                                    AlertController.okMassage("Customer Saved Successfully");
                                }else{
                                    AlertController.errormessage("Customer Saved Unsuccessfully");
                                }
                            } catch (SQLIntegrityConstraintViolationException e) {
                                //System.out.println(e);
                                AlertController.errormessage("Duplicate Customer ID");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                                System.out.println(throwables);
                            }

                        } else {
                            lblInvalidEmail.setVisible(true);
                        }
                    } else {
                        lblInvalidContacktNo.setVisible(true);
                    }
                } else {
                    lblInvalidEmail.setVisible(true);
                    lblInvalidContacktNo.setVisible(true);
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
        Customer customer = new Customer();

        customer.setCustId(txtCustId.getText());
        customer.setCustName(txtCustName.getText());
        customer.setContactNo(txtContactNo.getText());
        customer.setAddress(txtAddress.getText());
        customer.setEmail(txtEmail.getText());

            if(customer.getCustId().isEmpty() || customer.getCustName().isEmpty() || customer.getContactNo().isEmpty()){
                AlertController.errormessage("Customer details not updated.\nPlease make sure to fill out all the required fields.");
            }else {
                //if(customer.getCustId().isEmpty()){
                if (ValidateController.emailCheck(customer.getEmail()) || ValidateController.contactCheck(customer.getContactNo())) {
                    if (ValidateController.contactCheck(customer.getContactNo())) {
                        if (ValidateController.emailCheck(customer.getEmail()) || customer.getEmail().isEmpty()) {

                            boolean result = AlertController.okconfirmmessage("Are you sure you want to update ?");
                            if(result) {
                                try {
                                    boolean isUpdated = CustomerModel.update(customer);
                                    if (isUpdated) {
                                        setCellValueFactory();
                                        getAll();
                                        clearTxtField();
                                        genarateNextCustId();
                                        btnSave.setDisable(false);
                                        AlertController.okMassage("Customer updated Successfully");
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                    System.out.println(throwables);
                                }
                            }

                        } else {
                            lblInvalidEmail.setVisible(true);
                        }
                    } else {
                        lblInvalidContacktNo.setVisible(true);
                    }
                } else {
                    lblInvalidEmail.setVisible(true);
                    lblInvalidContacktNo.setVisible(true);
                }
            }
//        else{
//                System.out.println(customer.getCustId());
//                lblInvalidCustId.setVisible(true);
//                //txtCustId.setStyle("-fx-text-fill: red");
//            }
    }

    @FXML
    void txtcustIdOnMouseClickedAction(MouseEvent event) {
        lblInvalidCustId.setVisible(false);
    }

    @FXML
    void txtContactNoOnMouseClickedAction(MouseEvent event) {
        lblInvalidContacktNo.setVisible(false);
    }

    @FXML
    void txtEmailOnMouseClickedAction(MouseEvent event) {
        lblInvalidEmail.setVisible(false);
    }

    @FXML
    void btnCashierInventoryOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/inventory_form.fxml"));
        anchrpCustomer.getChildren().clear();
        anchrpCustomer.getChildren().add(load);

        ButtonColourController.btncolor(btnCashierInventory, anchrpCustomer);
        btnCustomer.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }

    @FXML
    void btnCashierOrdersOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierOrder_form.fxml"));
        anchrpCustomer.getChildren().clear();
        anchrpCustomer.getChildren().add(load);

        ButtonColourController.btncolor(btnCashierOrders, anchrpCustomer);
        btnCustomer.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }

    @FXML
    void btnSuppliesOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminSupplierLoad_form.fxml"));
        anchrpCustomer.getChildren().clear();
        anchrpCustomer.getChildren().add(load);

        ButtonColourController.btncolor(btnSupplies, anchrpCustomer);
        btnCustomer.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }


    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierCustomer_form.fxml"));
        anchrpCashierCustomerMain.getChildren().clear();
        anchrpCashierCustomerMain.getChildren().add(load);

        ButtonColourController.btncolor(btnCustomer, anchrpCustomer);
        btnCustomer.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }

    @FXML
    void btnDeliveryOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierDelivery_form.fxml"));
        anchrpCustomer.getChildren().clear();
        anchrpCustomer.getChildren().add(load);

        ButtonColourController.btncolor(btnDelivery, anchrpCustomer);
        btnCustomer.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }

    @FXML
    void btnRepairOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierRepair_form.fxml"));
        anchrpCustomer.getChildren().clear();
        anchrpCustomer.getChildren().add(load);

        ButtonColourController.btncolor(btnRepair, anchrpCustomer);
        btnCustomer.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }



    @FXML
    void logOutIconOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)anchrpCustomer.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert anchrpCashierButton != null : "fx:id=\"anchrpCashierButton\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert anchrpCashierCustomerMain != null : "fx:id=\"anchrpCashierCustomerMain\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert anchrpCustomer != null : "fx:id=\"anchrpCustomer\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert btnCashierInventory != null : "fx:id=\"btnCashierInventory\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert btnCashierLogout != null : "fx:id=\"btnCashierLogout\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert btnCashierOrders != null : "fx:id=\"btnCashierOrders\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert btnCustomer != null : "fx:id=\"btnCustomer\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert btnDelivery != null : "fx:id=\"btnDelivery\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert buttonDashBoard != null : "fx:id=\"buttonDashBoard\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";

        btnCustomer.setStyle("-fx-background-color: #100000;" +
                "-fx-background-radius: 20px;");

        DateAndTimeConntroller d1 = new DateAndTimeConntroller();
        d1.Timenow(lblTime, lblDate);

        genarateNextCustId();
        setCellValueFactory();
        getAll();

        lblInvalidEmail.setVisible(false);
        lblInvalidCustId.setVisible(false);
        lblInvalidContacktNo.setVisible(false);
    }

    private void genarateNextCustId() {
        try {
            String id = CustomerModel.getNextCustId();
            txtCustId.setText(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void clearTxtField() {
        txtCustId.setText("");
        txtCustName.setText("");
        txtContactNo.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtSearch.setText("");
    }

    private void getAll(){
        try {
            ObservableList<CustomerTM> obList = CustomerModel.getAll();
            tblCustomer.setItems(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
