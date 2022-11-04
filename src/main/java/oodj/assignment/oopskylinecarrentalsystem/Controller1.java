package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller1 implements Initializable {

    @FXML
    private TextField loginInputUsername;
    @FXML
    private TextField LoginInputPassword;
    @FXML
    private Label menuWelcomeText;
    @FXML
    private ComboBox carMenuFilter;




    ///////
    public static Label custMenuWelcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void menuWelcome() {
        String temp = loginInputUsername.getText();
        custMenuWelcomeText.setText("Hello " +temp);
    }

//    public void switchlogin_custMenu(ActionEvent event) throws IOException {
//        custMenuWelcomeText=menuWelcomeText;
//        ViewSwitcher.switchTo(View.CUST_MAIN);
//        menuWelcomeText.setText();
//    }

}
