package lk.ijse.sports_zone.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class CashierDeliveryFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpDelivery;

    @FXML
    void initialize() {
        assert anchrpDelivery != null : "fx:id=\"anchrpDelivery\" was not injected: check your FXML file 'cashierDelivery_form.fxml'.";

    }

}
