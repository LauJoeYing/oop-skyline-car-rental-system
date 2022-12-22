package oodj.assignment.oopskylinecarrentalsystem.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oodj.assignment.oopskylinecarrentalsystem.config.AdminConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.BookingConfig;
import oodj.assignment.oopskylinecarrentalsystem.controller.admin.AdminMainController;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import oodj.assignment.oopskylinecarrentalsystem.model.DateRange;

import java.io.IOException;

// This is the main class of a JavaFX application.
// The class also defines a main() method, which is the main entry point for the application.
// OOP Concept Implemented: Inheritance
public class App extends Application {

    // This method initializes and displays a JavaFX application.
    // It loads an FXML file that defines the layout of the application's user interface,
    // sets the layout as the scene of a JavaFX Stage, and sets the user and label data for the application's controller.
    // Finally, it sets the title of the Stage and shows it.
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/oodj/assignment/oopskylinecarrentalsystem/view/admin/Main.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        AdminMainController controller = fxmlLoader.getController();
        controller.setUser(AdminConfig.getAdminList().get(0));
        controller.setLabelData();
        primaryStage.setTitle("Skyline Car Rental");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // This method is the main entry point for a JavaFX application.
    // It calls the launch() method of the Application class, which launches the JavaFX application
    public static void main(String[] args) {
        launch();
    }

}