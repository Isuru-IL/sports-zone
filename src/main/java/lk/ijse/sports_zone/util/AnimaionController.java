package lk.ijse.sports_zone.util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class AnimaionController {

//    private static Timeline timeline;
//    private static IntegerProperty timeSeconds = new SimpleIntegerProperty();
//    private final static int START_TIME = 20;
//
//    public static void setCountDownLbl() {
//        //countdownLabel.setVisible(true);
//
//        Label countdownLabel;
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
//    }
}
