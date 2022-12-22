package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.util.BookingUtils;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.alertResultOk;
import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.setAlert;

public class CheckoutController extends LabelledViewController implements Initializable {

    private float bookingAmount;
    private UnprocessedBooking unprocessedBooking;
    private Alert alertInformation;
    private Alert alertConfirmation;
    @FXML
    private TextField bookingAmountTextField;

    @FXML
    private Button checkoutButton;

    @FXML
    private Button crossButton;

    @FXML
    private TextField dailyRateTextField;

    @FXML
    private TextField durationTextField;

    @FXML
    private TextField endDateTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField startDateTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setResizable(false);
    }

    @FXML
    void onCheckoutButtonClick(ActionEvent event) throws IOException {
        if (((Customer) getUser()).getAccountBalance() < bookingAmount) {
            setAlert(
                    alertConfirmation,
                    "Insufficient Account Balance",
                    """
                            You have insufficient account balance to make the booking request.
                            Do you want to top-up your account?
                            """
            );
            if (alertResultOk(alertConfirmation.showAndWait())) {
                switchLabelledUserSceneWithObject(event, FILEPATH.CUSTOMER.TOP_UP, unprocessedBooking);
            }
        } else {
            setAlert(
                    alertConfirmation,
                    "Confirm Booking",
                    "Are you sure to make this booking request?"
            );

            if (alertResultOk(alertConfirmation.showAndWait())) {
                BookingUtils.addBooking(new Booking(
                        getUser().getUsername(),
                        unprocessedBooking.getCar().getId(),
                        unprocessedBooking.getBookingDateRange(),
                        unprocessedBooking.getLicenseURL()
                ));
                BookingUtils.updateFile();
                setAlert(
                        alertInformation,
                        "Booking Pending Acceptance",
                        "You've submitted the booking request successfully, our admin will respond to your request within 3 days. Please check your mailbox."
                );
                Optional<ButtonType> resultInformation = alertInformation.showAndWait();
                if (resultInformation.isEmpty() || resultInformation.get() == ButtonType.OK) {
                    switchLabelledUserScene(event, FILEPATH.USER_MAIN);
                }
            }
        }
    }

    @FXML
    void onCrossButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserSceneWithObject(event, FILEPATH.CUSTOMER.DRIVER_DETAILS, unprocessedBooking);
    }

    @Override
    public void setLabelData() {
        unprocessedBooking = (UnprocessedBooking) getObject();
        Car car = unprocessedBooking.getCar();
        DateRange dateRange = unprocessedBooking.getBookingDateRange();
        idTextField.setText(car.getId());
        modelTextField.setText(String.join(" ", car.getBrand(), car.getModel()));
        startDateTextField.setText(dateRange.getStartDate().toString());
        endDateTextField.setText(dateRange.getEndDate().toString());
        durationTextField.setText(String.valueOf(dateRange.getDuration()));
        dailyRateTextField.setText(String.format("RM %.2f", car.getDailyRate()));
        bookingAmount = car.getDailyRate() * dateRange.getDuration();
        bookingAmountTextField.setText(String.format("RM %.2f", bookingAmount));
    }



}
