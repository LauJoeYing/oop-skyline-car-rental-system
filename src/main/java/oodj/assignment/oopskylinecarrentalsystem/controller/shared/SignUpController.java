package oodj.assignment.oopskylinecarrentalsystem.controller.shared;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.config.CustomerConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.MailConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.UserConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.WarningConfig;
import oodj.assignment.oopskylinecarrentalsystem.model.Address;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.config.AlertConfig.alertResultEmptyOrOk;
import static oodj.assignment.oopskylinecarrentalsystem.config.AlertConfig.setAlert;
import static oodj.assignment.oopskylinecarrentalsystem.config.CustomerConfig.*;
import static oodj.assignment.oopskylinecarrentalsystem.config.StringConfig.isAnyContainsBlank;
import static org.apache.commons.lang.WordUtils.capitalize;

public class SignUpController extends CommonViewController implements Initializable {

    private Customer customer;
    private Alert alert;
    @FXML
    private TextField cityTextField;
    @FXML
    private PasswordField confirmPasswordPasswordField;
    @FXML
    private TextField emailTextField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private Button homeButton;
    @FXML
    private TextField icTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField postCodeTextField;
    @FXML
    private Button signUpButton;
    @FXML
    private TextArea signUpTipsTextArea;
    @FXML
    private ComboBox<String> stateComboBox;
    @FXML
    private TextField streetOneTextField;
    @FXML
    private TextField streetTwoTextField;
    @FXML
    private TextField unitTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField verificationCodeTextField;
    @FXML
    private Button verifyButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderComboBox.getItems().addAll(
                "Male",
                "Female"
        );
        genderComboBox.setValue("Male");

        stateComboBox.getItems().addAll(
                "Pahang",
                "Sabah",
                "Selangor",
                "Kedah",
                "Kelantan",
                "Sarawak",
                "Negeri Sembilan",
                "Johor",
                "Terengganu",
                "Malacca",
                "Perak",
                "Perlis",
                "Penang",
                "Labuan",
                "Putrajaya",
                "Kuala Lumpur"
        );
        stateComboBox.setValue("Kuala Lumpur");

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(false);
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchSharedScene(event, "Login");
    }

    @FXML
    void onSignUpButtonClick(ActionEvent event) {
        validateCustomerRegistration();
    }

    @FXML
    void onSignUpEnterKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            validateCustomerRegistration();
        }
    }

    @FXML
    void onVerificationCodeTextFieldEnterKeyPressed(KeyEvent event) throws IOException {
        registerCustomer(event);
    }

    @FXML
    void onVerifyButtonClick(ActionEvent event) throws IOException {
        registerCustomer(event);
    }

    private boolean isEmailVerified () {
        return MailConfig.validateUserEmail(customer.getUsername(), verificationCodeTextField.getText());
    }

    private void registerCustomer(Event event) throws IOException {
        signUpTipsTextArea.setText("");
        if (isEmailVerified()) {
            CustomerConfig.register(customer);
            UserConfig.updateFile();

            setAlert(
                    alert,
                    "Successful Registration",
                    "You've registered successfully. Please login to continue."
            );

            Optional<ButtonType> result = alert.showAndWait();

            if(alertResultEmptyOrOk(result)) {
                switchSharedScene(event, "Login");
            }
        } else {
            signUpTipsTextArea.setText("Invalid verification code.");
        }
    }

    private void validateCustomerRegistration() {
        if (validateRegistration()) {
            sendEmailVerificationCode();
        }
    }

    private boolean validateRegistration() {
        signUpTipsTextArea.setText("");

        String username = usernameTextField.getText();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String icNumber = icTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String unit = unitTextField.getText();
        String street1 = streetOneTextField.getText();
        String street2 = streetTwoTextField.getText();
        String city = cityTextField.getText();
        String postcode = postCodeTextField.getText();
        String state = stateComboBox.getValue();
        String password = passwordPasswordField.getText();
        String confirmPassword = confirmPasswordPasswordField.getText();

        if (isAnyContainsBlank(
                username,
                name,
                icNumber,
                phoneNumber,
                unit,
                street1,
                city,
                postcode,
                state,
                password,
                confirmPassword
        )
        ) {
            signUpTipsTextArea.setText(WarningConfig.FILLINALLFIELDS);
            return false;
        } else {
            username = username.trim();
            name = toStandardName(name.trim());
            email = toStandardEmail(email.trim());
            icNumber = toStandardIcNumber(icNumber.trim());
            phoneNumber = toStandardPhoneNumber(phoneNumber.trim());
            unit = capitalize(unit.trim());
            street1 = capitalize(street1.trim());
            street2 = street2.equals("") ? null : capitalize(street2.trim());
            city = capitalize(city.trim());
            state = capitalize(state.trim());

            if (!isValidUsername(username)) {
                signUpTipsTextArea.setText(WarningConfig.USER.USERNAME);
                return false;
            }
            if (!isValidName(name)) {
                signUpTipsTextArea.setText(WarningConfig.USER.NAME);
                return false;
            }
            if (!isValidEmailToCreate(email)) {
                signUpTipsTextArea.setText(WarningConfig.USER.EMAIL);
                return false;
            }
            if (!isValidIcNumber(icNumber)) {
                signUpTipsTextArea.setText(WarningConfig.USER.IC);
                return false;
            }
            if (!isValidPhoneNumberToCreate(phoneNumber)) {
                signUpTipsTextArea.setText(WarningConfig.USER.PHONENUMBER);
                return false;
            }
            if (!isValidPostcode(postcode)) {
                signUpTipsTextArea.setText(WarningConfig.USER.POSTCODE);
                return false;
            }
            if (!isValidPassword(password)) {
                signUpTipsTextArea.setText(WarningConfig.USER.PASSWORD);
                return false;
            }
            if (!password.equals(confirmPassword)) {
                signUpTipsTextArea.setText(WarningConfig.USER.UNMATCHEDPASSWORD);
                return false;
            }
            customer = new Customer(
                    username,
                    password,
                    name,
                    email,
                    icNumber,
                    phoneNumber,
                    new Address(unit, street1, street2, city, postcode, state)
            );
            return true;
        }
    }

    private void sendEmailVerificationCode() {
        setAlert(
                alert,
                "Verify your registration",
                String.format(
                        """
                        A verification code has been sent to your email at %s.
                        If you do not see the email in a few minutes, check your “junk mail” folder or “spam” folder.
                        """, toStandardEmail(customer.getEmailAddress())
                )
        );

        alert.show();
        MailConfig.sendVerificationCode(customer);
    }
}
