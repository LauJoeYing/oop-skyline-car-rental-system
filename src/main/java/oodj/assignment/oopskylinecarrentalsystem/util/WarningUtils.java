package oodj.assignment.oopskylinecarrentalsystem.util;

import javafx.scene.control.Label;

public class WarningUtils {                 //Utility Function for Warning popup
    public static void resetLabel(Label... labels) {
        for (Label label:labels) {
            label.setText("");
        }
    }
}