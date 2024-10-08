package lk.ijse.sports_zone.controller;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.ijse.sports_zone.dto.User;
import lk.ijse.sports_zone.model.UserModel;
import lk.ijse.sports_zone.util.AlertController;

public class LoginFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private ComboBox<String> cmbbxLoggin;

    @FXML
    private Group demoPasswordGroup;

    @FXML
    private Group originalPasswordGroup;

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
    private ImageView imageHideIcon;

    @FXML
    private ImageView imageUnHideIcon;

    @FXML
    private Hyperlink hyprlnkForgotPassword;

    @FXML
    private Hyperlink hyprlnkSignUp;

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

    @FXML
    private TextField txtPasswordDemo;

    String sourceRoot;
    User userCheckLogin = new User();

    @FXML
    void comboboxLoginOnAction(ActionEvent event) {
       userCheckLogin.setJobTitle(cmbbxLoggin.getValue());

    }

    @FXML
    void goDashBoard(ActionEvent event) throws IOException {

        if(cmbbxLoggin.getSelectionModel().isEmpty() || txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
            if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
                AlertController.errormessage("Please enter login details");
            }else {
                AlertController.errormessage("Please select Job Title");
            }
        }else{
            try {
                userCheckLogin.setUserName(txtUsername.getText());
                userCheckLogin.setPassword(txtPassword.getText());

                User user = UserModel.checkLoginAccess(userCheckLogin);
                String userName = user.getUserName();
                String password = user.getPassword();
                String jobTitle = user.getJobTitle();

                if(user == null){
                    AlertController.errormessage("User name not found");
                }

                if(userName.equals(userCheckLogin.getUserName()) && password.equals(userCheckLogin.getPassword()) && jobTitle.equals(userCheckLogin.getJobTitle()) && jobTitle.equals("Admin")){
                    AlertController.animationMesseage("assets/wdoneIcon.png", "Login", "login successful");
                    AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/homePage_form.fxml"));

                    Scene scene = new Scene(anchorPane);

                    Stage stage = (Stage)loginAnchorPane.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Admin Page");
                    stage.centerOnScreen();

                }else if(userName.equals(userCheckLogin.getUserName()) && password.equals(userCheckLogin.getPassword()) && jobTitle.equals(userCheckLogin.getJobTitle()) && jobTitle.equals("Cashier")){
                    AlertController.animationMesseage("assets/wdoneIcon.png", "Login", "login successful");
                    AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/cashierCustomer_form.fxml"));

                    Scene scene = new Scene(anchorPane);

                    Stage stage = (Stage)loginAnchorPane.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Cashier Page");
                    stage.centerOnScreen();

                }else{
                    AlertController.errormessage("Invalid login details");
                }

            } catch (Exception exception) {
                //exception.printStackTrace();
                System.out.println(exception);
                //new Alert(Alert.AlertType.ERROR,"something went wrong").show();
                AlertController.errormessage("Login unsuccessful");
            }
        }

    }

    @FXML
    void forgotPasswordOnAction(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/forgotPassword_form.fxml"));
//
//        Scene scene = new Scene(anchorPane);
//
//        Stage stage = (Stage)loginAnchorPane.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Forgot Password Form");
//        stage.centerOnScreen();

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/forgotPassword_form.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StartFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Forgot Password Form");
        stage.show();
        loginAnchorPane.getScene().getWindow().hide();
    }

    @FXML
    void signUpOnAction(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signUp_form.fxml"));
//
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage)loginAnchorPane.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Login Form");
//        stage.centerOnScreen();

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/signUp_form.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StartFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.show();
        loginAnchorPane.getScene().getWindow().hide();
    }

    @FXML
    void passwordShowOnMouseEntered(MouseEvent event) {
        demoPasswordGroup.setVisible(true);
        txtPasswordDemo.setText(txtPassword.getText());
        originalPasswordGroup.setVisible(false);

    }

    @FXML
    void passwordHideOnMouseExited(MouseEvent event) {
        demoPasswordGroup.setVisible(false);
        originalPasswordGroup.setVisible(true);
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

    public void goToGoogleOnMousePressed(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.sportszone.lk/"));
    }

    public void goToFacebookOnMousePressed(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjj6fjShOb9AhX9XmwGHc_XAIEQPAgI"));
    }
}
