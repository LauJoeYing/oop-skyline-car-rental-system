package oodj.assignment.oopskylinecarrentalsystem.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import oodj.assignment.oopskylinecarrentalsystem.util.CarUtils;
import oodj.assignment.oopskylinecarrentalsystem.constant.FILEPATH;
import oodj.assignment.oopskylinecarrentalsystem.controller.shared.CommonViewController;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;
import oodj.assignment.oopskylinecarrentalsystem.util.SearchUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageCarMenuController extends CommonViewController implements Initializable {
    private ObservableList<Car> carList = FXCollections.observableList(CarUtils.getCarList());

    @FXML
    private Button addNewCarButton;
    @FXML
    private TableColumn<Car, String> brandColumn;
    @FXML
    private TableColumn<Car, String> carIdColumn;
    @FXML
    private TableView<Car> carTableView;
    @FXML
    private TableColumn<Car, Float> dailyRateColumn;
    @FXML
    private Button homeButton;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private Button modifyCarButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableColumn<Car, String> transmissionTypeColumn;
    @FXML
    private TableColumn<Car, String> typeColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {           //Initialization of UI page to feed values to table
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        transmissionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transmissionType"));
        dailyRateColumn.setCellValueFactory(new PropertyValueFactory<>("dailyRate"));
        dailyRateColumn.setCellFactory(dailyRateCell -> new TableCell<>() {
            @Override
            protected void updateItem(Float dailyRate, boolean empty) {
                super.updateItem(dailyRate, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", dailyRate));
                }
            }
        });

        carTableView.setItems(carList);

        carTableView.setRowFactory(tv -> {
            TableRow<Car> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() > 1 && !row.isEmpty()) {
                    try {
                        modifyCar(mouseEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    void onAddNewCarButtonClick(ActionEvent event) throws IOException {
        switchUserScene(event, FILEPATH.ADMIN.ADD_CAR);
    }

    @FXML
    void onSearchTextFileKeyTyped(KeyEvent event) {
        searchCar();
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) throws IOException {
        switchLabelledUserScene(event, FILEPATH.USER_MAIN);
    }

    @FXML
    void onModifyCarButtonClick(ActionEvent event) throws IOException {
        modifyCar(event);
    }

    private void searchCar() {                     //Filter tables values according to search bar input
        String searchKey = searchTextField.getText().trim();

        if (searchKey.equals("")) {
            resetCarTableView();
        } else {
            ObservableList<Car> matchingCarList = FXCollections.observableList(SearchUtils.search(carList, searchKey));
            carTableView.setItems(matchingCarList);
        }
    }

    private void resetCarTableView() {
        carTableView.setItems(carList);
    }

    private void modifyCar(Event event) throws IOException {                  //Switch to edit car details page for selected car
        Car selectedCar = carTableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            switchLabelledUserSceneWithObject(event, FILEPATH.ADMIN.EDIT_CAR, selectedCar);
        }
    }
}
