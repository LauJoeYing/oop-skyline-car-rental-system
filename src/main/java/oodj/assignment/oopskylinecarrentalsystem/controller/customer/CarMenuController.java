package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.config.CarConfig;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;
import oodj.assignment.oopskylinecarrentalsystem.model.DateRange;
import oodj.assignment.oopskylinecarrentalsystem.model.UnprocessedBooking;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.config.AlertConfig.setAlert;
import static oodj.assignment.oopskylinecarrentalsystem.config.CarConfig.getCarUnavailableDatesFromId;

public class CarMenuController extends LabelledViewController implements Initializable {
    private ObservableList<Car> carList = FXCollections.observableList(CarConfig.getCarList());
    private Car carSelected;
    private Alert alertConfirmation;
    @FXML
    private Button bookCarButton;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TableView<Car> carTableView;
    @FXML
    private TableColumn<Car, String> idColumn;
    @FXML
    private TableColumn<Car, String> brandColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> typeColumn;
    @FXML
    private TableColumn<Car, String> transmissionTypeColumn;
    @FXML
    private TableColumn<Car, Float> dailyRateColumn;
    @FXML
    private Button clearButton;
    @FXML
    private TextField dailyRateTextField;
    @FXML
    private DatePicker endDateDatePicker;
    @FXML
    private Button homeButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField searchTextField;
    @FXML
    private DatePicker startDateDatePicker;
    @FXML
    private TextField transmissionTypeTextField;
    @FXML
    private TextField typeTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmation.setResizable(false);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        transmissionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transmissionType"));
        dailyRateColumn.setCellValueFactory(new PropertyValueFactory<>("dailyRate"));
        dailyRateColumn.setCellFactory(dailyRateCell -> new TableCell<>() {
            @Override
            protected void updateItem(Float dailyRate, boolean empty) {
                super.updateItem(dailyRate, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", dailyRate));
                }
            }
        });

        carTableView.setItems(carList);

        carTableView.setRowFactory(tv -> {
            TableRow<Car> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (!row.isEmpty()) {
                    setBookingSelectionDetails();
                }
            });
            return row;
        });
    }

    @FXML
    void onBookCarButton(ActionEvent event) throws IOException {
        LocalDate startDate = startDateDatePicker.getValue();
        LocalDate endDate = endDateDatePicker.getValue();

        if (startDate != null && endDate != null) {
            DateRange dateRangeSelected = new DateRange(startDate, endDate);

            setAlert(
                    alertConfirmation,
                    "Confirm Book Car",
                    "Are you sure to book this car?"
            );

            switchLabelledUserSceneWithObject(event, "DriverDetails", new UnprocessedBooking(carSelected, dateRangeSelected));
        }
    }

    @FXML
    void onClearButtonClick(ActionEvent event) {
        clearButton.setVisible(false);
        searchTextField.setText("");
        resetCarTableView();
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, "Main");
    }

    @FXML
    void searchTextFieldKeyPressed(KeyEvent event) {
        String searchKey = searchTextField.getText();
        clearButton.setVisible(searchKey.length() >= 1);
        searchCar();
    }

    private void setBookingSelectionDetails() {
        carSelected = carTableView.getSelectionModel().getSelectedItem();
        setCarDetailsInBookingSelection();

        List<LocalDate> selectedCarUnavailableDates = getCarUnavailableDatesFromId(carSelected.getId());
        LocalDate today = LocalDate.now();

        startDateDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate startDate, boolean empty) {
                super.updateItem(startDate, empty);

                if (startDate.isBefore(today)
                        || selectedCarUnavailableDates.contains(startDate)
                        || startDate.minusDays(1).isBefore(today) && selectedCarUnavailableDates.contains(startDate.plusDays(1))
                        || (endDateDatePicker.getValue() != null && startDate.isAfter(endDateDatePicker.getValue().minusDays(1)))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        endDateDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate endDate, boolean empty) {
                super.updateItem(endDate, empty);

                if (endDate.isBefore(today)
                        || selectedCarUnavailableDates.contains(endDate)
                        || endDate.minusDays(1).isBefore(today) && selectedCarUnavailableDates.contains(endDate.plusDays(1))
                        ||(startDateDatePicker.getValue() != null && endDate.isBefore(startDateDatePicker.getValue().plusDays(1)))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }

    private void resetCarTableView() {
        carList = FXCollections.observableList(CarConfig.getCarList());
    }

    private void searchCar() {
        String searchKey = searchTextField.getText().trim();

        if (searchKey.equals("")) {
            resetCarTableView();
        } else {
            carList = FXCollections.observableList(CarConfig.searchCar((searchKey)));
        }
    }

    @Override
    public void setLabelData() {
        UnprocessedBooking unprocessedBooking = (UnprocessedBooking) getObject();
        carSelected = unprocessedBooking.getCar();
        DateRange bookingDateRange = unprocessedBooking.getBookingDateRange();
        setCarDetailsInBookingSelection();
        startDateDatePicker.setValue(bookingDateRange.getStartDate());
        endDateDatePicker.setValue(bookingDateRange.getEndDate());
    }

    private void setCarDetailsInBookingSelection() {
        idTextField.setText(carSelected.getId());
        brandTextField.setText(carSelected.getBrand());
        modelTextField.setText(carSelected.getModel());
        typeTextField.setText(carSelected.getType());
        transmissionTypeTextField.setText(carSelected.getTransmissionType());
        dailyRateTextField.setText(String.format("RM %.2f", carSelected.getDailyRate()));
    }


}
