package oodj.assignment.oopskylinecarrentalsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class AdminCarMenuController extends CustomerCarMenuController  {

    @FXML
    private TableColumn<Car, String> carBrandCol;

    @FXML
    private TableColumn<Car, String>carIDCol;

    @FXML
    private TableView<Car> carMenuTable;

    @FXML
    private TableColumn<Car, String> carModelCol;

    @FXML
    private TableColumn<Car, String> carPriceCol;

    @FXML
    private TableColumn<Car, String> carTransmissionCol;

    @FXML
    private TableColumn<Car, String> carTypeCol;

    @FXML
    private TextField searchInput;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ObservableList<Transaction> list = FXCollections.observableArrayList(
//                new Car();
//    );

        carList = initializeCar();
        carIDCol.setCellValueFactory(new PropertyValueFactory<Car, String>("Car ID"));
        carBrandCol.setCellValueFactory(new PropertyValueFactory<Car, String>("Car Brand"));
        carModelCol.setCellValueFactory(new PropertyValueFactory<Car, String>("Car Model"));
        carTypeCol.setCellValueFactory(new PropertyValueFactory<Car, String>("Car Type"));
        carTransmissionCol.setCellValueFactory(new PropertyValueFactory<Car, String>("Transmission"));
        carPriceCol.setCellValueFactory(new PropertyValueFactory<Car, String>("Rental Price (RM)"));

    }


    @FXML
    void listen(InputMethodEvent event) {

    }

    @FXML
    void toAddCar(ActionEvent event) {

    }

    @FXML
    void toAdminMain(ActionEvent event) {

    }

    @FXML
    void toModCar(ActionEvent event) {

    }

}

