package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.config.CustomerConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.UserConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.WarningConfig;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Address;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.config.AlertConfig.alertResultOk;
import static oodj.assignment.oopskylinecarrentalsystem.config.AlertConfig.setAlert;
import static oodj.assignment.oopskylinecarrentalsystem.config.CustomerConfig.*;
import static oodj.assignment.oopskylinecarrentalsystem.config.StringConfig.isAnyContainsBlank;
import static oodj.assignment.oopskylinecarrentalsystem.config.WarningConfig.resetLabel;
import static org.apache.commons.lang.WordUtils.capitalize;

public class EditAccountDetailsController extends LabelledViewController implements Initializable {

    private Customer customer;
    private Alert alertInformation;
    private Alert alertConfirmation;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField emailAddressTextField;
    @FXML
    private Label emailAddressWarningLabel;
    @FXML
    private Button homeButton;
    @FXML
    private TextField icNumberTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label nameWarningLabel;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private Label phoneNumberWarningLabel;
    @FXML
    private TextField postcodeTextField;
    @FXML
    private Label postcodeWarningLabel;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private ComboBox<String> stateComboBox;
    @FXML
    private TextField streetLine1TextField;
    @FXML
    private TextField streetLine2TextField;
    @FXML
    private TextField unitTextField;
    @FXML
    private Button updateDetailsButton;
    @FXML
    private TextField usernameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderComboBox.getItems().addAll(
                "Male",
                "Female"
        );
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
        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setResizable(false);
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, "Main");
    }

    @FXML
    void onUpdateDetailsButtonClick(ActionEvent event) {
        updateAccountDetails();
    }

    @FXML
    void onUpdateEnterKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            updateAccountDetails();
        }
    }

    @Override
    public void setLabelData() {
        customer = (Customer) getUser();
        usernameTextField.setText(customer.getUsername());
        icNumberTextField.setText(customer.getIcNumber());
        emailAddressTextField.setText(customer.getEmailAddress());
        genderComboBox.setValue("Male");
        nameTextField.setText(customer.getName());
        phoneNumberTextField.setText(customer.getPhoneNumber());
        Address address = customer.getAddress();
        unitTextField.setText(address.getUnitNumber());
        streetLine1TextField.setText(address.getStreetAddressLine1());
        String streetLine2 = address.getStreetAddressLine2();
        streetLine2TextField.setText(streetLine2 == null ? "" :streetLine2);
        postcodeTextField.setText(address.getPostcode());
        cityTextField.setText(address.getCity());
        stateComboBox.setValue(address.getState());
    }

    private void updateAccountDetails() {
        if (validateAccountDetailsModification()) {
            updateAccount();
        }
    }

    private void updateAccount() {
        setAlert(
                alertConfirmation,
                "Confirm Update",
                "Are you sure to update the account details?"
        );
        if (alertResultOk(alertConfirmation.showAndWait())) {
            CustomerConfig.updateCustomer(customer);
            UserConfig.updateFile();
            setAlert(
                    alertInformation,
                    "Complete Update",
                    "You've updated the account details successfully."
            );
            alertInformation.show();
        }
    }

    private boolean validateAccountDetailsModification() {
        resetLabel(
                warningLabel,
                nameWarningLabel,
                emailAddressWarningLabel,
                phoneNumberWarningLabel,
                postcodeWarningLabel
        );

        boolean isValidModification = true;

        String name = nameTextField.getText();
        String email = emailAddressTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String houseNo = unitTextField.getText();
        String street1 = streetLine1TextField.getText();
        String street2 = streetLine2TextField.getText();
        String city = cityTextField.getText();
        String postcode = postcodeTextField.getText();
        String state = stateComboBox.getValue();

        if (isAnyContainsBlank(
                name,
                email,
                phoneNumber,
                houseNo,
                street1,
                city,
                postcode,
                state
        )
        ) {
            warningLabel.setText(WarningConfig.FILLINALLFIELDS);
        } else {
            name = toStandardName(name.trim());
            email = toStandardEmail(email.trim());
            phoneNumber = toStandardPhoneNumber(phoneNumber.trim());
            houseNo = capitalize(houseNo.trim());
            street1 = capitalize(street1.trim());
            street2 = street2.equals("") ? null : capitalize(street2.trim());
            city = capitalize(city.trim());
            state = capitalize(state.trim());

            if (!isValidName(name)) {
                nameWarningLabel.setText(WarningConfig.USER.NAME);
                isValidModification = false;
            }
            if (!isValidEmailToModify(customer, email)) {
                emailAddressWarningLabel.setText(WarningConfig.USER.EMAIL);
                isValidModification = false;
            }
            if (!isValidPhoneNumberToModify(customer, phoneNumber)) {
                phoneNumberWarningLabel.setText(WarningConfig.USER.PHONENUMBER);
                isValidModification = false;
            }
            if (!isValidPostcode(postcode)) {
                postcodeWarningLabel.setText(WarningConfig.USER.POSTCODE);
                isValidModification = false;
            }
            if (isValidModification) {
                this.customer.setName(name);
                this.customer.setEmailAddress(email);
                this.customer.setPhoneNumber(phoneNumber);
                this.customer.setAddress(new Address(
                        houseNo,
                        street1,
                        street2,
                        city,
                        postcode,
                        state
                ));

                return true;
            }
        }
        return false;
    }

}
