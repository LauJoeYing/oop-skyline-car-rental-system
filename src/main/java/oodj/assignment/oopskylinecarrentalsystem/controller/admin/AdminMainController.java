package oodj.assignment.oopskylinecarrentalsystem.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Admin;

import java.io.IOException;

public class AdminMainController extends LabelledViewController {

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
    void onGenerateAnalysedReportButtonClick(ActionEvent event) {

    }

    @FXML
    void onManageBookingButtonClick(ActionEvent event) {

    }

    @FXML
    void onManageCarMenuButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, "ManageCarMenu");
    }

    @FXML
    void onRecoverOrBlacklistAccountButtonClick(ActionEvent event) {

    }

    @FXML
    void onViewTransactionRecordButtonClick(ActionEvent event) {

    }

    public void setLabelData() {
        Admin admin = (Admin) getUser();

        welcomeText.setText(String.format("Welcome,  %s", admin.getName()));
        usernameLabel.setText(admin.getUsername());
    }

}
