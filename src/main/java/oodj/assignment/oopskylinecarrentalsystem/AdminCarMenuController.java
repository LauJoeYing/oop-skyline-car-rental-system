package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminCarMenuController implements Initializable {


    @FXML
    private Button addCarButton;

    @FXML
    private ImageView carImage1;

    @FXML
    private ImageView carImage2;

    @FXML
    private ImageView carImage3;

    @FXML
    private Label carLabel1;

    @FXML
    private Label carLabel2;

    @FXML
    private Label carLabel3;

    @FXML
    private Button editButton1;

    @FXML
    private Button editButton2;

    @FXML
    private Button editButton3;

    @FXML
    private ComboBox<String> filterChoice;

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<String> sortbyChoice;

    //
    //
    //
    private Stage stage;
    private Scene scene;
    private Parent root;

    int count = 0;
    ArrayList<String> carImageSource = new ArrayList<String>(3);

    private ArrayList<String> getThreeImage(int cnt){

        //READ LINES OF cnt + 1, cnt + 2, and cnt + 3
        //APPEND IMAGE SOURCE INTO carImageSource ArrayList with reference to MODEL NAME, SOURCES ARE ENUMURATED


        return carImageSource;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //CALL READ FILE FUNCTION
            //WRITE LINES INTO LIST
        //CALL GET IMAGE FUNCTION

        //Images Load
        carImageSource = getThreeImage(count);
        Image myImage1 = new Image(carImageSource.get(0));
        carImage1.setImage(myImage1);
        Image myImage2 = new Image(carImageSource.get(1));
        carImage2.setImage(myImage2);
        Image myImage3 = new Image(carImageSource.get(2));
        carImage3.setImage(myImage3);
        //

        //Label Load


        //

        //Car Details Load Table
            //Table View
        //

    }

    @FXML
    void nextThreeCars(ActionEvent event) {
        //Images Load
        carImageSource = getThreeImage(count);
        Image myImage1 = new Image(carImageSource.get(0));
        carImage1.setImage(myImage1);
        Image myImage2 = new Image(carImageSource.get(1));
        carImage2.setImage(myImage2);
        Image myImage3 = new Image(carImageSource.get(2));
        carImage3.setImage(myImage3);
        //

        //Label Load


        //

        //Car Details Load Table
            //Table View
        //

    }

    @FXML
    void AddCar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Add_Car.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ToAdminMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main_Admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
