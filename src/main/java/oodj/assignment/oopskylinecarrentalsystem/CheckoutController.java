package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.CustomerCarMenuController.*;

import static oodj.assignment.oopskylinecarrentalsystem.BookNowController.*;

import static oodj.assignment.oopskylinecarrentalsystem.CustomerMainController.*;

import static oodj.assignment.oopskylinecarrentalsystem.DriverDetailsController.subtotal;

import static oodj.assignment.oopskylinecarrentalsystem.LoginController.currentUser;
public class CheckoutController implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField payDuration;

    @FXML
    private TextField payEnd;

    @FXML
    private TextField payID;

    @FXML
    private TextField payModel;

    @FXML
    private TextField payStart;

    @FXML
    private TextField paypickup;

    @FXML
    private TextField priceDay;

    @FXML
    private TextField totalPrice;

    @FXML
    void CheckoutConfirm(ActionEvent event) throws IOException {
        int temp = Integer.parseInt(currentUserWallet.getBalance());
        if(temp >= subtotal){
            currentUserWallet.setBalance(Integer.toString(temp - subtotal));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation of Process");
            alert.setHeaderText("Payment Confirmation");
            alert.setContentText("Are you sure you want to make this payment?");

            if(alert.showAndWait().get() == ButtonType.OK){
                Parent root = FXMLLoader.load(getClass().getResource("Receipt.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            else if (alert.showAndWait().get() == ButtonType.CANCEL) {
                //WHAT TO DO
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("INSUFFICIENT CREDITS");
            alert.setContentText("PLEASE TOPUP");
            alert.showAndWait();


        }
    }

    @FXML
    void toCustMain(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        payDuration.setText(duration);
        payID.setText(currentCar.getCarId());
        payEnd.setText(returnDate);
        payStart.setText(pickupDate);
        paypickup.setText(pickupTime);
        payModel.setText(carNameLabel);
        priceDay.setText(carPriceLabel);
        totalPrice.setText(Integer.toString(subtotal));

    }

}
