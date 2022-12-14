package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Stream;
public class CustomerCarMenuController implements Initializable {

    @FXML
    private ImageView carImage1;

    @FXML
    private ImageView carImage2;

    @FXML
    private ImageView carImage3;

    @FXML
    private Text carLabel1;

    @FXML
    private Text carLabel2;

    @FXML
    private Text carLabel3;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button prevPageButton;

    @FXML
    private TextField priceLabel1;

    @FXML
    private TextField priceLabel2;

    @FXML
    private TextField priceLabel3;

    @FXML
    private TextField searchField;

    @FXML
    private TextField seaterLabel1;

    @FXML
    private TextField seaterLabel2;

    @FXML
    private TextField seaterLabel3;

    @FXML
    private ComboBox<String> sortCombo;

    @FXML
    private TextField transLabel1;

    @FXML
    private TextField transLabel2;

    @FXML
    private TextField transLabel3;

    @FXML
    private TextField typeLabel1;

    @FXML
    private TextField typeLabel2;

    @FXML
    private TextField typeLabel3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        String [] sortChoice = {"Sort by Price (High-Low)", "Sort by Price (Low-High)", "Sort by Brand Model"};
        sortCombo.getItems().addAll(sortChoice);
        sortCombo.setPromptText("Sort by Brand Model"); //Default sort

//        carImage1.setImage();
//        carLabel1.setText();
//        priceLabel1.setText();
//        transLabel1.setText();
//        typeLabel1.setText();
//        seaterLabel1.setText();
//
//        carImage1.setImage();
//        carLabel1.setText();
//        priceLabel1.setText();
//        transLabel1.setText();
//        typeLabel1.setText();
//        seaterLabel1.setText();
//
//        carImage1.setImage();
//        carLabel1.setText();
//        priceLabel1.setText();
//        transLabel1.setText();
//        typeLabel1.setText();
//        seaterLabel1.setText();



    }


    @FXML
    void nextCarPage(ActionEvent event) {

    }

    @FXML
    void onRentClick1(ActionEvent event) {

    }

    @FXML
    void onRentClick2(ActionEvent event) {

    }

    @FXML
    void onRentClick3(ActionEvent event) {

    }

    @FXML
    void onSearch(ActionEvent event) {

    }

    @FXML
    void prevCarPage(ActionEvent event) {

    }

    @FXML
    void toCustMain(ActionEvent event) {

    }

    int numJump(int val) {
        if (val == 0) {
            return 1;
        } else if (val == 1) {
            return 2;
        } else{
            return 0;
        }
    }

    void initCar() throws IOException {
        ArrayList<Car> carList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Path.of(FilePath.CAR.getDataFile()))) {
            stream.parallel().forEach(car -> {
                String[] carData = car.split(" \\| ");
                carList.add(new Car(carData));
            });

        }
        carList.sort(Comparator.comparing(Car::getCarBrand));
        carList.forEach(System.out::println);

    }
}
