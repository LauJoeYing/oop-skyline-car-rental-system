package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class GetTesterController implements Initializable {

    @FXML
    private Label nowAmount;

    @FXML
    private TextField topupAmount;


    double placeholder = 300.00;

    @FXML
    void OnTopupClick(ActionEvent event) {
        nowAmount.setText(Double.toString(placeholder));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        nowAmount.setText("400");
    }





}

