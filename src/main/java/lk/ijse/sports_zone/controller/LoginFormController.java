package lk.ijse.sports_zone.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LoginFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private ComboBox cmbbxLoggin;

    @FXML
    private Label forgotPasswordLbl1;

    @FXML
    private Label forgotPasswordLbl11;

    @FXML
    private Label forgotPasswordLbl12;

    @FXML
    private Label forgotPasswordLbl121;

    @FXML
    private ImageView google;

    @FXML
    private ImageView hyprlnkForgotPassword;

    @FXML
    private Label lblLogin;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private Rectangle loginRectt;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    String sourceRoot;

    @FXML
    void comboboxLoginOnAction(ActionEvent event) {
        if(cmbbxLoggin.getValue().equals("Admin")){
            sourceRoot = "/view/homePage_form.fxml";
        }else{
            sourceRoot = "/view/cashierCustomer_form.fxml";
        }

    }

    @FXML
    void goDashBoard(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(sourceRoot));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)loginAnchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();
    }

    @FXML
    void forgotPasswordOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/forgotPassword_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)loginAnchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Forgot Password Form");
        stage.centerOnScreen();
    }

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'login_form.fxml'.";
        assert cmbbxLoggin != null : "fx:id=\"cmbbxLoggin\" was not injected: check your FXML file 'login_form.fxml'.";
        assert forgotPasswordLbl1 != null : "fx:id=\"forgotPasswordLbl1\" was not injected: check your FXML file 'login_form.fxml'.";
        assert forgotPasswordLbl11 != null : "fx:id=\"forgotPasswordLbl11\" was not injected: check your FXML file 'login_form.fxml'.";
        assert forgotPasswordLbl12 != null : "fx:id=\"forgotPasswordLbl12\" was not injected: check your FXML file 'login_form.fxml'.";
        assert forgotPasswordLbl121 != null : "fx:id=\"forgotPasswordLbl121\" was not injected: check your FXML file 'login_form.fxml'.";
        assert google != null : "fx:id=\"google\" was not injected: check your FXML file 'login_form.fxml'.";
        assert hyprlnkForgotPassword != null : "fx:id=\"hyprlnkForgotPassword\" was not injected: check your FXML file 'login_form.fxml'.";
        assert lblLogin != null : "fx:id=\"lblLogin\" was not injected: check your FXML file 'login_form.fxml'.";
        assert loginAnchorPane != null : "fx:id=\"loginAnchorPane\" was not injected: check your FXML file 'login_form.fxml'.";
        assert loginRectt != null : "fx:id=\"loginRectt\" was not injected: check your FXML file 'login_form.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'login_form.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'login_form.fxml'.";

        cmbbxLoggin.getItems().addAll("Admin", "Cashier");
    }

}
