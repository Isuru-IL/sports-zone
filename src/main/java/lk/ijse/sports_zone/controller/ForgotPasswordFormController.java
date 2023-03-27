package lk.ijse.sports_zone.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.sports_zone.dto.User;
import lk.ijse.sports_zone.model.UserModel;
import lk.ijse.sports_zone.util.AlertController;
import lk.ijse.sports_zone.util.AnimaionController;
import lk.ijse.sports_zone.util.EmailController;
import lk.ijse.sports_zone.util.ValidateEmail;

import javax.mail.MessagingException;

public class ForgotPasswordFormController {

    @FXML
    private AnchorPane anchorpForgotPassword;

    @FXML
    private Button btnBackToLogin;

    @FXML
    private Button btnResend;

    @FXML
    private Button btnSendOtp;

    @FXML
    private Button btnSubmitOtp;

    @FXML
    private Button btnSubmitPassword;

    @FXML
    private Label countdownLabel;

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
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEnterUserName;

    @FXML
    private TextField txtEnterEmail;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private TextField txtOtp;

    int randomnum;
    Random rand = new Random();


    private Timeline timeline;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty();
    private final int START_TIME = 20;
    private String email;
    private String searchEmail;
    private String userName;

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
            btnSubmitOtp.setDisable(true);
            lblOTP.setDisable(true);
            btnResend.setDisable(true);

            newPasswordGroup.setVisible(true);
        }else{
            AlertController.errormessage("Wrong OTP");
        }


    }

    @FXML
    void submitEmailOnAction(ActionEvent event) throws MessagingException {
        userName = txtEnterUserName.getText();

        try {
            User user = UserModel.searchByUsername(userName);
            searchEmail = user.getEmail();
            System.out.println(searchEmail);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//
//        ///////////////////////////////////////////////////////////////
//        timeSeconds.set(START_TIME);
//        timeline = new Timeline();
//        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME+1),
//                new KeyValue(timeSeconds, 0)));
//        timeline.setOnFinished(event2 -> {
//            countdownLabel.setVisible(false);
//            btnResend.setVisible(true);
//            // handle timeout event
//        });
//        timeline.playFromStart();
//        countdownLabel.textProperty().bind(timeSeconds.asString());

        /////////////////////////////////////////////////////////////////

        if(ValidateEmail.emailCheck(txtEnterEmail.getText())) {
            if(txtEnterEmail.getText().equals(searchEmail)) {
                randomnum = rand.nextInt(9000);
                randomnum += 1000;

                email = txtEnterEmail.getText();
                //setCountDownLbl();
                try {
                    EmailController.sendEmail(email, "Test Email", randomnum + "");
                    System.out.println("Email sent successfully.");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                emailGroup.setVisible(false);
                otpGroup.setVisible(true);

                setCountDownLbl();

            }else{
                AlertController.errormessage("Invalid Email");
            }
        }else{
            AlertController.errormessage("Invalid Email format");
        }
    }



    @FXML
    void submitPasswordOnAction(ActionEvent event) {
        User user = new User();

        if(txtNewPassword.getText().equals(txtConfirmPassword.getText())) {

            user.setUserName(userName);
            user.setPassword(txtConfirmPassword.getText());
            try {
                boolean isUpdate = UserModel.updatePassword(user);

                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION, "Password change successfull").show();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Password change Unsuccessfull").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "password not same").show();
        }
    }

    @FXML
    void resendOtpOnAction(ActionEvent event) {
        countdownLabel.setVisible(true);
        txtOtp.setText("");

        if(ValidateEmail.emailCheck(txtEnterEmail.getText())) {
            randomnum = rand.nextInt(9000);
            randomnum += 1000;

            try {
                EmailController.sendEmail(email, "Test Email", randomnum + "");
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        setCountDownLbl();
        //////////////////////////////////////////////////////
//        timeSeconds.set(START_TIME);
//        timeline = new Timeline();
//        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME+1),
//                new KeyValue(timeSeconds, 0)));
//        timeline.setOnFinished(event2 -> {
//            countdownLabel.setVisible(false);
//            //btnResend.setVisible(true);
//            // handle timeout event
//        });
//        timeline.playFromStart();
//        countdownLabel.textProperty().bind(timeSeconds.asString());
    }



    @FXML
    void initialize() {
        assert anchorpForgotPassword != null : "fx:id=\"anchorpForgotPassword\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert btnBackToLogin != null : "fx:id=\"btnBackToLogin\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert btnSendOtp != null : "fx:id=\"btnSendOtp\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert btnSubmitPassword != null : "fx:id=\"btnSubmitPassword\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert countdownLabel != null : "fx:id=\"countdownLabel\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert emailGroup != null : "fx:id=\"emailGroup\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert lblOTP != null : "fx:id=\"lblOTP\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert loginRectt != null : "fx:id=\"loginRectt\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert newPasswordGroup != null : "fx:id=\"newPasswordGroup\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert otpGroup != null : "fx:id=\"otpGroup\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert txtConfirmPassword != null : "fx:id=\"txtConfirmPassword\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert txtEnterEmail != null : "fx:id=\"txtEnterEmail\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert txtNewPassword != null : "fx:id=\"txtNewPassword\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";
        assert txtOtp != null : "fx:id=\"txtOtp\" was not injected: check your FXML file 'forgotPassword_form.fxml'.";

        emailGroup.setVisible(true);
        newPasswordGroup.setVisible(false);
        otpGroup.setVisible(false);
        btnResend.setVisible(false);
        countdownLabel.setVisible(false);
    }

    private void setCountDownLbl() {

        countdownLabel.setVisible(true);

        timeSeconds.set(START_TIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME+1),
                new KeyValue(timeSeconds, 0)));
        timeline.setOnFinished(event2 -> {
            countdownLabel.setVisible(false);
            btnResend.setVisible(true);
            // handle timeout event
        });
        timeline.playFromStart();
        countdownLabel.textProperty().bind(timeSeconds.asString());

    }

}
