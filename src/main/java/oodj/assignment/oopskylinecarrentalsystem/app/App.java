package oodj.assignment.oopskylinecarrentalsystem.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oodj.assignment.oopskylinecarrentalsystem.config.AdminConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.BookingConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.CustomerConfig;
import oodj.assignment.oopskylinecarrentalsystem.controller.admin.AdminMainController;
import oodj.assignment.oopskylinecarrentalsystem.controller.customer.CustomerMainController;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import oodj.assignment.oopskylinecarrentalsystem.model.DateRange;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/oodj/assignment/oopskylinecarrentalsystem/view/customer/Main.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        CustomerMainController controller = fxmlLoader.getController();
        controller.setUser(CustomerConfig.getCustomerList().get(0));
        controller.setLabelData();
        primaryStage.setTitle("Skyline Car Rental");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}