package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sports_zone.dto.Customer;
import lk.ijse.sports_zone.dto.tm.CustomerTM;
import lk.ijse.sports_zone.model.CustomerModel;
import lk.ijse.sports_zone.util.DateAndTimeConntroller;
import lk.ijse.sports_zone.util.NotificationController;

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
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

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

    }

    @FXML
    void SearchOnAction(ActionEvent event) {

    }

    @FXML
    void searchIconOnClickedAction(MouseEvent event) {

    }

    @FXML
    void txtSearchKeyTypedOnAction(KeyEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Customer customer = new Customer();

        customer.setCustId(txtCustId.getText());
        customer.setCustName(txtCustName.getText());
        customer.setContactNo(txtContactNo.getText());
        customer.setAddress(txtAddress.getText());
        customer.setEmail(txtEmail.getText());

        try {
            boolean isSaved = CustomerModel.save(customer);
            if(isSaved){
                NotificationController.successful("Saved Successful");

                setCellValueFactory();
                getAll();
                clearTxtField();

            }else{
                NotificationController.unSuccessful("Saved Unsuccessful");
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            NotificationController.catchException(throwables);
        }
    }


    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @FXML
    void btnCashierInventoryOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/inventory_form.fxml"));
        anchrpCustomer.getChildren().clear();
        anchrpCustomer.getChildren().add(load);
    }

    @FXML
    void btnCashierOrdersOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierCustomer_form.fxml"));
        anchrpCashierCustomerMain.getChildren().clear();
        anchrpCashierCustomerMain.getChildren().add(load);
    }

    @FXML
    void btnDeliveryOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/cashierDelivery_form.fxml"));
        anchrpCustomer.getChildren().clear();
        anchrpCustomer.getChildren().add(load);

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

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
        assert btnPayment != null : "fx:id=\"btnPayment\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert buttonDashBoard != null : "fx:id=\"buttonDashBoard\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";

        DateAndTimeConntroller d1 = new DateAndTimeConntroller();
        d1.Timenow(lblTime, lblDate);

        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void clearTxtField() {

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
