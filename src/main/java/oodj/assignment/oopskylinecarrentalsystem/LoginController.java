package oodj.assignment.oopskylinecarrentalsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField LoginInputPass;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginInputUser;

    @FXML
    private Hyperlink toSignUp;


    public void loginCheck() {
        //Check in UserFile account details according to input
    }

    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    public TextField getLoginInputPass() {
        return LoginInputPass;
    }

    public void setLoginInputPass(TextField loginInputPass) {
        LoginInputPass = loginInputPass;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public TextField getLoginInputUser() {
        return loginInputUser;
    }

    public void setLoginInputUser(TextField loginInputUser) {
        this.loginInputUser = loginInputUser;
    }

    public Hyperlink getToSignUp() {
        return toSignUp;
    }

    public void setToSignUp(Hyperlink toSignUp) {
        this.toSignUp = toSignUp;
    }
}