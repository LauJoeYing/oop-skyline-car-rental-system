package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;

import java.io.IOException;

public class CustomerMainController extends LabelledViewController {

    @FXML
    private Button editPersonalDetailsButton;
    @FXML
    private Button modifyPasswordButton;
    @FXML
    private Button rentCarButton;
    @FXML
    private Button topUpButton;
    @FXML
    private TextField usernameLabel;
    @FXML
    private Button viewApprovalButton;
    @FXML
    private Button viewBookingHistoryButton;
    @FXML
    private Text welcomeText;
    @FXML
    private TextField accountBalanceTextField;

    @FXML
    void onEditPersonalDetailsButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, "EditAccountDetails");
    }

    @FXML
    void onModifyPasswordButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, "EditPassword");
    }

    @FXML
    void onRentCarButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, "CarMenu");
    }

    @FXML
    void onTopUpButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, "TopUp");
    }

    @FXML
    void onViewBookingHistoryButtonClick(ActionEvent event) {

    }

    public void setLabelData() {
        Customer customer = (Customer) getUser();

        welcomeText.setText(String.format("Welcome,  %s", customer.getName()));
        usernameLabel.setText(customer.getUsername());
        accountBalanceTextField.setText(String.format("RM %.2f", customer.getAccountBalance()));
    }

}
