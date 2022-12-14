package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static oodj.assignment.oopskylinecarrentalsystem.LoginController.currentUser;

public class CustomerMainController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label menuWelcomeText;

    @FXML
    private ImageView profimg;

    @FXML
    private TextField nowAmount;

    @FXML
    private Label usernameText;

    private String tempString;

    static Wallet currentUserWallet;
    public String getMenuWelcomeText() {
        tempString = "Welcome, "+currentUser.getName();
        return tempString;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        menuWelcomeText.setText(getMenuWelcomeText());
        Image myImage = new Image(ImagePath.PROFILE.getImageSource());
        profimg.setImage(myImage);
        userWalletInit();
        Double tempBal = Double.parseDouble(currentUserWallet.getBalance());
        String tempAmount = String.format("%.2f", tempBal);
        nowAmount.setText(tempAmount);
        usernameText.setText(currentUser.getUsername());
    }


    @FXML
    void ToBookHist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BookingHistory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ToCarMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CarMenuCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ToChgPass(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChgPass.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ToTXHist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TransactionRecord.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void Topup(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Topup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    void userWalletInit() {
        ArrayList<Wallet> userWalletList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Path.of(FilePath.WALLET.getDataFile()))) {
            stream.parallel().forEach(userWallet -> {
                String[] userWalletData = userWallet.split(" \\| ");
                userWalletList.add(new Wallet(userWalletData));
            });
            userWalletList.stream().parallel().forEach(userWallet -> {
                if (currentUser.getUsername().equals(userWallet.getUsername())) {
                    this.currentUserWallet = userWallet;
                }
            });
        } catch (IOException e) {
        }
    }
}
