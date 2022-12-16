package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import oodj.assignment.oopskylinecarrentalsystem.config.CustomerConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.UserConfig;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;
import oodj.assignment.oopskylinecarrentalsystem.model.UnprocessedBooking;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.config.AlertConfig.*;
import static oodj.assignment.oopskylinecarrentalsystem.config.CarConfig.isValidDailyRate;

public class TopUpController extends LabelledViewController implements Initializable {


    private Customer customer;
    private UnprocessedBooking unprocessedBooking;
    private Alert alertInformation;
    private Alert alertConfirmation;
    @FXML
    private Label accountBalanceTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private ImageView imageLogo;
    @FXML
    private Button topUpButton;
    @FXML
    private TextField topUpAmountTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setResizable(false);
    }

    @FXML
    void onTopUpButtonClick(ActionEvent event) {
        String topUpAmountInString = topUpAmountTextField.getText();
        if (isValidDailyRate(topUpAmountInString)) {
            float topUpAmount = Float.parseFloat(topUpAmountInString);
            setAlert(
                    alertConfirmation,
                    "Confirm Top-Up",
                    String.format("Are you sure to top-up this amount? (RM %.2f)", topUpAmount)
            );

            if (alertResultOk(alertConfirmation.showAndWait())) {
                customer.addAccountBalance(topUpAmount);
                CustomerConfig.updateCustomer(customer);
                UserConfig.updateFile();

                setAlert(
                        alertInformation,
                        "Complete Top-Up",
                        "You've topped-up your account balance successfully."
                );

                alertInformation.show();
            }
        }
    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        if (unprocessedBooking != null) {
            switchLabelledUserSceneWithObject(event, "Checkout", unprocessedBooking);
        } else {
            switchLabelledUserScene(event, "Main");
        }
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        if (unprocessedBooking != null) {
            setAlert(
                    alertConfirmation,
                    "Incomplete Booking Alert",
                    "You have an incomplete booking, do you want to complete the booking?"
            );
            if (alertResultOk(alertConfirmation.showAndWait())) {
                setAlert(
                        alertInformation,
                        "Redirection",
                        "You will be directed back to the booking page."
                );
                alertInformation.show();
                switchLabelledUserSceneWithObject(event, "Checkout", unprocessedBooking);
            } else {
                setAlert(
                        alertInformation,
                        "Redirection",
                        "You will be directed back to the main page."
                );
                alertInformation.show();
                switchLabelledUserScene(event, "Main");
            }
        } else {
            switchLabelledUserScene(event, "Main");
        }
    }

    @Override
    public void setLabelData() {
        Object object = getObject();
        customer = (Customer) getUser();
        float accountBalance = customer.getAccountBalance();
        accountBalanceTextField.setText(String.format("%.2f", accountBalance));
        if (object instanceof UnprocessedBooking) {
            unprocessedBooking = (UnprocessedBooking) object;
            topUpAmountTextField.setText(String.format("%.2f", unprocessedBooking.getUnprocessedBookingAmount() - accountBalance));
        }
    }
}
