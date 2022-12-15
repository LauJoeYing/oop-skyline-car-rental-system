package oodj.assignment.oopskylinecarrentalsystem.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oodj.assignment.oopskylinecarrentalsystem.config.FilePath;
import oodj.assignment.oopskylinecarrentalsystem.model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static oodj.assignment.oopskylinecarrentalsystem.controller.shared.LoginController.currentUser;


public class ChangePassword {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField newPassInput;

    @FXML
    private TextField oldPassInput;

    @FXML
    void chgConfirmAlerts (ActionEvent event) throws IOException {
        if(currentUser.getPassword().equals(oldPassInput.getText())){
            //DO NEXT
        }
        else{
            popupERROR();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Change Password");
        alert.setContentText("Are you sure you want to make this change?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            //VALIDATION
            int placeholder = passInputValidation(newPassInput.getText());
            if (placeholder == 1) {
                //WRITE TO FILE
                ArrayList<User> userList = new ArrayList<>();
                StringBuffer newUser = new StringBuffer();
                try (Stream<String> stream = Files.lines(Path.of(FilePath.USER.getDataFile()))) {
                    stream.parallel().forEach(user -> {
                        String[] userData = user.split(" \\| ");
                        userList.add(new User(userData));
                    });
                    userList.stream().parallel().forEach(user -> {
                        if (currentUser.getUsername().equals(user.getUsername())) {
                            user.setPassword(newPassInput.getText());
                            String userLineTemp = user.getUserType() + " | " + user.getUsername() + " | " + user.getPassword() + " | " + user.getName() + " | " + user.getGender() + " | " + user.getPhoneNum() + " | " + user.getEmail() + " | " + user.getIc() + " | " + user.getUnitNum() + " | " + user.getStreetLine1() + " | " + user.getStreetLine2() + " | " + user.getPostcode() + " | " + user.getCity() + " | " + user.getState() + " | " + "\n";
                            newUser.append(userLineTemp);
                        } else {
                            String userLineTemp = user.getUserType() + " | " + user.getUsername() + " | " + user.getPassword() + " | " + user.getName() + " | " + user.getGender() + " | " + user.getPhoneNum() + " | " + user.getEmail() + " | " + user.getIc() + " | " + user.getUnitNum() + " | " + user.getStreetLine1() + " | " + user.getStreetLine2() + " | " + user.getPostcode() + " | " + user.getCity() + " | " + user.getState() + " | " + "\n";
                            newUser.append(userLineTemp);
                        }
                    });
                    String inputStr = newUser.toString();
                    FileOutputStream fileOut = new FileOutputStream(FilePath.USER.getDataFile());
                    fileOut.write(inputStr.getBytes());
                    fileOut.close();
                }
            } else {
                popupAnotherERROR();

            }
        }

        else if (alert.showAndWait().get() == ButtonType.CANCEL) {
        }
    }

    @FXML
    void toCustMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    int passInputValidation(String input) {
        String passwordInput;
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,32}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher;

        passwordInput = input;
        passwordMatcher = passwordPattern.matcher(passwordInput);
        if (passwordMatcher.matches()) {
            System.out.println("YES");
            return 1;
        }
        else{
            System.out.println("NO");
            return 0;
        }
    }

    public void popupERROR() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("INVALID CREDENTIALS");
        alert.setContentText("PLEASE TRY AGAIN!");
        alert.showAndWait();
    }

    public void popupAnotherERROR() throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("INVALID NEW PASSWORD FORMAT");
        alert.setContentText("PLEASE TRY AGAIN!");
        alert.showAndWait();
    }
}

