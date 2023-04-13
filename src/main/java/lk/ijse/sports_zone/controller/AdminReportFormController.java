package lk.ijse.sports_zone.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AdminReportFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchrpReport;

    @FXML
    void initialize() {
        assert anchrpReport != null : "fx:id=\"anchrpReport\" was not injected: check your FXML file 'adminReport_form.fxml'.";

    }

}
