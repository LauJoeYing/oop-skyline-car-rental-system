package oodj.assignment.oopskylinecarrentalsystem.config;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertConfig {
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
