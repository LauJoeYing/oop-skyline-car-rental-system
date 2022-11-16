package oodj.assignment.oopskylinecarrentalsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//        App joey = new App();
//        joey.login();
//        joey.signUp();
        Booking joey = new Booking();
        joey.displayCar();
        joey.selectCar();
        joey.bookingDetail();

    }

    public static void main(String[] args) {
        launch();
    }
}