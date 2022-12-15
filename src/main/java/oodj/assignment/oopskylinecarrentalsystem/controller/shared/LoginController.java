package oodj.assignment.oopskylinecarrentalsystem.controller.shared;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import oodj.assignment.oopskylinecarrentalsystem.model.User;
import oodj.assignment.oopskylinecarrentalsystem.config.FilePath;
import oodj.assignment.oopskylinecarrentalsystem.model.Admin;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField loginInputPassword;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView imageLogo;

    @FXML
    private TextField loginInputUsername;

    private static String loginUserTemp, loginPassTemp;

    public static User currentUser;

    private int flagHolder;


    @FXML
    void loginButtonClick(ActionEvent event) throws IOException {
        loginUserTemp = loginInputUsername.getText();
        loginPassTemp = loginInputPassword.getText();
        System.out.println(loginUserTemp+" "+loginPassTemp);

        //CALL ACCOUNT DETAILS VALIDATION FUNCTION
        flagHolder = login();
        //IF VALIDATED CHANGE TO MAIN MENU
        if(flagHolder == 1) {
            Parent root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("INVALID CREDENTIALS");
            alert.setContentText("PLEASE TRY AGAIN!");
            alert.show();
        }
    }

    @FXML
    void signupButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sign_Up.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }



    public int login() {
        AtomicBoolean authenticated = new AtomicBoolean(false);
        ArrayList<User> userList = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(FilePath.USER.getDataFile()))) {
            AtomicBoolean parsingError = new AtomicBoolean(false);

            stream.parallel().forEach(user -> {
                String[] userData = user.split(" \\| ");
                switch (userData[0]) {
                    case "A" -> {
                        userList.add(new Admin(userData));
                    }
                    case "C" -> {
                        userList.add(new Customer(userData));
                    }
                    default -> {
                        System.out.printf("%nError parsing usertype (%s)! Please check your \"user.txt\".", userData[0]);
                        parsingError.set(true);
                    }
                }
            });

            if (!parsingError.get()) {
                System.out.println("\n-- Login Page --");

                while (!authenticated.get()) {

                    userList.stream().parallel().forEach(user -> {
                        if (loginUserTemp.equalsIgnoreCase(user.getUsername()) && loginPassTemp.equals(user.getPassword())) {
                            this.currentUser = user;
                            authenticated.set(true);
                        }
                    });

                    if (!authenticated.get()) {
                        System.out.println("\nWrong username or password. Please try again.");
                        break;
                    }

                    System.out.printf("\nWelcome back to Skyline Car Rental System, %s!", this.currentUser.getName());
                    return 1;
                };
            }

        } catch (IOException e) {
        }
        return 0;
    }





    public String getLoginUserTemp() {  //TO USE OUTSIDE CALL THIS FUNCTION
        return this.loginUserTemp;
    }

    public String getLoginPassTemp() {  //TO USE OUTSIDE CALL THIS FUNCTION
        return this.loginPassTemp;
    }

}
