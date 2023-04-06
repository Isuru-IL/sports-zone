package lk.ijse.sports_zone.util;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ButtonColourController {
    public static void btncolor(Button btn, AnchorPane anchorPane){
        btn.setStyle("-fx-background-color: #440000;" +
                "-fx-background-radius: 20px;");
        anchorPane.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Node node : change.getAddedSubList()) {
                        if (node instanceof AnchorPane) {
                            // Check if the new node is an AnchorPane
                            AnchorPane newAnchorPane = (AnchorPane) node;
                            //System.out.println(newAnchorPane.getId());
                            if (newAnchorPane.getId().equals("anchorpaneHomeMain")) {
                                btn.setStyle("-fx-background-color: #3C4043;");
                            } else {
                                btn.setStyle("-fx-background-color: linear-gradient(to top right  ,#000000 ,#808080);" +
                                        "-fx-background-radius: 20px;");
                            }
                        }
                    }
                }
            }
        });
    }
}
