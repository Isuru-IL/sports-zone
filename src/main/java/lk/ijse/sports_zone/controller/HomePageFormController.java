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
import lk.ijse.sports_zone.util.DateAndTimeConntroller;

public class HomePageFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpaneAdminHome;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnInventory;

    @FXML
    private ImageView btnLogoutIcon;

    @FXML
    private JFXButton btnOrders;

    @FXML
    private JFXButton btnReports;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private ImageView buttonDashBoard;

    @FXML
    private AnchorPane anchorpaneHomeMain;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;


    @FXML
    void homeOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/homePage_form.fxml"));
        anchorpaneHomeMain.getChildren().clear();
        anchorpaneHomeMain.getChildren().add(load);
        //btnHome.setVisible(false);
    }

    @FXML
    void ordersOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        anchorpaneAdminHome.getChildren().clear();
        anchorpaneAdminHome.getChildren().add(load);

    }

    @FXML
    void inventoryOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/inventory_form.fxml"));
        anchorpaneAdminHome.getChildren().clear();
        anchorpaneAdminHome.getChildren().add(load);
    }


    @FXML
    void reportsOnAction(ActionEvent event) {

    }

    @FXML
    void suplierOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminSupplier_form.fxml"));
        anchorpaneAdminHome.getChildren().clear();
        anchorpaneAdminHome.getChildren().add(load);
    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminEmployee_form.fxml"));
        anchorpaneAdminHome.getChildren().clear();
        anchorpaneAdminHome.getChildren().add(load);
    }

    @FXML
    void logOutOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)anchorpaneHomeMain.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert anchorpaneAdminHome != null : "fx:id=\"anchorpaneAdminHome\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert anchorpaneHomeMain != null : "fx:id=\"anchorpaneHomeMain\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert btnEmployee != null : "fx:id=\"btnEmployee\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert btnInventory != null : "fx:id=\"btnInventory\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert btnLogoutIcon != null : "fx:id=\"btnLogoutIcon\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert btnOrders != null : "fx:id=\"btnOrders\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert btnReports != null : "fx:id=\"btnReports\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert btnSupplier != null : "fx:id=\"btnSupplier\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert buttonDashBoard != null : "fx:id=\"buttonDashBoard\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'homePage_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'homePage_form.fxml'.";

        DateAndTimeConntroller d1 = new DateAndTimeConntroller();
        d1.Timenow(lblTime, lblDate);
    }

}
