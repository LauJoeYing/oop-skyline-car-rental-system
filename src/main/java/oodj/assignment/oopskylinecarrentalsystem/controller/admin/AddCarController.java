package oodj.assignment.oopskylinecarrentalsystem.controller.admin;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.util.CarUtils;
import oodj.assignment.oopskylinecarrentalsystem.constant.WARNING;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.CommonViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.*;
import static oodj.assignment.oopskylinecarrentalsystem.util.CarUtils.*;
import static oodj.assignment.oopskylinecarrentalsystem.util.FloatUtils.roundToTwoDecimals;
import static oodj.assignment.oopskylinecarrentalsystem.util.StringUtils.isAnyContainsBlank;
import static oodj.assignment.oopskylinecarrentalsystem.util.WarningUtils.resetLabel;
import static org.apache.commons.lang.WordUtils.capitalize;

public class AddCarController extends CommonViewController implements Initializable {

    private Car newCar;
    private Alert alertConfirmation;
    private Alert alertInformation;

    @FXML
    private Button addNewCarButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField dailyRateTextField;
    @FXML
    private Button homeButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField imageURLTextField;
    @FXML
    private Label carIdWarningLabel;
    @FXML
    private Label warningLabel;
    @FXML
    private Label dailyRateWarningLabel;
    @FXML
    private TextField modelTextField;
    @FXML
    private ComboBox<String> transmissionTypeComboBox;
    @FXML
    private ComboBox<String> typeComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeComboBox.getItems().addAll(
                "Hatchback",
                "Sedan",
                "SUV"
        );
        typeComboBox.getSelectionModel().selectFirst();

        transmissionTypeComboBox.getItems().addAll(
                "Automatic",
                "Manual"
        );
        transmissionTypeComboBox.getSelectionModel().selectFirst();

        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setResizable(false);
    }

    @FXML
    void onAddNewCarEnterKeyPressed(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            addCar(event);
        }
    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.ADMIN.MANAGE_CAR_MENU);
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    @FXML
    void onAddNewCarButtonClick(ActionEvent event) throws IOException {
        addCar(event);
    }

    private void addCar(Event event) throws IOException {
        if(validateCarCreation()) {
            setAlert(
                    alertConfirmation,
                    "Confirm Add New Car",
                    "Are you sure to add this car?"
            );

            if (alertResultOk(alertConfirmation.showAndWait())) {
                CarUtils.addCar(newCar);
                CarUtils.updateFile();

                setAlert(
                        alertInformation,
                        "Complete Add Car",
                        "You've added the car successfully."
                );

                if (alertResultEmptyOrOk(alertInformation.showAndWait())) {
                    switchUserScene(event, FILEPATH.ADMIN.MANAGE_CAR_MENU);
                }
            }
        }
    }

    private boolean validateCarCreation() {
        resetLabel(warningLabel, carIdWarningLabel, dailyRateWarningLabel);

        boolean isValidCreation = true;

        String carId = idTextField.getText();
        String brand = brandTextField.getText();
        String model = modelTextField.getText();
        String type = typeComboBox.getValue();
        String transmissionType = transmissionTypeComboBox.getValue();
        String dailyRateInString = dailyRateTextField.getText();

        if (isAnyContainsBlank(carId,
                brand,
                model,
                dailyRateInString
        )
        ) {
            warningLabel.setText(WARNING.FILL_IN_ALL_THE_FIELDS);
        } else {
            carId = toStandardCarId(carId.trim());
            brand = capitalize(brand.trim());
            model = capitalize(model.trim());

            if (!isValidCarIdToCreate(carId)) {
                carIdWarningLabel.setText(WARNING.CAR.CAR_ID);
                isValidCreation = false;
            }
            if (!isValidDailyRate(dailyRateInString)) {
                dailyRateWarningLabel.setText(WARNING.CAR.DAILY_RATE);
                isValidCreation = false;
            }
            if (isValidCreation) {
                newCar = new Car(
                        carId,
                        brand,
                        model,
                        type,
                        transmissionType,
                        roundToTwoDecimals(Float.parseFloat(dailyRateInString))
                );
                return true;
            }
        }
        return false;
    }

}
