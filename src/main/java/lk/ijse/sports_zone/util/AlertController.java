package lk.ijse.sports_zone.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertController {
    public static void errormessage(String msg){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
            alert.getDialogPane().setMinSize(400, 150); // Set the minimum size of the alert dialog pane
            alert.getDialogPane().setMaxSize(300, 150); // Set the maximum size of the alert dialog pane
        //alert.getDialogPane().setStyle("-fx-background-color: #F8D7DA;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/assets/wrongIcon.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static void successfulMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150);
        alert.getDialogPane().setMinSize(400, 150);
        alert.getDialogPane().setMaxSize(300, 150);
        alert.getDialogPane().setHeaderText(null);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/assets/wdoneIcon.png"));
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        alert.show();
    }

    public static void exceptionMessage(String msg) {
        Alert alert= new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exception");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150);
        alert.getDialogPane().setMinSize(400, 150);
        alert.getDialogPane().setMaxSize(300, 150);
        alert.getDialogPane().setHeaderText(null);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/assets/warningIcon.png"));
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton);

        alert.show();
    }
}