package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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


    ArrayList<Car> carListcol1 = new ArrayList<>();
    ArrayList<Car> carListcol2 = new ArrayList<>();
    ArrayList<Car> carListcol3 = new ArrayList<>();
    int cnt, cnt1, cnt2, cnt3;
    int placeholder;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnt = 0;
        prevPageButton.setVisible(false);
        initCar();
        String [] sortChoice = {"Sort by Price (High-Low)", "Sort by Price (Low-High)", "Sort by Brand Model"};
        sortCombo.getItems().addAll(sortChoice);
        sortCombo.setPromptText("Sort by Brand Model"); //Default sort


        Image myImagecol1 = new Image(ImgAssign(carListcol1.get(cnt).getCarModel()));
        carImage1.setImage(myImagecol1);
        carLabel1.setText(carListcol1.get(cnt).getCarBrand()+" "+carListcol1.get(cnt).getCarModel());
        priceLabel1.setText("RM "+ carListcol1.get(cnt).getCarRentalPrice());
        transLabel1.setText(carListcol1.get(cnt).getCarTransmission());
        typeLabel1.setText(carListcol1.get(cnt).getCarType());
        seaterLabel1.setText("5");


        Image myImagecol2 = new Image(ImgAssign(carListcol2.get(cnt).getCarModel()));
        carImage2.setImage(myImagecol2);
        carLabel2.setText(carListcol2.get(cnt).getCarBrand()+" "+carListcol2.get(cnt).getCarModel());
        priceLabel2.setText("RM "+ carListcol2.get(cnt).getCarRentalPrice());
        transLabel2.setText(carListcol2.get(cnt).getCarTransmission());
        typeLabel2.setText(carListcol2.get(cnt).getCarType());
        seaterLabel2.setText("5");
//
        Image myImagecol3 = new Image(ImgAssign(carListcol3.get(cnt).getCarModel()));
        carImage3.setImage(myImagecol3);
        carLabel3.setText(carListcol3.get(cnt).getCarBrand()+" "+carListcol3.get(cnt).getCarModel());
        priceLabel3.setText("RM "+ carListcol3.get(cnt).getCarRentalPrice());
        transLabel3.setText(carListcol3.get(cnt).getCarTransmission());
        typeLabel3.setText(carListcol3.get(cnt).getCarType());
        seaterLabel3.setText("5");



    }


    @FXML
    void nextCarPage(ActionEvent event) {
        cnt++;
        if(cnt == 5){
            prevPageButton.setVisible(true);
            nextPageButton.setVisible(false);
        }
        else if(cnt < 5){
            prevPageButton.setVisible(true);
            nextPageButton.setVisible(true);
        }

        Image myImagecol1 = new Image(ImgAssign(carListcol1.get(cnt).getCarModel()));
        carImage1.setImage(myImagecol1);
        carLabel1.setText(carListcol1.get(cnt).getCarBrand()+" "+carListcol1.get(cnt).getCarModel());
        priceLabel1.setText("RM "+ carListcol1.get(cnt).getCarRentalPrice());
        transLabel1.setText(carListcol1.get(cnt).getCarTransmission());
        typeLabel1.setText(carListcol1.get(cnt).getCarType());
        seaterLabel1.setText("5");


        Image myImagecol2 = new Image(ImgAssign(carListcol2.get(cnt).getCarModel()));
        carImage2.setImage(myImagecol2);
        carLabel2.setText(carListcol2.get(cnt).getCarBrand()+" "+carListcol2.get(cnt).getCarModel());
        priceLabel2.setText("RM "+ carListcol2.get(cnt).getCarRentalPrice());
        transLabel2.setText(carListcol2.get(cnt).getCarTransmission());
        typeLabel2.setText(carListcol2.get(cnt).getCarType());
        seaterLabel2.setText("5");
//
        Image myImagecol3 = new Image(ImgAssign(carListcol3.get(cnt).getCarModel()));
        carImage3.setImage(myImagecol3);
        carLabel3.setText(carListcol3.get(cnt).getCarBrand()+" "+carListcol3.get(cnt).getCarModel());
        priceLabel3.setText("RM "+ carListcol3.get(cnt).getCarRentalPrice());
        transLabel3.setText(carListcol3.get(cnt).getCarTransmission());
        typeLabel3.setText(carListcol3.get(cnt).getCarType());
        seaterLabel3.setText("5");
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
        cnt--;
        if(cnt == 0){
            prevPageButton.setVisible(false);
            nextPageButton.setVisible(true);
        }
        else if(cnt > 0){
            prevPageButton.setVisible(true);
            nextPageButton.setVisible(true);
        }

        Image myImagecol1 = new Image(ImgAssign(carListcol1.get(cnt).getCarModel()));
        carImage1.setImage(myImagecol1);
        carLabel1.setText(carListcol1.get(cnt).getCarBrand()+" "+carListcol1.get(cnt).getCarModel());
        priceLabel1.setText("RM "+ carListcol1.get(cnt).getCarRentalPrice());
        transLabel1.setText(carListcol1.get(cnt).getCarTransmission());
        typeLabel1.setText(carListcol1.get(cnt).getCarType());
        seaterLabel1.setText("5");


        Image myImagecol2 = new Image(ImgAssign(carListcol2.get(cnt).getCarModel()));
        carImage2.setImage(myImagecol2);
        carLabel2.setText(carListcol2.get(cnt).getCarBrand()+" "+carListcol2.get(cnt).getCarModel());
        priceLabel2.setText("RM "+ carListcol2.get(cnt).getCarRentalPrice());
        transLabel2.setText(carListcol2.get(cnt).getCarTransmission());
        typeLabel2.setText(carListcol2.get(cnt).getCarType());
        seaterLabel2.setText("5");
//
        Image myImagecol3 = new Image(ImgAssign(carListcol3.get(cnt).getCarModel()));
        carImage3.setImage(myImagecol3);
        carLabel3.setText(carListcol3.get(cnt).getCarBrand()+" "+carListcol3.get(cnt).getCarModel());
        priceLabel3.setText("RM "+ carListcol3.get(cnt).getCarRentalPrice());
        transLabel3.setText(carListcol3.get(cnt).getCarTransmission());
        typeLabel3.setText(carListcol3.get(cnt).getCarType());
        seaterLabel3.setText("5");
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

    void initCar() {
        ArrayList<Car> carList = new ArrayList<>();
        cnt = 0;
        try (Stream<String> stream = Files.lines(Path.of(FilePath.CAR.getDataFile()))) {
            stream.parallel().forEach(car -> {
                String[] carData = car.split(" \\| ");
                carList.add(new Car(carData));
            });

        }
        catch (IOException e){

        }
        carList.sort(Comparator.comparing(Car::getCarBrand));

        carList.forEach(car -> {
            cnt++;
            cnt = cnt%3;
            if(cnt == 1){
                carListcol1.add(car);
            }
            else if (cnt == 2){
                carListcol2.add(car);
            }
            else if (cnt == 0){
                carListcol3.add(car);
            }
        });

    }

    String ImgAssign(String model){
        switch(model){
            case "Camry 2019":
                return ImagePath.CAMRY.getImageSource();
            case "Myvi 2020":
                return ImagePath.MYVI.getImageSource();
            case "Bezza 2022":
                return ImagePath.BEZZA.getImageSource();
            case "Civic 2021":
                return ImagePath.CIVIC.getImageSource();
            case "CR-V 2019":
                return ImagePath.CRV.getImageSource();
            default:
                return null;
        }
    }


    /**
     * SORT BY OTHER STUFF
     */

}
