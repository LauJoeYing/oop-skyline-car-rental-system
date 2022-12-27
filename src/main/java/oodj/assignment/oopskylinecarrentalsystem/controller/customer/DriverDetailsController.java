package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;
import oodj.assignment.oopskylinecarrentalsystem.model.UnprocessedBooking;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.alertResultOk;
import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.setAlert;
import static oodj.assignment.oopskylinecarrentalsystem.util.ImageUtils.ImgAssign;
import static oodj.assignment.oopskylinecarrentalsystem.util.StringUtils.isAnyContainsBlank;

public class DriverDetailsController extends LabelledViewController implements Initializable {

    private UnprocessedBooking unprocessedBooking;

    private Alert alertInformation;
    private Alert alertConfirmation;
    @FXML
    private ImageView carImage;
    @FXML
    private Button backButton;
    @FXML
    private Button confirmBooking;
    @FXML
    private TextField customerAccountBalanceTextField;
    @FXML
    private TextField dailyRateTextField;
    @FXML
    private Text brandModelText;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private TextField driverLicenseURLTextField;
    @FXML
    private TextField driverNameTextField;
    @FXML
    private Button homeButton;
    @FXML
    private TextField transmissionTypeTextField;
    @FXML
    private TextField typeTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);
    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserSceneWithObject(event, FILEPATH.CUSTOMER.CAR_MENU, unprocessedBooking);
    }

    @FXML
    void onConfirmBookingClick(ActionEvent event) throws IOException {
        String driverLicenseURL = driverLicenseURLTextField.getText();
        LocalDate driverDateOfBirth = dateOfBirthDatePicker.getValue();
        if (!isAnyContainsBlank(
                driverNameTextField.getText(),
                driverLicenseURL
        ) && driverDateOfBirth != null && isDriverUnderAge(driverDateOfBirth)) {
            setAlert(
                    alertConfirmation,
                    "Confirm Booking",
                    "Are you sure to confirm this booking request?"
            );
            if (alertResultOk(alertConfirmation.showAndWait())) {
                unprocessedBooking.setLicenseURL(driverLicenseURL);
                switchLabelledUserSceneWithObject(event, FILEPATH.CUSTOMER.CHECKOUT, unprocessedBooking);
            }
        } else {
            setAlert(
                    alertInformation,
                    "Invalid Booking Request",
                    """
                            Please ensure your booking request:
                            1. All fields are not blank.
                            2. The driver's age is older than 17 y.o.
                            """
                    );
            alertInformation.show();
        }

    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    @Override
    public void setLabelData() {
        unprocessedBooking = (UnprocessedBooking) getObject();
        Car car = unprocessedBooking.getCar();
        Customer customer = (Customer) getUser();

        carImage.setImage(new Image(ImgAssign(car.getModel().split(" ")[0])));
        brandModelText.setText(String.join(" ", car.getBrand(), car.getModel()));
        dailyRateTextField.setText(String.format("RM %.2f", car.getDailyRate()));
        transmissionTypeTextField.setText(car.getTransmissionType());
        typeTextField.setText(car.getType());

        driverNameTextField.setText(customer.getName());
        customerAccountBalanceTextField.setText(String.format("RM %.2f", customer.getAccountBalance()));
    }

    private boolean isDriverUnderAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 17;
    }
}
