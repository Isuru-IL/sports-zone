package lk.ijse.sports_zone.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class OrderFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ordersAnchorPane;

    @FXML
    void initialize() {
        assert ordersAnchorPane != null : "fx:id=\"ordersAnchorPane\" was not injected: check your FXML file 'order_form.fxml'.";

    }

}
