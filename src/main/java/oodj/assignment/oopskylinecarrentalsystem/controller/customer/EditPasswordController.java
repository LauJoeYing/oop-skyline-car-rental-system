package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.util.CustomerUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.UserUtils;
import oodj.assignment.oopskylinecarrentalsystem.constant.WARNING;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.CommonViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.alertResultOk;
import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.setAlert;
import static oodj.assignment.oopskylinecarrentalsystem.util.CustomerUtils.*;
import static oodj.assignment.oopskylinecarrentalsystem.util.StringUtils.isAnyContainsBlank;
import static oodj.assignment.oopskylinecarrentalsystem.util.WarningUtils.resetLabel;

public class EditPasswordController extends CommonViewController implements Initializable {

    private Customer customer;
    private Alert alertConfirmation;
    private Alert alertInformation;
    @FXML
    private Button changePasswordButton;
    @FXML
    private TextField confirmPasswordPasswordField;
    @FXML
    private Label confirmPasswordWarningLabel;
    @FXML
    private TextField currentPasswordPasswordField;
    @FXML
    private Label currentPasswordWarningLabel;
    @FXML
    private Button homeButton;
    @FXML
    private ImageView imageLogo;
    @FXML
    private TextField newPasswordPasswordField;
    @FXML
    private Label newPasswordWarningLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setResizable(false);
    }

    @FXML
    void onChangePasswordButtonClick(ActionEvent event) {
        updateAccountPassword();

    }

    @FXML
    void onChangePasswordEnterKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            updateAccountPassword();
        }
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    private void updateAccountPassword() {
        if (validateAccountDetailsModification()) {
            updateAccount();
        }
    }

    private boolean validateAccountDetailsModification() {
        resetLabel(
                currentPasswordWarningLabel,
                newPasswordWarningLabel,
                confirmPasswordWarningLabel
        );

        boolean isValidModification = true;

        String currentPassword = currentPasswordPasswordField.getText();
        String newPassword = newPasswordPasswordField.getText();
        String confirmPassword = confirmPasswordPasswordField.getText();

        if (isAnyContainsBlank(
                currentPassword,
                newPassword,
                confirmPassword
        )
        ) {
            confirmPasswordWarningLabel.setText(WARNING.FILL_IN_ALL_THE_FIELDS);
        } else {
            if (!isValidPassword(newPassword)) {
                newPasswordWarningLabel.setText(WARNING.USER.PASSWORD);
                isValidModification = false;
            }
            if (!newPassword.equals(confirmPassword)) {
                confirmPasswordWarningLabel.setText(WARNING.USER.UNMATCHED_PASSWORD);
                isValidModification = false;
            }
            if (isValidModification) {
                this.customer.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    private void updateAccount() {
        setAlert(
                alertConfirmation,
                "Confirm Update",
                "Are you sure to update the account password?"
        );
        if (alertResultOk(alertConfirmation.showAndWait())) {
            CustomerUtils.updateCustomer(customer);
            UserUtils.updateFile();
            setAlert(
                    alertInformation,
                    "Complete Update",
                    "You've updated the account password successfully."
            );
            alertInformation.show();
        }
    }
}
