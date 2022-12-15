package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.LoginController.currentUser;

public class AdminMainController implements Initializable {

    @FXML
    private Label menuWelcomeText;

    @FXML
    private ImageView profimg;

    @FXML
    private Text usernameText;

    private String tempString;

    public String getMenuWelcomeText() {
        tempString = "Welcome, "+currentUser.getName();
        return tempString;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        menuWelcomeText.setText(getMenuWelcomeText());
        Image myImage = new Image(ImagePath.PROFILE.getImageSource());
        profimg.setImage(myImage);
        usernameText.setText(currentUser.getUsername());
    }

    @FXML
    void genReport(ActionEvent event) {

    }

    @FXML
    void manageBooking(ActionEvent event) {

    }

    @FXML
    void manageCarMenu(ActionEvent event) {

    }

    @FXML
    void modCustAccount(ActionEvent event) {

    }

    @FXML
    void viewTXRecord(ActionEvent event) {

    }

}

