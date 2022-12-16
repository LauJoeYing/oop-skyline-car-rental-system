package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import oodj.assignment.oopskylinecarrentalsystem.config.CarConfig;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledController;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static oodj.assignment.oopskylinecarrentalsystem.config.ImageConfig.ImgAssign;

public class RentCarController extends LabelledController {

    @FXML
    private ImageView carImageView;
    @FXML
    private Text brandModelText;
    @FXML
    private Button confirmButton;
    @FXML
    private Button crossButton;
    @FXML
    private TextField dailyRateTextField;
    @FXML
    private DatePicker endDateDatePicker;
    @FXML
    private ComboBox<String> endTimeComboBox;
    @FXML
    private TextField seaterTextField;
    @FXML
    private DatePicker startDateDatePicker;
    @FXML
    private ComboBox<String> startTimeComboBox;
    @FXML
    private TextField transmissionTypeTextField;
    @FXML
    private TextField typeTextField;

    @FXML
    void onConfirmButtonClick(ActionEvent event) {

    }

    @FXML
    void onCrossButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, "CarMenu");
    }

    @Override
    public void setLabelData() {
        Car car = (Car) getObject();
        brandModelText.setText(String.join(" ", car.getBrand(), car.getModel()));
        carImageView.setImage(new Image(ImgAssign(car.getModel())));
        dailyRateTextField.setText(String.format("RM %.2f", car.getDailyRate()));
        transmissionTypeTextField.setText(car.getTransmissionType());
        typeTextField.setText(car.getType());

        List<LocalDate> selectedCarUnavailableList = CarConfig.getCarUnavailableDatesFromId(car.getId());
        LocalDate today = LocalDate.now();

        startDateDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate startDate, boolean empty) {
                super.updateItem(startDate, empty);

                if (startDate.isBefore(today)
                        || selectedCarUnavailableList.contains(startDate)
                        || startDate.minusDays(1).isBefore(today) && selectedCarUnavailableList.contains(startDate.plusDays(1))
                        || (endDateDatePicker.getValue() != null && startDate.isAfter(endDateDatePicker.getValue().minusDays(1)))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        endDateDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate startDate, boolean empty) {
                super.updateItem(startDate, empty);

                if (startDate.isBefore(today)
                        || selectedCarUnavailableList.contains(startDate)
                        || startDate.minusDays(1).isBefore(today) && selectedCarUnavailableList.contains(startDate.plusDays(1))
                        || (startDateDatePicker.getValue() != null && startDate.isAfter(startDateDatePicker.getValue().plusDays(1)))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }


}
