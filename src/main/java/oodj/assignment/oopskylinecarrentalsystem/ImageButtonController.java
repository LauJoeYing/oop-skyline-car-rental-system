package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButtonController {
    @FXML
    private Button button;

    @FXML
    private ImageView image;



    private final static String imagesource = "https://i.imgur.com/R22iSbs.jpeg";

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
        Image myImage = new Image(imagesource);
        image.setImage(myImage);
    }
}