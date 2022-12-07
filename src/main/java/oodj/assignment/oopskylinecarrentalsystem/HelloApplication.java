package oodj.assignment.oopskylinecarrentalsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ParseException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        App joey = new App();
        Transaction user = new Transaction();
        user.setCurrentUser(joey.login());
        user.payment();

//        joey.signUp();
//        Booking joey = new Booking();
////        joey.displayCarList();
////        joey.selectCar();
////        joey.bookingDetail();
//        joey.carSorting();
//        Booking joey = new Booking();
//        joey.carMenu();
//        joey.bookingDetails();
//        joey.bookingConfirmation();
//        joey.calculatePrice();

    }

    public static void main(String[] args) {
        launch();
    }
}