package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CashierCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpaneAdminHome;

    @FXML
    private AnchorPane anchorpaneCashierCustomer;

    @FXML
    private JFXButton btnCashierInventory;

    @FXML
    private ImageView btnCashierLogout;

    @FXML
    private JFXButton btnCashierOrders;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnDelivery;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private ImageView buttonDashBoard;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    void btnCashierInventoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnCashierOrdersOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeliveryOnAction(ActionEvent event) {

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void logOutIconOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)anchorpaneCashierCustomer.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert anchorpaneAdminHome != null : "fx:id=\"anchorpaneAdminHome\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
        assert anchorpaneCashierCustomer != null : "fx:id=\"anchorpaneCashierCustomer\" was not injected: check your FXML file 'cashierCustomer_form.fxml'.";
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
    }

}
