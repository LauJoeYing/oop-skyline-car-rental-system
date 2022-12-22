package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.util.CustomerUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.UserUtils;
import oodj.assignment.oopskylinecarrentalsystem.constant.WARNING;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Address;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.*;
import static oodj.assignment.oopskylinecarrentalsystem.util.CustomerUtils.*;
import static oodj.assignment.oopskylinecarrentalsystem.util.StringUtils.isAnyContainsBlank;
import static oodj.assignment.oopskylinecarrentalsystem.util.WarningUtils.resetLabel;
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
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    @FXML
    void onUpdateDetailsButtonClick(ActionEvent event) throws IOException {
        updateAccountDetails(event);
    }

    @FXML
    void onUpdateEnterKeyPressed(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            updateAccountDetails(event);
        }
    }

    @Override
    public void setLabelData() {
        customer = (Customer) getUser();
        usernameTextField.setText(customer.getUsername());
        icNumberTextField.setText(customer.getIcNumber());
        emailAddressTextField.setText(customer.getEmailAddress());
        genderComboBox.setValue(customer.getGender());
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

    private void updateAccountDetails(Event event) throws IOException {
        if (validateAccountDetailsModification()) {
            updateAccount(event);
        }
    }

    private void updateAccount(Event event) throws IOException {
        setAlert(
                alertConfirmation,
                "Confirm Update",
                "Are you sure to update the account details?"
        );
        if (alertResultOk(alertConfirmation.showAndWait())) {
            CustomerUtils.updateCustomer(customer);
            UserUtils.updateFile();
            setAlert(
                    alertInformation,
                    "Complete Update",
                    "You've updated the account details successfully."
            );
            if (alertResultEmptyOrOk(alertInformation.showAndWait())) {
                switchLabelledUserScene(event, FILEPATH.USER_MAIN);
            };
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
        String gender = genderComboBox.getValue();
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
            warningLabel.setText(WARNING.FILL_IN_ALL_THE_FIELDS);
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
                nameWarningLabel.setText(WARNING.USER.NAME);
                isValidModification = false;
            }
            if (!isValidEmailToModify(customer, email)) {
                emailAddressWarningLabel.setText(WARNING.USER.EMAIL);
                isValidModification = false;
            }
            if (!isValidPhoneNumberToModify(customer, phoneNumber)) {
                phoneNumberWarningLabel.setText(WARNING.USER.PHONE_NUMBER);
                isValidModification = false;
            }
            if (!isValidPostcode(postcode)) {
                postcodeWarningLabel.setText(WARNING.USER.POSTCODE);
                isValidModification = false;
            }
            if (isValidModification) {
                this.customer.setName(name);
                this.customer.setEmailAddress(email);
                this.customer.setPhoneNumber(phoneNumber);
                this.customer.setGender(gender);
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
