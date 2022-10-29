package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switch000_008() throws IOException {
        viewSwitcher.switchTo(View.ADMIN_MAIN);
    }

    public void switch008_000() throws IOException {
        viewSwitcher.switchTo(View.LOGIN);
    }
}