package oodj.assignment.oopskylinecarrentalsystem.controller.admin;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.util.BookingUtils;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.CommonViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import oodj.assignment.oopskylinecarrentalsystem.util.SearchUtils;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class ManageBookingController extends CommonViewController implements Initializable {

    private final ObservableList<Booking> bookingObservableList = FXCollections.observableList(BookingUtils.getBookingList());
    private final ObservableList<Booking> pendingBookingObservableList = FXCollections.observableList(BookingUtils.getBookingListByStatus("Pending"));

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
    private TableColumn<Booking, String> idColumn;
    @FXML
    private CheckBox pendingCheckBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableColumn<Booking, LocalDate> startDateColumn;
    @FXML
    private TableColumn<Booking, String> usernameColumn;
    @FXML
    private TableColumn<Booking, String> statusColumn;
    @FXML
    private Button approveOrRejectBookingRequestButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookingDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDateTime"));
        bookingDateColumn.setCellFactory(bookingDateColumn -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")));
                }
            }
        });

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("customerUsername"));
        startDateColumn.setCellValueFactory(booking -> new SimpleObjectProperty<>(booking.getValue().getBookingDateRange().getStartDate()));
        endDateColumn.setCellValueFactory(booking -> new SimpleObjectProperty<>(booking.getValue().getBookingDateRange().getEndDate()));
        bookingAmountColumn.setCellValueFactory(new PropertyValueFactory<>("bookingAmount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        bookingTableView.setItems(bookingObservableList);

        bookingTableView.setRowFactory(tv -> {
            TableRow<Booking> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 1 && !row.isEmpty()) {
                    setApproveOrRejectBookingRequestButtonVisibility();
                }
                if (mouseEvent.getClickCount() > 1 && !row.isEmpty()) {
                    try {
                        modifyBooking(mouseEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    private void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    @FXML
    private void onSearchTextFieldKeyTyped(KeyEvent event) {
        String searchKey = searchTextField.getText().trim();
        if (searchKey.length() == 0) {
            if (pendingCheckBox.isSelected()) {
                resetPendingBookingTableView();
            } else {
                resetBookingTableView();
            }
        } else {
            if (pendingCheckBox.isSelected()) {
                searchPendingBooking(searchKey);
            } else {
                searchBooking(searchKey);
            }
        }

    }

    @FXML
    private void onApproveOrRejectBookingRequestButtonClick(ActionEvent event) throws IOException {
        modifyBooking(event);
    }

    private void setApproveOrRejectBookingRequestButtonVisibility() {
        Booking booking = bookingTableView.getSelectionModel().getSelectedItem();
        if (booking != null) {
            approveOrRejectBookingRequestButton.setVisible(true);
            if (booking.getStatus().equals("Pending")) {
                approveOrRejectBookingRequestButton.setText("Approve/ Reject Booking Request");
            } else {
                approveOrRejectBookingRequestButton.setText("View Booking");
            }
        }
    }

    private void resetBookingTableView() {
        bookingTableView.setItems(bookingObservableList);
    }
    private void resetPendingBookingTableView() {
        bookingTableView.setItems(pendingBookingObservableList);
    }

    private void searchBooking(String searchKey) {
        ObservableList<Booking> matchingCarList = FXCollections.observableList(SearchUtils.search(bookingObservableList, searchKey));
        bookingTableView.setItems(matchingCarList);
    }

    private void searchPendingBooking(String searchKey) {
        ObservableList<Booking> matchingCarList = FXCollections.observableList(SearchUtils.search(pendingBookingObservableList, searchKey));
        bookingTableView.setItems(matchingCarList);
    }

    @FXML
    private void onPendingCheckBoxChange(ActionEvent event) {
        String searchKey = searchTextField.getText().trim();
        if (pendingCheckBox.isSelected()) {
            if (searchKey.length() == 0) {
                resetPendingBookingTableView();
            } else {
                searchPendingBooking(searchKey);
            }
        } else {
            if (searchKey.length() == 0) {
                resetBookingTableView();
            } else {
                searchBooking(searchKey);
            }
        }
    }

    private void modifyBooking(Event event) throws IOException {
        Booking booking = bookingTableView.getSelectionModel().getSelectedItem();
        if (booking != null) {
            switchLabelledUserSceneWithObject(event, FILEPATH.ADMIN.PENDING_APPROVAL, booking);
        }
    }
}
