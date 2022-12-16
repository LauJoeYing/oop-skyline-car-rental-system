package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import oodj.assignment.oopskylinecarrentalsystem.config.CarConfig;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.CommonViewController;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.LabelledController;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static oodj.assignment.oopskylinecarrentalsystem.config.ImageConfig.ImgAssign;

public class CarMenuController extends CommonViewController implements Initializable {

    private List<Car> carList = CarConfig.getCarList();

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
    private Button previousPageButton;
    @FXML
    private TextField priceTextField1;
    @FXML
    private TextField priceTextField2;
    @FXML
    private TextField priceTextField3;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField seaterTextField1;
    @FXML
    private TextField seaterTextField2;
    @FXML
    private TextField seaterTextField3;
    @FXML
    private ComboBox<String> sortComboBox;
    @FXML
    private TextField transmissionTypeTextField1;
    @FXML
    private TextField transmissionTypeTextField2;
    @FXML
    private TextField transmissionTypeTextField3;
    @FXML
    private TextField typeTextField1;
    @FXML
    private TextField typeTextField2;
    @FXML
    private TextField typeTextField3;

    ArrayList<Car> carListcol1 = new ArrayList<>();
    ArrayList<Car> carListcol2 = new ArrayList<>();
    ArrayList<Car> carListcol3 = new ArrayList<>();
    int cnt;

    static String carNameLabel;
    static String carPriceLabel;
    static String carTransLabel;
    static String carTypeLabel;
    static String carImageSource;
    static Car currentCar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnt = 0;
        previousPageButton.setVisible(false);
        sortComboBox.getItems().addAll(
                "Sort by Price (High-Low)",
                "Sort by Price (Low-High)",
                "Sort by Brand Model"
        );
        sortComboBox.setValue("Sort by Brand Model");
        sortComboBox.setOnAction((event) -> {
            switch (sortComboBox.getValue()) {
                case "Sort by Price (High-Low)" -> carList.sort((car1, car2) -> Float.compare(car2.getDailyRate(), car1.getDailyRate()));
                case "Sort by Price (Low-High)" -> carList.sort((car1, car2) -> Float.compare(car1.getDailyRate(), car2.getDailyRate()));
                case "Sort by Brand Model" -> carList.sort(Comparator.comparing(Car::getBrand));
            }
            initCar();
            setData();
        });

        initCar();
        setData();
    }

    private void setData() {
        setCar(carListcol1, carImage1, carLabel1, priceTextField1, transmissionTypeTextField1, typeTextField1, seaterTextField1);
        setCar(carListcol2, carImage2, carLabel2, priceTextField2, transmissionTypeTextField2, typeTextField2, seaterTextField2);
        setCar(carListcol3, carImage3, carLabel3, priceTextField3, transmissionTypeTextField3, typeTextField3, seaterTextField3);
    }

    private void setCar(ArrayList<Car> carListcol1, ImageView carImage1, Text carLabel1, TextField priceTextField1, TextField transmissionTypeTextField1, TextField typeTextField1, TextField seaterTextField1) {
        Image myImagecol1 = new Image(ImgAssign(carListcol1.get(cnt).getModel()));
        carImage1.setImage(myImagecol1);
        carLabel1.setText(carListcol1.get(cnt).getBrand()+" "+ carListcol1.get(cnt).getModel());
        priceTextField1.setText("RM "+ carListcol1.get(cnt).getDailyRate());
        transmissionTypeTextField1.setText(carListcol1.get(cnt).getTransmissionType());
        typeTextField1.setText(carListcol1.get(cnt).getType());
        seaterTextField1.setText("5");
    }

    @FXML
    void onNextPageButtonClick(ActionEvent event) {
        cnt++;
        if(cnt == 5){
            previousPageButton.setVisible(true);
            nextPageButton.setVisible(false);
        }
        else if(cnt < 5){
            previousPageButton.setVisible(true);
            nextPageButton.setVisible(true);
        }

        setData();
    }

    @FXML
    void onReserveNowButton1Click(ActionEvent event) throws IOException {
        rentCar(event, carListcol1);
    }

    @FXML
    void onReserveNowButton2Click(ActionEvent event) throws IOException {
        rentCar(event, carListcol2);
    }

    @FXML
    void onReserveNowButton3Click(ActionEvent event) throws IOException {
        rentCar(event, carListcol3);
    }

    @FXML
    private void onSearchTextFieldKeyTyped() {
        String searchKey = searchTextField.getText();
        if (searchKey.length() == 0) {
            carList = CarConfig.getCarList();
        } else {
            carList = CarConfig.searchCar(searchKey);
        }
        initCar();
        setData();
    }

    private void rentCar(ActionEvent event, ArrayList<Car> carList) throws IOException {
        currentCar = carList.get(cnt);
        carImageSource = ImgAssign(carList.get(cnt).getModel());
        carNameLabel = carList.get(cnt).getBrand()+" "+ carList.get(cnt).getModel();
        carPriceLabel = String.format("RM %.2f", carListcol3.get(cnt).getDailyRate());
        carTransLabel = carList.get(cnt).getTransmissionType();
        carTypeLabel = carList.get(cnt).getType();
        LabelledController mainController = (LabelledController) switchUserSceneWithObject(event, "RentCar", currentCar);
        mainController.setLabelData();
    }

    @FXML
    void onPreviousPageButtonClick(ActionEvent event) {
        cnt--;
        if(cnt == 0){
            previousPageButton.setVisible(false);
            nextPageButton.setVisible(true);
        }
        else if(cnt > 0){
            previousPageButton.setVisible(true);
            nextPageButton.setVisible(true);
        }

        setData();
    }

    @FXML
    void onCrossButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, "Main");
    }
    void initCar() {
        cnt = 0;
        carListcol1 = new ArrayList<>();
        carListcol2 = new ArrayList<>();
        carListcol3 = new ArrayList<>();
        carList.sort(Comparator.comparing(Car::getBrand));
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


}