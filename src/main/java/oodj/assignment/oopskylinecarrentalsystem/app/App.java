package oodj.assignment.oopskylinecarrentalsystem.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oodj.assignment.oopskylinecarrentalsystem.config.CustomerConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.MailConfig;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/oodj/assignment/oopskylinecarrentalsystem/view/shared/Login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Skyline Car Rental");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}