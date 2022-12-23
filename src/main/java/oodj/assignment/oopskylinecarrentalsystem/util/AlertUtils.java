package oodj.assignment.oopskylinecarrentalsystem.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertUtils {                  //Utility Function for Alert Popup
    public static void setAlert(Alert alert, String title, String contentText) {
        alert.setTitle(title);
        alert.setContentText(contentText);
    }

    public static boolean alertResultEmptyOrOk(Optional<ButtonType> result) {
        return result.isEmpty() || result.get() == ButtonType.OK;
    }

    public static boolean alertResultOk(Optional<ButtonType> result) {
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
