package oodj.assignment.oopskylinecarrentalsystem.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.util.BookingUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.CarUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.CustomerUtils;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.setAlert;

public class PendingApprovalController extends LabelledViewController implements Initializable {

    private Booking booking;
    @FXML
    private Button approveButton;

    private Alert alertConfirmation;
    private Alert alertInformation;
    @FXML
    private TextField bookingAmountTextField;

    @FXML
    private TextField carIdTextField;

    @FXML
    private TextField carModelTextField;

    @FXML
    private Button crossButton;

    @FXML
    private TextField dailyRateTextField;

    @FXML
    private TextField durationTextField;

    @FXML
    private TextField endDateTextField;

    @FXML
    private TextField icNumberTextField;
    @FXML
    private Text title;
    @FXML
    private Button rejectButton;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button viewDriverLicenseButton;

    @FXML
    void onApproveButtonClick(ActionEvent event) throws IOException {
        updateBookingStatus(event, true);
    }

    @FXML
    void onCrossButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.ADMIN.MANAGE_BOOKING);
    }

    @FXML
    void onRejectButtonClick(ActionEvent event) throws IOException {
        updateBookingStatus(event, false);
    }

    @Override
    public void setLabelData() {
        booking = (Booking) getObject();
        usernameTextField.setText(booking.getCustomerUsername());
        icNumberTextField.setText(Objects.requireNonNull(CustomerUtils.getCustomerFromUsername(usernameTextField.getText())).getIcNumber());
        startDateTextField.setText(booking.getBookingDateRange().getStartDate().toString());
        endDateTextField.setText(booking.getBookingDateRange().getEndDate().toString());
        durationTextField.setText(String.format("%d day(s)", booking.getBookingDateRange().getDuration()));
        Car car = Objects.requireNonNull(CarUtils.getCarFromId(booking.getCarId()));
        carIdTextField.setText(booking.getCarId());
        carModelTextField.setText(car.getModel());
        dailyRateTextField.setText(String.format("RM %.2f", car.getDailyRate()));
        bookingAmountTextField.setText(String.format("RM %.2f", booking.getBookingAmount()));
        if (booking.getStatus().equals("Pending")) {
            rejectButton.setVisible(true);
            approveButton.setVisible(true);
            title.setText("Pending Approval");
        } else {
            rejectButton.setVisible(false);
            approveButton.setVisible(false);
            title.setText("Booking Details");
        }
    }

    @FXML
    private void onViewDriverLicenseButtonClick() {
        try {
            Desktop.getDesktop().browse(new URI(booking.getLicenseURL()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void updateBookingStatus(ActionEvent event, boolean isAccepted) throws IOException {
        setAlert(
                alertConfirmation,
                String.format("%s Booking", isAccepted ? "Accept" : "Reject"),
                String.format("Are you sure to %s this booking?", isAccepted ? "accept" : "reject")
        );

        Optional<ButtonType> resultConfirmation = alertConfirmation.showAndWait();

        if (resultConfirmation.isPresent() && resultConfirmation.get() == ButtonType.OK) {
            if (isAccepted) {
                booking.setStatus("Accepted");
            } else {
                booking.setStatus("Rejected");
            }

            BookingUtils.updateBooking(booking);
            BookingUtils.updateFile();

            setAlert(
                    alertInformation,
                    String.format("Complete Booking %s", isAccepted ? "Acceptance" : "Rejection"),
                    String.format("You've %s the booking successfully.", isAccepted ? "accepted" : "rejected")
            );

            Optional<ButtonType> resultInformation = alertInformation.showAndWait();
            if (resultInformation.isEmpty() || resultInformation.get() == ButtonType.OK) {
                switchUserScene(event, FILEPATH.ADMIN.MANAGE_BOOKING);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);
        alertInformation.setResizable(false);
    }
}
