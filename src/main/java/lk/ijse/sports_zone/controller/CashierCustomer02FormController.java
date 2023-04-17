package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.sports_zone.dto.tm.CustomerTM;
import lk.ijse.sports_zone.model.CustomerModel;
import lk.ijse.sports_zone.util.AlertController;
import lk.ijse.sports_zone.util.DateAndTimeConntroller;
import lk.ijse.sports_zone.util.NotificationController;
import lk.ijse.sports_zone.util.ValidateController;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CashierCustomer02FormController {
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
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblInvalidContacktNo;

    @FXML
    private Label lblInvalidCustId;

    @FXML
    private Label lblInvalidEmail;

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
        String id = txtCustId.getText();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to delete ?");
        if(result){
            try {
                boolean isDelete = CustomerModel.delete(id);
                if(isDelete){
                    setCellValueFactory();
                    getAll();
                    clearTxtField();
                    genarateNextCustId();
                    btnSave.setDisable(false);
                    AlertController.okMassage("Delete Successful");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
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
//        Customer customer = new Customer();
//
//        customer.setCustId(txtCustId.getText());
//        customer.setCustName(txtCustName.getText());
//        customer.setContactNo(txtContactNo.getText());
//        customer.setAddress(txtAddress.getText());
//        customer.setEmail(txtEmail.getText());
//
//        boolean result = AlertController.okconfirmmessage("Are you sure you want to update ?");
//        if(result){
//            try {
//                boolean isUpdated = CustomerModel.update(customer);
//                if(isUpdated){
//                    NotificationController.successful("Updated Successful");
//                    setCellValueFactory();
//                    getAll();
//                    clearTxtField();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//                NotificationController.catchException(throwables);
//            }
//        }
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
    void txtContactNoOnMouseClickedAction(MouseEvent event) {
        lblInvalidContacktNo.setVisible(false);
    }

    @FXML
    void txtCustIdOnMouseClickedAction(MouseEvent event) {
        lblInvalidCustId.setVisible(false);
    }

    @FXML
    void txtEmailOnMouseClickedAction(MouseEvent event) {
        lblInvalidEmail.setVisible(false);
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

    private void genarateNextCustId() {
        try {
            String id = CustomerModel.getNextCustId();
            txtCustId.setText(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        genarateNextCustId();
        setCellValueFactory();
        getAll();

        lblInvalidContacktNo.setVisible(false);
        lblInvalidCustId.setVisible(false);
        lblInvalidEmail.setVisible(false);
    }
}
