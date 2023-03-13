package lk.ijse.sports_zone.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartForm {
    public AnchorPane acrLoading;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ProgressBar loading;

     Stage stage;

    public void initialize(){
        new ShowSplashScreen().start();
    }

    class ShowSplashScreen extends Thread {
        public void run() {
            try {
                for (int i = 0; i <= 10; i++) {
                    double x = i * (0.1);
                    loading.setProgress(x);


                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/lk.ijse.sports_zone/view/login_form.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(StartForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    acrLoading.getScene().getWindow().hide();
                });
            } catch (Exception ex) {
                Logger.getLogger(StartForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
