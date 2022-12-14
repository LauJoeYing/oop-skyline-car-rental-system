package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TestingController implements Initializable{

    @FXML
    private Label label;

    @FXML
    private Button OkButton;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        label.setText("Hello");
    }

    @FXML
    public void OKtoCloseWindow(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Confirmation of Process");
        alert.setHeaderText("Booking Confirmation");
        alert.setContentText("Are you sure you want to make this booking?");
        alert.show();
    }


    /**
     * Template of showing ERROR or CONFIRMATION POPUP
     */


    public void OKforCONFIRMATION() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation of Process");
        alert.setHeaderText("Booking Confirmation");
        alert.setContentText("Are you sure you want to make this booking?");

        if(alert.showAndWait().get() == ButtonType.OK){
            //FUNCTION AFTER CONFIRMATION
        }
        else if (alert.showAndWait().get() == ButtonType.CANCEL) {
            //WHAT TO DO
        }


    }

    public void popupERROR() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("INVALID CREDENTIALS");
        alert.setContentText("PLEASE TRY AGAIN!");
        alert.show();
    }


}
