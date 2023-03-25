package lk.ijse.sports_zone.util;

import javafx.scene.control.Alert;

import java.sql.SQLException;

public class NotificationController {
    public static void successful(String text) {
        new Alert(Alert.AlertType.CONFIRMATION,text).show();
    }

    public static void unSuccessful(String text) {
        new Alert(Alert.AlertType.ERROR,text).show();
    }

    public static void catchException(Exception exception) {
        new Alert(Alert.AlertType.ERROR, "exception_error").show();
        System.out.println(exception);
    }
}
