package oodj.assignment.oopskylinecarrentalsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("oodj/assignment/oopskylinecarrentalsystem/Login.fxml"));
//        ViewSwitcher.setScene(scene);
//        ViewSwitcher.switchTo(View.LOGIN);
        stage.setScene(new Scene(root, 960, 540));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}