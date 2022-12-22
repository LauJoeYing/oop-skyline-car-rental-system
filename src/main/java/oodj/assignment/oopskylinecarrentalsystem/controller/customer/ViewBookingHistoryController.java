package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.util.BookingUtils;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Booking;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    private TableColumn<Booking, LocalDate> endDateColumn;
    @FXML
    private Button homeButton;
    @FXML
    private TableColumn<Booking, UUID> idColumn;
    @FXML
    private TableColumn<Booking, LocalDate> startDateColumn;
    @FXML
    private TableColumn<Booking, String> statusColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookingDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDateTime"));
        bookingDateColumn.setCellFactory(bookingDateCell -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime bookingDate, boolean empty) {
                super.updateItem(bookingDate, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(bookingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")));
                }
            }
        });
        startDateColumn.setCellValueFactory(booking -> new SimpleObjectProperty<>(booking.getValue().getBookingDateRange().getStartDate()));
        endDateColumn.setCellValueFactory(booking -> new SimpleObjectProperty<>(booking.getValue().getBookingDateRange().getEndDate()));
        bookingAmountColumn.setCellValueFactory(new PropertyValueFactory<>("BookingAmount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    @Override
    public void setLabelData() {
        List<Booking> bookingList = BookingUtils.getBookingFromCustomerUsername(getUser().getUsername());
        ObservableList<Booking> observableBookingList = FXCollections.observableList(bookingList);
        bookingTableView.setItems(observableBookingList);
    }

}
