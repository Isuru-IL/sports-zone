package lk.ijse.sports_zone.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.ijse.sports_zone.dto.User;
import lk.ijse.sports_zone.model.UserModel;

public class SignUpFormController {

    @FXML
    private AnchorPane anchorpSignUp;

    @FXML
    private Button btnLogin;

    @FXML
    private ComboBox<String> cmbSignUp;

    @FXML
    private Hyperlink hyprlnkSignIn;

    @FXML
    private Rectangle loginRectt;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtUserName;

    User user = new User();

    @FXML
    void cmbSignUpOnAction(ActionEvent event) {
        user.setJobTitle(cmbSignUp.getValue());
    }

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {

        if(txtPassword.getText().equals(txtConfirmPassword.getText())){
            user.setUserName(txtUserName.getText());
            user.setEmpId(txtEmployeeId.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(txtPassword.getText());

            try {
                Boolean isSaved = UserModel.save(user);
                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "User saved").show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "somthing went wrong").show();
            }

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage)anchorpSignUp.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.centerOnScreen();

        }else{
            new Alert(Alert.AlertType.ERROR,"password not same").show();
        }
    }

    @FXML
    void alreadySignInOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) anchorpSignUp.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();

    }

    @FXML
    void initialize() {
        assert anchorpSignUp != null : "fx:id=\"anchorpSignUp\" was not injected: check your FXML file 'signUp_form.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'signUp_form.fxml'.";
        assert hyprlnkSignIn != null : "fx:id=\"hyprlnkSignIn\" was not injected: check your FXML file 'signUp_form.fxml'.";
        assert loginRectt != null : "fx:id=\"loginRectt\" was not injected: check your FXML file 'signUp_form.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'signUp_form.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'signUp_form.fxml'.";
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'signUp_form.fxml'.";

        cmbSignUp.getItems().addAll("Admin", "Cashier");
    }

}
