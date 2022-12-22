package oodj.assignment.oopskylinecarrentalsystem.controller.admin;

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
import oodj.assignment.oopskylinecarrentalsystem.util.SearchUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.TransactionUtils;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.CommonViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.UUID;

public class TransactionRecordController extends CommonViewController implements Initializable {

    private ObservableList<Transaction> transactionList = FXCollections.observableList(TransactionUtils.getTransactionList());

    @FXML
    private TableColumn<Transaction, Float> amountColumn;

    @FXML
    private TableColumn<Transaction, LocalDateTime> dateTimeColumn;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<Transaction, String> referenceIdColumn;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<Transaction, UUID> transactionIdColumn;

    @FXML
    private TableView<Transaction> transactionTableView;

    @FXML
    private TableColumn<Transaction, String> transactionTypeColumn;

    @FXML
    private Button viewPaymentDetailsButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        referenceIdColumn.setCellValueFactory(new PropertyValueFactory<>("referenceId"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateTimeColumn.setCellFactory(dateTimeTableColumn -> new TableCell<>() {
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

        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountColumn.setCellFactory(amountTableColumn -> new TableCell<>() {
            @Override
            protected void updateItem(Float amount, boolean empty) {
                super.updateItem(amount, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", amount));
                }
            }
        });

        transactionTableView.setItems(FXCollections.observableArrayList(TransactionUtils.getTransactionList()));
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    @FXML
    void onSearchTextFieldKeyTyped(KeyEvent event) {
        String searchKey = searchTextField.getText().trim();

        if (searchKey.equals("")) {
            resetTransactionTableView();
        } else {
            ObservableList<Transaction> matchingTransactionList = FXCollections.observableList(SearchUtils.search(transactionList, searchKey));
            transactionTableView.setItems(matchingTransactionList);
        }
    }

    private void resetTransactionTableView() {
        transactionTableView.setItems(transactionList);
    }
}
