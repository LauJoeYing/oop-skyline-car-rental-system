package oodj.assignment.oopskylinecarrentalsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(root, 960, 540));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.out.println("Welcome to OODJ Assignment!");
    }
}