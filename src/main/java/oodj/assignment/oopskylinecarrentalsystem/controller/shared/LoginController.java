package oodj.assignment.oopskylinecarrentalsystem.controller.shared;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.config.UserConfig;
import oodj.assignment.oopskylinecarrentalsystem.model.User;

import java.io.IOException;

public class LoginController extends CommonViewController {

    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Hyperlink signUpHyperlink;

    @FXML
    private Label warningLabel;

    @FXML
    void onLoginEnterKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            login(event);
        }
    }

    @FXML
    void onSignUpHyperlinkClick(ActionEvent event) throws IOException {
        switchSharedScene(event, "SignUp");
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        login(event);
    }

    private void login(Event event) throws IOException {
        String usernameInput = usernameTextField.getText();
        String passwordInput = passwordPasswordField.getText();

        if (usernameInput.equals("") && passwordInput.equals("")) {
            warningLabel.setText("Please enter your username and your password.");
        } else if (usernameInput.equals("")) {
            warningLabel.setText("Please enter your username.");
        } else if (passwordInput.equals("")) {
            warningLabel.setText("Please enter your password.");
        } else {
            setUser(UserConfig.login(usernameInput, passwordInput));
            User user = getUser();
            if (user != null) {
                LabelledController mainController = (LabelledController) switchUserScene(event, "Main");
                mainController.setLabelData();
            } else {
                warningLabel.setText("Incorrect username or password. Please try again!");
            }
        }
    }
}

