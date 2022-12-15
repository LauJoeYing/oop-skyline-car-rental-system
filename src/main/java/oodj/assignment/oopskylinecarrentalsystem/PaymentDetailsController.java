package oodj.assignment.oopskylinecarrentalsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.LoginController.currentUser;

public class PaymentDetailsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableView<Transaction> PaymentTableView;
    @FXML
    private TableColumn<Transaction, String> CarBrandCol;

    @FXML
    private TableColumn<Transaction, String> CarIDCol;

    @FXML
    private TableColumn<Transaction, String>CarModelCol;

    @FXML
    private TableColumn<Transaction, String> DurationCol;

    @FXML
    private TableColumn<Transaction, String> EndDateCol;

    @FXML
    private TableColumn<Transaction, String> StartDateCol;

    @FXML
    private TableColumn<Transaction, String> TotalAmountCol;

    @FXML
    private TextField checkoutDateLabel;

    @FXML
    private TextField paymentTypeLabel;

    @FXML
    private TextField totalAmountLabel;

    @FXML
    private TextField userLabel;

    private Transaction currentRecord;
    ObservableList<Transaction> list = FXCollections.observableArrayList(
        new Transaction(currentRecord.getCarID(),currentRecord.getCarBrand(),currentRecord.getCarModel(), currentRecord.getStartDate(),currentRecord.getEndDate(),currentRecord.getDuration(),currentRecord.getTotal())
    );



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        userLabel.setText(currentUser.getUsername());
        paymentTypeLabel.setText("Online Payment");
        checkoutDateLabel.setText(currentRecord.getTxDate());
        totalAmountLabel.setText(currentRecord.getTotal());

        CarIDCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Car ID"));
        CarBrandCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Car Brand"));
        CarModelCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Car Model"));
        StartDateCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Start Date"));
        EndDateCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("End Date"));
        DurationCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Duration"));
        TotalAmountCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Total (RM)"));

        PaymentTableView.setItems(list);

    }

    @FXML
    void toCustMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void toTXRec(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("TransactionRecord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
