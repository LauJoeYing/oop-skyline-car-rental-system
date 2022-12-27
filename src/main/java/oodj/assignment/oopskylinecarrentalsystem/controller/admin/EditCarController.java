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
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.alertResultEmptyOrOk;
import static oodj.assignment.oopskylinecarrentalsystem.util.AlertUtils.setAlert;
import static oodj.assignment.oopskylinecarrentalsystem.util.CarUtils.*;
import static oodj.assignment.oopskylinecarrentalsystem.util.FloatUtils.roundToTwoDecimals;
import static oodj.assignment.oopskylinecarrentalsystem.util.StringUtils.isAnyContainsBlank;
import static oodj.assignment.oopskylinecarrentalsystem.util.WarningUtils.resetLabel;
import static org.apache.commons.lang.WordUtils.capitalize;

public class EditCarController extends LabelledViewController implements Initializable {

    private Car car;
    private Alert alertConfirmation;
    private Alert alertInformation;

    @FXML
    private Button backButton;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField dailyRateTextField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button homeButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private ComboBox<String> transmissionTypeComboBox;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Button updateButton;
    @FXML
    private Label warningLabel;
    @FXML
    private Label idWarningLabel;
    @FXML
    private Label dailyRateWarningLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);
        alertInformation = new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setResizable(false);
    }

    @FXML
    void deleteButtonClick(ActionEvent event) throws IOException {
        modifyAction(event, "Delete");
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
    void onUpdateButtonClick(ActionEvent event) throws IOException {
        modifyAction(event, "Update");
    }

    @Override
    public void setLabelData() {
        car = (Car) getObject();
        idTextField.setText(car.getId());
        brandTextField.setText(car.getBrand());
        modelTextField.setText(car.getModel());

        typeComboBox.getItems().addAll(
                "Hatchback",
                "Sedan",
                "SUV"
        );
        typeComboBox.setValue(car.getType());

        transmissionTypeComboBox.getItems().addAll(
                "Automatic",
                "Manual"
        );
        transmissionTypeComboBox.setValue(car.getTransmissionType());

        dailyRateTextField.setText(String.format("%.2f", car.getDailyRate()));
    }

    @FXML
    private void onEnterKeyPressed(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER){
            modifyAction(event, "Update");
        }
    }

    private void modifyAction(Event event, String action) throws IOException {     //Confirmation for Changing Car Details
        if (action.equals("Update")) {
            if (!validateCarModification()) {
                return;
            };
        }
        setAlert(
                alertConfirmation,
                action.equals("Delete") ? "Confirm Deletion" : "Confirm Update",
                String.format("Are you sure to %s this car?", action.equals("Delete") ? "delete" : "update")
        );

        Optional<ButtonType> resultConfirmation = alertConfirmation.showAndWait();
        if (resultConfirmation.isPresent() && resultConfirmation.get() == ButtonType.OK) {
            if (action.equals("Delete")) {
                CarUtils.removeCar(car);
            } else {
                CarUtils.updateCar(car);
            }
            CarUtils.updateFile();
            setAlert(
                    alertInformation,
                    action.equals("Delete") ? "Complete Deletion" : "Complete Update",
                    String.format("You've %s the car successfully.", action.equals("Delete") ? "deleted" : "updated")
            );

            Optional<ButtonType> resultInformation = alertInformation.showAndWait();
            if (alertResultEmptyOrOk(resultInformation)) {
                switchUserScene(event, FILEPATH.ADMIN.MANAGE_CAR_MENU);
            }
        }
    }

    private boolean validateCarModification() {                 //Validation of Car Details Input
        resetLabel(warningLabel, idWarningLabel, dailyRateWarningLabel);

        boolean isValidModification = true;

        String carId = idTextField.getText();
        String brand = brandTextField.getText();
        String model = modelTextField.getText();
        String type = typeComboBox.getValue();
        String transmissionType = transmissionTypeComboBox.getValue();
        String dailyRateInString = dailyRateTextField.getText();

        if (isAnyContainsBlank(
                carId,
                brand,
                model,
                dailyRateInString
        )
        ) {
            warningLabel.setText(WARNING.FILL_IN_ALL_THE_FIELDS);
        } else {
            carId = toStandardCarId(carId.trim());
            brand = capitalize(brand);
            model = capitalize(model);

            if (!isValidCarIdToModify(car, carId)) {
                idWarningLabel.setText(WARNING.CAR.CAR_ID);
                isValidModification = false;
            }
            if (!isValidDailyRate(dailyRateInString)) {
                dailyRateWarningLabel.setText(WARNING.CAR.DAILY_RATE);
                isValidModification = false;
            }
            if (isValidModification) {
                car.setId(carId);
                car.setBrand(brand);
                car.setModel(model);
                car.setType(type);
                car.setTransmissionType(transmissionType);
                car.setDailyRate(roundToTwoDecimals(Float.parseFloat(dailyRateInString)));

                return true;
            }
        }
        return false;
    }

}
