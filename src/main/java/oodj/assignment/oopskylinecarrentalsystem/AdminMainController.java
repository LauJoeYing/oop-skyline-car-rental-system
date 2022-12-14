package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable {

    @FXML
    private Label menuWelcomeText;

    @FXML
    private ImageView profimg;

    private String tempString;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        menuWelcomeText.setText(getMenuWelcomeText());
        Image myImage = new Image("https://i.imgur.com/q79wXSH.jpeg");
        profimg.setImage(myImage);
    }

    public String getMenuWelcomeText() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\com\\example\\demo\\TestFile.txt")); /**FOR SOME REASON CANNOT USE SHORT PATH*/
            tempString = "Welcome, "+br.readLine();
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return tempString;
    }



    @FXML
    void ToAdminBookingList(ActionEvent event) {

    }

    @FXML
    void ToAdminCarMenu(ActionEvent event) {

    }

    @FXML
    void ToAnalyzedReport(ActionEvent event) {

    }

    @FXML
    void ToLogin(ActionEvent event) {

    }

    @FXML
    void ToSettings(ActionEvent event) {

    }

}

