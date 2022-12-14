package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.CustomerCarMenuController.carNameLabel;
import static oodj.assignment.oopskylinecarrentalsystem.CustomerCarMenuController.carTypeLabel;
import static oodj.assignment.oopskylinecarrentalsystem.CustomerCarMenuController.carImageSource;
import static oodj.assignment.oopskylinecarrentalsystem.CustomerCarMenuController.carTransLabel;
import static oodj.assignment.oopskylinecarrentalsystem.CustomerCarMenuController.carPriceLabel;
public class BookNowController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    static String pickupDate, returnDate, pickupTime;

    @FXML
    private ImageView carImage;

    @FXML
    private Text carLabel;

    @FXML
    private DatePicker pickupDatePicker;

    @FXML
    private ComboBox<String> pickupTimeCombo;

    @FXML
    private TextField priceLabel;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TextField transLabel;

    @FXML
    private TextField typeLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Image myImage = new Image(carImageSource);
        carImage.setImage(myImage);
        carLabel.setText(carNameLabel);
        priceLabel.setText(carPriceLabel);
        transLabel.setText(carTransLabel);
        typeLabel.setText(carTypeLabel);

        String [] timeChoice = {"8:00","10:00","13:00","15:00","17:00","19:00"};
        pickupTimeCombo.getItems().addAll(timeChoice);


    }


    @FXML
    void toCustMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void checkout(ActionEvent event) throws IOException{
        LocalDate rawPickupDate = pickupDatePicker.getValue();
        LocalDate rawReturnDate = returnDatePicker.getValue();
        pickupDate = rawPickupDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        returnDate = rawReturnDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        pickupTime = pickupTimeCombo.getSelectionModel().getSelectedItem().toString();

        Parent root = FXMLLoader.load(getClass().getResource("DriverDetails.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}

