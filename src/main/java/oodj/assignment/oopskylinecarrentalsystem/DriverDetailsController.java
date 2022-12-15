package oodj.assignment.oopskylinecarrentalsystem;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Stream;


import static oodj.assignment.oopskylinecarrentalsystem.CustomerCarMenuController.*;

import static oodj.assignment.oopskylinecarrentalsystem.BookNowController.*;

import static oodj.assignment.oopskylinecarrentalsystem.CustomerMainController.*;

import static oodj.assignment.oopskylinecarrentalsystem.LoginController.currentUser;


public class DriverDetailsController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView carImage;

    @FXML
    private Text carLabel;

    @FXML
    private TextField priceLabel;

    @FXML
    private TextField transLabel;

    @FXML
    private TextField typeLabel;

    @FXML
    private DatePicker DOB;

    @FXML
    private Button licenseButton;

    @FXML
    private Button icButton;

    @FXML
    private Label currentBalLabel;

    @FXML
    private Label driverNameLabel;

    @FXML
    private Label totalPriceLabel;

    static int subtotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Image myImage = new Image(carImageSource);
        carImage.setImage(myImage);
        carLabel.setText(carNameLabel);
        priceLabel.setText("RM "+carPriceLabel);
        transLabel.setText(carTransLabel);
        typeLabel.setText(carTypeLabel);

        driverNameLabel.setText(currentUser.getName());
        currentBalLabel.setText(currentUserWallet.getBalance());
        subtotal = Integer.parseInt(carPriceLabel) *  Integer.parseInt(duration);
        totalPriceLabel.setText(Integer.toString(subtotal));
    }

    @FXML
    void confirmBooking(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void toCustMain(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void licensePicker(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img"));
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            licenseButton.setText(file.getAbsolutePath());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FILE I/O ERROR");
            alert.setHeaderText("File Not Found");
            alert.setContentText("Please Ensure the File Path Exists");
            alert.show();
        }
    }

}


//      FileChooser fileChooser = new FileChooser();
//      fileChooser.setTitle("Open Resource File");
//      fileChooser.showOpenDialog(stage);