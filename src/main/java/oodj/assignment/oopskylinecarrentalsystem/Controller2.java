package oodj.assignment.oopskylinecarrentalsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class Controller2 implements Initializable {
    @FXML
    private Label label;
    @FXML
    private TextField txtfield;
    public static Label static_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            Stage primaryStage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("resources/oodj.assignment.oopskylinecarrentalsystem/testing.fxml"));
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.setResizable(false);
//            primaryStage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        static_label=label;

    }

    @FXML
    void button_action(ActionEvent event){
        static_label.setText(txtfield.getText());
    }

}
