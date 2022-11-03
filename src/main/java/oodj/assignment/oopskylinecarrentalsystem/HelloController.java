package oodj.assignment.oopskylinecarrentalsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField loginInputUsername;
    @FXML
    private TextField LoginInputPassword;
    @FXML
    private Label menuWelcomeText;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void menuWelcome() {
        menuWelcomeText.setText("Hello " +loginInputUsername);
    }

    public void switchlogin_custMenu() throws IOException {
        ViewSwitcher.switchTo(View.CUST_MAIN);
    }

}
