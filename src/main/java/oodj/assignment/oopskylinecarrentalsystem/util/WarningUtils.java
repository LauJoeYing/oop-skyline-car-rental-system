package oodj.assignment.oopskylinecarrentalsystem.util;

import javafx.scene.control.Label;

public class WarningUtils {
    public static void resetLabel(Label... labels) {
        for (Label label:labels) {
            label.setText("");
        }
    }
}