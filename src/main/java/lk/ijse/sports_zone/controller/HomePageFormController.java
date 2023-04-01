package lk.ijse.sports_zone.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.sports_zone.util.ButtonColourController;
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

        //btnColour(btnHome,anchorpaneAdminHome);
        btnHomecolor(btnHome,anchorpaneAdminHome);
    }

    @FXML
    void ordersOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        anchorpaneAdminHome.getChildren().clear();
        anchorpaneAdminHome.getChildren().add(load);
                ButtonColourController.btncolor(btnOrders, anchorpaneAdminHome);
        btnHome.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }

    @FXML
    void inventoryOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/inventory_form.fxml"));
        anchorpaneAdminHome.getChildren().clear();
        anchorpaneAdminHome.getChildren().add(load);

        ButtonColourController.btncolor(btnInventory,anchorpaneAdminHome);

        btnHome.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }


    @FXML
    void reportsOnAction(ActionEvent event) {

        ButtonColourController.btncolor(btnReports,anchorpaneAdminHome);
        btnHome.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }

    @FXML
    void suplierOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminSupplier_form.fxml"));
        anchorpaneAdminHome.getChildren().clear();
        anchorpaneAdminHome.getChildren().add(load);

        ButtonColourController.btncolor(btnSupplier,anchorpaneAdminHome);
        btnHome.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/adminEmployee_form.fxml"));
        anchorpaneAdminHome.getChildren().clear();
        anchorpaneAdminHome.getChildren().add(load);

        ButtonColourController.btncolor(btnEmployee,anchorpaneAdminHome);
        btnHome.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                "-fx-background-radius: 20px;");
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

        btnHome.setStyle("-fx-background-color: #0c0c0c;" +
                "-fx-background-radius: 20px;");
    }

    public static void btnHomecolor(Button btn, AnchorPane anchorPane){
        btn.setStyle("-fx-background-color: #de1818;" +
                "-fx-background-radius: 20px;");
        anchorPane.getChildren().addListener((ListChangeListener<Node>) change -> {
            //System.out.println(newAnchorPane.getId());
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Node node : change.getAddedSubList()) {
                        if (node instanceof AnchorPane) {
                            // Check if the new node is an AnchorPane
                            AnchorPane newAnchorPane = (AnchorPane) node;
                            System.out.println(newAnchorPane.getId());
                            if (newAnchorPane.getId().equals("anchorpaneAdminHome")) {
                                btn.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                                        "-fx-background-radius: 20px;");
                                System.out.println("if");
                            } else {
                                btn.setStyle("-fx-background-color: #033b66;");
                                System.out.println("else");
                            }
                        }
                    }
                }
            }
        });
    }
}
