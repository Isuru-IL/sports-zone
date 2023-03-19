package lk.ijse.sports_zone.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class InventoryFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane inventoryAnchorPane;

    @FXML
    void initialize() {
        assert inventoryAnchorPane != null : "fx:id=\"inventoryAnchorPane\" was not injected: check your FXML file 'inventory_form.fxml'.";

    }

}
