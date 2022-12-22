package oodj.assignment.oopskylinecarrentalsystem.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Admin;

import java.io.IOException;

public class AdminMainController extends LabelledViewController {

    @FXML
    private Button logOutButton;
    @FXML
    private Button generateAnalysedReportButton;
    @FXML
    private Button manageBookingButton;
    @FXML
    private Button manageCarMenuButton;
    @FXML
    private Button recoverOrBlacklistAccountButton;
    @FXML
    private TextField usernameLabel;
    @FXML
    private Button viewTransactionRecordButton;
    @FXML
    private Text welcomeText;

    @FXML
    void onGenerateAnalysedReportButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.ADMIN.SUMMARY_REPORT);
    }

    @FXML
    void onManageBookingButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.ADMIN.MANAGE_BOOKING);
    }

    @FXML
    void onManageCarMenuButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.ADMIN.MANAGE_CAR_MENU);
    }

    @FXML
    void onViewTransactionRecordButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.ADMIN.TRANSACTION_RECORD);
    }

    @FXML
    private void onLogOutButtonClick(ActionEvent event) throws IOException {
        switchSharedScene(event, FILEPATH.SHARED.LOGIN);
    }

    public void setLabelData() {
        Admin admin = (Admin) getUser();

        welcomeText.setText(String.format("Welcome,  %s", admin.getName()));
        usernameLabel.setText(admin.getUsername());
    }

}
