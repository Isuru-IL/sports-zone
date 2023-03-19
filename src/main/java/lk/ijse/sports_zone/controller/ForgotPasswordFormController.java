package lk.ijse.sports_zone.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.mail.MessagingException;

public class ForgotPasswordFormController {

    @FXML
    private AnchorPane anchorpForgotPassword;

    @FXML
    private Button btnBackToLogin;

    @FXML
    private Button btnEmail;

    @FXML
    private Button btnSendOtp;

    @FXML
    private Button btnSubmitPassword;

    @FXML
    private Group emailGroup;

    @FXML
    private Label lblOTP;

    @FXML
    private Rectangle loginRectt;

    @FXML
    private Group newPasswordGroup;

    @FXML
    private Group otpGroup;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEnterEmail;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtOtp;

    int randomnum;
    Random rand = new Random();

    @FXML
    void backToLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StartFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        anchorpForgotPassword.getScene().getWindow().hide();
    }

    @FXML
    void sendOtpOnAction(ActionEvent event) {
        if(txtOtp.getText().equals(Integer. toString(randomnum))) {
            txtOtp.setDisable(true);
            btnSendOtp.setDisable(true);
            lblOTP.setDisable(true);

            newPasswordGroup.setVisible(true);
        }else{
            AlertController.errormessage("Wrong OTP");
        }
    }

    @FXML
    void submitEmailOnAction(ActionEvent event) throws MessagingException {
        if(ValidateEmail.emailCheck(txtEnterEmail.getText())) {
            randomnum = rand.nextInt(9000);
            randomnum += 1000;

            String email = txtEnterEmail.getText();

            try {
                EmailController.sendEmail(email, "Test Email", randomnum + "");
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            emailGroup.setVisible(false);
            otpGroup.setVisible(true);
        }else{
            AlertController.errormessage("Invalid Email");
        }
    }


    @FXML
    void submitPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnBackToLogin != null : "fx:id=\"btnBackToLogin\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert btnEmail != null : "fx:id=\"btnEmail\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert btnSendOtp != null : "fx:id=\"btnSendOtp\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert btnSubmitPassword != null : "fx:id=\"btnSubmitPassword\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert emailGroup != null : "fx:id=\"emailGroup\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert newPasswordGroup != null : "fx:id=\"newPasswordGroup\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert otpGroup != null : "fx:id=\"otpGroup\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert txtConfirmPassword != null : "fx:id=\"txtConfirmPassword\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert txtEnterEmail != null : "fx:id=\"txtEnterEmail\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert txtNewPassword != null : "fx:id=\"txtNewPassword\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert txtOtp != null : "fx:id=\"txtOtp\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";

        emailGroup.setVisible(true);
        newPasswordGroup.setVisible(false);
        otpGroup.setVisible(false);
    }

}
