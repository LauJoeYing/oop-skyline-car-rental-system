package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;

import java.io.IOException;

public class CustomerMainController extends LabelledViewController {

    @FXML
    private Button logOutButton;
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
        switchLabelledUserScene(event, FILEPATH.CUSTOMER.EDIT_ACCOUNT_DETAILS);
    }

    @FXML
    void onModifyPasswordButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.CUSTOMER.EDIT_PASSWORD);
    }

    @FXML
    void onRentCarButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.CUSTOMER.CAR_MENU);
    }

    @FXML
    void onTopUpButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.CUSTOMER.TOP_UP);
    }

    @FXML
    void onViewBookingHistoryButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.CUSTOMER.VIEW_BOOKING_HISTORY);
    }

    @FXML
    void onLogOutButtonClick(ActionEvent event) throws IOException {
        switchSharedScene(event, FILEPATH.SHARED.LOGIN);
    }

    public void setLabelData() {
        Customer customer = (Customer) getUser();

        welcomeText.setText(String.format("Welcome,  %s", customer.getName()));
        usernameLabel.setText(customer.getUsername());
        accountBalanceTextField.setText(String.format("RM %.2f", customer.getAccountBalance()));
    }

}
