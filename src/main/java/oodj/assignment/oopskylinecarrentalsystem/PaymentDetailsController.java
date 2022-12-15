package oodj.assignment.oopskylinecarrentalsystem;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static oodj.assignment.oopskylinecarrentalsystem.BookNowController.*;
import static oodj.assignment.oopskylinecarrentalsystem.BookNowController.pickupTime;
import static oodj.assignment.oopskylinecarrentalsystem.CustomerCarMenuController.*;
import static oodj.assignment.oopskylinecarrentalsystem.DriverDetailsController.subtotal;

public class PaymentDetailsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<?, ?> CarBrandCol;

    @FXML
    private TableColumn<?, ?> CarIDCol;

    @FXML
    private TableColumn<?, ?> CarModelCol;

    @FXML
    private TableColumn<?, ?> DurationCol;

    @FXML
    private TableColumn<?, ?> EndDateCol;

    @FXML
    private TableView<?> PaymentTableView;

    @FXML
    private TableColumn<?, ?> StartDateCol;

    @FXML
    private TableColumn<?, ?> TotalAmountCol;

    @FXML
    private TextField checkoutDateLabel;

    @FXML
    private TextField paymentTypeLabel;

    @FXML
    private TextField totalAmountLabel;

    @FXML
    private TextField userLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){


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
