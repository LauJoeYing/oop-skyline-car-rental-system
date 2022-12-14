package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static oodj.assignment.oopskylinecarrentalsystem.CustomerMainController.currentUserWallet;
import static oodj.assignment.oopskylinecarrentalsystem.LoginController.currentUser;

public class TopupController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView TnG;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label nowAmount;

    @FXML
    private TextField topupAmount;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(ImagePath.TNG.getImageSource());
        TnG.setImage(image);
        Double tempBal = Double.parseDouble(currentUserWallet.getBalance());
        String tempAmount = String.format("%.2f", tempBal);
        nowAmount.setText(tempAmount);
    }


    @FXML
    void toMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void OnTopupClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Topup");
        alert.setHeaderText("Wallet Top Up Confirmation");
        alert.setContentText("Are you sure you want to top up?");
        if(alert.showAndWait().get() == ButtonType.OK){
            //FUNCTION AFTER CONFIRMATION'
        }
        else if (alert.showAndWait().get() == ButtonType.CANCEL) {
            Parent root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            return;
        }
        /**
         * OVERWRITE WALLET FILE WITH NEW VALUE
         */

        ArrayList<Wallet> userWalletList = new ArrayList<>();
        StringBuffer newWallet = new StringBuffer();
        try (Stream<String> stream = Files.lines(Path.of(FilePath.WALLET.getDataFile()))) {
            stream.parallel().forEach(userWallet -> {
                String[] userWalletData = userWallet.split(" \\| ");
                userWalletList.add(new Wallet(userWalletData));
            });
            userWalletList.stream().parallel().forEach(userWallet -> {
                if (currentUser.getUsername().equals(userWallet.getUsername())) {
                    double balanceTemp = Double.parseDouble(userWallet.getBalance());
                    balanceTemp = balanceTemp + Double.parseDouble(topupAmount.getText());
                    /**
                     *  CHECK FOR NEGATIVE VALUE INPUT FOR TOPUP AMOUNT
                     */
                    userWallet.setBalance(Double.toString(balanceTemp));
                    String walletLineTemp = userWallet.getUsername()+" | "+userWallet.getBalance()+"\n";
                    newWallet.append(walletLineTemp);
//                    this.currentUserWallet = userWallet;
                    currentUserWallet = userWallet;
                }
                else{
                    String walletLineTemp = userWallet.getUsername()+" | "+userWallet.getBalance()+"\n";
                    newWallet.append(walletLineTemp);
                }
            });
            String inputStr = newWallet.toString();
            FileOutputStream fileOut = new FileOutputStream(FilePath.WALLET.getDataFile());
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            Double tempBal = Double.parseDouble(currentUserWallet.getBalance());
            String tempAmount = String.format("%.2f", tempBal);
            nowAmount.setText(tempAmount);
            topupAmount.setText("");

        } catch (IOException e) {
        }
    }
}
