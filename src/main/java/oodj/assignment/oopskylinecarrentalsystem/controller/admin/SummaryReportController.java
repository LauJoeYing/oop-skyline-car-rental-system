package oodj.assignment.oopskylinecarrentalsystem.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.CommonViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;
import oodj.assignment.oopskylinecarrentalsystem.util.BookingUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.CarUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.CustomerUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SummaryReportController extends CommonViewController implements Initializable {

    @FXML
    private TextField acceptedBookingNumberTextField;
    @FXML
    private TextField bookingNumberTextField;
    @FXML
    private TextField customerNumberTextField;
    @FXML
    private TextField hatchBackNumberTextField;
    @FXML
    private Button homeButton;
    @FXML
    private TextField pendingBookingNumberTextField;
    @FXML
    private TextField rejectedBookingNumberTextField;
    @FXML
    private TextField revenueTotalTextField;
    @FXML
    private TextField sedanNumberTextField;
    @FXML
    private TextField suvNumberTextField;
    @FXML
    private TextField totalCarTextField;
    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Car> carList = CarUtils.getCarList();
        int suvCount = 0;
        int sedanCount = 0;
        int hatchbackCount = 0;
        totalCarTextField.setText(String.valueOf(carList.size()));
        for (Car car: carList) {
            String carType = car.getType();
            switch (carType) {
                case "SUV" -> suvCount += 1;
                case "Sedan" -> sedanCount += 1;
                case "Hatchback" -> hatchbackCount += 1;
            }
        }
        suvNumberTextField.setText(String.valueOf(suvCount));
        sedanNumberTextField.setText(String.valueOf(sedanCount));
        hatchBackNumberTextField.setText(String.valueOf(hatchbackCount));

        List<Booking> bookingList = BookingUtils.getBookingList();
        bookingNumberTextField.setText(String.valueOf(bookingList.size()));
        float totalRevenue = 0.0F;
        int acceptedCount = 0;
        int pendingCount = 0;
        int rejectedCount = 0;
        for (Booking booking: bookingList) {
            String bookingStatus = booking.getStatus();
            switch (bookingStatus) {
                case "Accepted" -> {
                    totalRevenue += booking.getBookingAmount();
                    acceptedCount += 1;
                }
                case "Pending" -> {
                    totalRevenue += booking.getBookingAmount();
                    pendingCount += 1;
                }
                case "Rejected" -> {
                    rejectedCount += 1;
                }
            }
        }
        revenueTotalTextField.setText(String.format("RM %.2f", totalRevenue));
        acceptedBookingNumberTextField.setText(String.valueOf(acceptedCount));
        pendingBookingNumberTextField.setText(String.valueOf(pendingCount));
        rejectedBookingNumberTextField.setText(String.valueOf(rejectedCount));

        customerNumberTextField.setText(String.valueOf(CustomerUtils.getCustomerList().size()));

    }
}
