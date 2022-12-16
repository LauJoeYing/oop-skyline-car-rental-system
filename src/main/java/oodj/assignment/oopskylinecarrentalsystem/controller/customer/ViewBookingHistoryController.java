package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.config.BookingConfig;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import oodj.assignment.oopskylinecarrentalsystem.model.DateRange;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class ViewBookingHistoryController extends LabelledViewController implements Initializable {

    @FXML
    private TableColumn<Booking, Float> bookingAmountColumn;
    @FXML
    private TableColumn<Booking, LocalDateTime> bookingDateColumn;
    @FXML
    private TableView<Booking> bookingTableView;
    @FXML
    private TableColumn<Booking, DateRange> endDateColumn;
    @FXML
    private Button homeButton;
    @FXML
    private TableColumn<Booking, UUID> idColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableColumn<Booking, DateRange> startDateColumn;
    @FXML
    private TableColumn<Booking, String> statusColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookingDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDateTime"));
        bookingDateColumn.setCellFactory(bookingDateCell -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime bookingDate, boolean empty) {
                super.updateItem(bookingDate, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(bookingDate.format(formatter));
                }
            }
        });
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDateRange"));
        startDateColumn.setCellFactory(bookingDateCell -> new TableCell<>() {
            @Override
            protected void updateItem(DateRange bookingDateRange, boolean empty) {
                super.updateItem(bookingDateRange, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(bookingDateRange.getStartDate().toString());
                }
            }
        });
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDateRange"));
        endDateColumn.setCellFactory(bookingDateCell -> new TableCell<>() {
            @Override
            protected void updateItem(DateRange bookingDateRange, boolean empty) {
                super.updateItem(bookingDateRange, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(bookingDateRange.getEndDate().toString());
                }
            }
        });
        bookingAmountColumn.setCellValueFactory(new PropertyValueFactory<>("BookingAmount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, "Main");
    }

    @Override
    public void setLabelData() {
        List<Booking> bookingList = BookingConfig.getBookingFromCustomerUsername(getUser().getUsername());
        ObservableList<Booking> observableBookingList = FXCollections.observableList(bookingList);
        bookingTableView.setItems(observableBookingList);
    }

}
