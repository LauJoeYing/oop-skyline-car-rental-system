package oodj.assignment.oopskylinecarrentalsystem.controller.shared;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String signupUserTemp, signupPassTemp, signupGenderTemp, signupICTemp, signupEmailTemp, signupUnitNumTemp, signupAddLine1Temp, signupAddLine2Temp, signupPostTemp, signupCityTemp, signupStateTemp;

    @FXML
    private TextField signupAddLine1;

    @FXML
    private TextField signupAddLine2;

    @FXML
    private TextField signupCity;

    @FXML
    private TextField signupEmail;

    @FXML
    private ComboBox<String> signupGender;

    @FXML
    private TextField signupIC;

    @FXML
    private TextField signupPassword;

    @FXML
    private TextField signupPostcode;

    @FXML
    private ComboBox<String> signupState;

    @FXML
    private Button signupSubmitButton;

    @FXML
    private TextField signupUnitNum;

    @FXML
    private TextField signupUsername;

    ArrayList<String> arrRegister = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        String [] genderChoice = {"Male", "Female"};
        String [] stateChoice = {"Johor", "Kedah", "Kelantan", "Melaka", "Negeri Sembilan", "Pahang", "Penang", "Perak", "Perlis", "Sabah", "Sarawak", "Selangor", "Terengganu"};
        signupGender.getItems().addAll(genderChoice);
        signupState.getItems().addAll(stateChoice);
    }


    @FXML
    void onSubmitButtonClicked(ActionEvent event){
        signupUserTemp = signupUsername.getText();             /**Just Print Lines for Testing Input      */
        signupPassTemp = signupPassword.getText();
        signupGenderTemp = signupGender.getSelectionModel().getSelectedItem().toString();
        signupICTemp = signupIC.getText();
        signupEmailTemp = signupEmail.getText();
        signupUnitNumTemp = signupUnitNum.getText();
        signupAddLine1Temp = signupAddLine1.getText();
        signupAddLine2Temp = signupAddLine2.getText();
        signupPostTemp = signupPostcode.getText();
        signupCityTemp = signupCity.getText();
        signupStateTemp = signupState.getSelectionModel().getSelectedItem().toString();
        ArrayList<String> arrRegister = new ArrayList<>(Arrays.asList(signupUserTemp, signupPassTemp, signupGenderTemp, signupICTemp, signupEmailTemp, signupUnitNumTemp, signupAddLine1Temp, signupAddLine2Temp, signupPostTemp, signupCityTemp, signupStateTemp));
        signUp(arrRegister);

//        System.out.println(arrRegister.get(0));
//        String temp = "Jun Xian";
//        if (arrRegister.get(0).equals(temp)){
//            System.out.println("Yes Equal");
//        }

        //APPEND ALL ENTRIES TO ARRAY, SAVE TO USERFILE

        //POPUP WINDOW SHOW USERNAME AND PASSWORD
    }

    @FXML
    void returnToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void signUp(ArrayList<String> arr) {

        String usernameInput;
        String usernameRegex = "^[a-z][\\w\\-]{7,14}$"; //Condition: Must start with alphabet, length 8-15, can contain "_"/"-"
        Pattern usernamePattern = Pattern.compile(usernameRegex, Pattern.CASE_INSENSITIVE);
        Matcher usernameMatcher;

        while (true) {
            usernameInput = arr.get(0);
            //To accept all alphabets input
            usernameMatcher= usernamePattern.matcher(usernameInput);

            if (usernameMatcher.matches()) {
                break;
            }
            else{
                System.out.println("name");
//                popupERROR();
                break;
            }

        }

        String passwordInput;
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,32}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher;

        while (true) {
            passwordInput = arr.get(1);
            passwordMatcher = passwordPattern.matcher(passwordInput);
            if (passwordMatcher.matches()) {
                break;
            }
            else{
                System.out.println("pass");
//                popupERROR();
                break;
            }
        }

        String userIcInput;
        String userIcRegex = "^[\\d]{12}$|^[\\d]{6}-[\\d]{2}-[\\d]{4}$";
        Pattern userIcPattern = Pattern.compile(userIcRegex);
        Matcher userIcMatcher;

        while (true) {
            userIcInput = arr.get(3).trim();
            userIcMatcher = userIcPattern.matcher(userIcInput);

            if (userIcMatcher.matches()) {
                break;
            }
            System.out.println("ic");
//            popupERROR();
            break;

        }


        String emailInput;
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        //Condition: Allow: 0-9, upper & lowercase, "-", "_", max 64 characters
        //Condition Not Allow: Dot at the start and end of local part, consecutive dots
        Matcher emailMatcher;
        Pattern emailPattern = Pattern.compile(emailRegex);

        while (true) {

            System.out.println("\nPlease Enter Your Email:");
            emailInput = arr.get(4).trim();
            emailMatcher = emailPattern.matcher(emailInput);

            if (emailMatcher.matches()) {
                break;
            }
            System.out.println("email");
//            popupERROR();
            break;

        }

//        String phoneNumInput;
//        String phoneNumRegex = "^((\\+6|6)?01)([02-46-9]-?[\\d]{7}|1-?[\\d]{8})$";
//        //Accepted format: \d{10}: 1234567890 | (?:\d{3}-){2}\d{4} : 123-456-7890 | \(\d{3}\)\d{3}-?\d{4} : (123)456-7890 or (123)4567890
//        Pattern phoneNumPattern = Pattern.compile(phoneNumRegex);
//        Matcher phoneNumMatcher;
//
//        while (true) {
//
//            System.out.println("\nPlease Enter Your Phone Number:");
//            phoneNumInput = arr.get().trim();
//            phoneNumMatcher = phoneNumPattern.matcher(phoneNumInput);
//
//            if (phoneNumMatcher.matches()) {
//                System.out.println("\nCorrect Phone Format! Your Email Has Been Recorded");
//                break;
//            }
//
//            System.out.println("\nInvalid Input! Please Try Again!");
//            System.out.println("\nExample of accepted input: 1234567890 / 123-456-7890 / (123)456-7890 / (123)4567890");
//
//        }

        String unitNumInput;
        String unitNumRegex = "^[a-z\\d]{1,5}";
        Pattern unitNumPattern = Pattern.compile(unitNumRegex);
        Matcher unitNumMatcher;

        while (true) {

            unitNumInput = arr.get(5).trim();
            unitNumMatcher = unitNumPattern.matcher(unitNumInput);

            if (unitNumMatcher.matches()) {
                break;
            }
            System.out.println("unit");
//            popupERROR();
            break;

        }

        String street1Input;
        String street1Regex = "^[a-z][\\s\\w\\-]*$";
        Pattern street1Pattern = Pattern.compile(street1Regex, Pattern.CASE_INSENSITIVE);
        Matcher street1Matcher;

        while (true) {

            street1Input = arr.get(6).trim();
            street1Matcher = street1Pattern.matcher(street1Input);

            if (street1Matcher.matches()) {
                break;
            }
            System.out.println("street1");
            break;

        }

        String street2Input;
        String street2Regex = "^[a-z][\\s\\w\\-]*$";
        Pattern street2Pattern = Pattern.compile(street2Regex, Pattern.CASE_INSENSITIVE);
        Matcher street2Matcher;

        while (true) {
            street2Input = arr.get(7).trim();
            street2Matcher = street1Pattern.matcher(street2Input);

            if (street2Matcher.matches()) {
                break;
            }
            System.out.println("street2");
//            popupERROR();
            break;
        }

        String postcodeInput;
        String postcodeRegex = "\\d{5}";
        Pattern postcodePattern = Pattern.compile(postcodeRegex, Pattern.CASE_INSENSITIVE);
        Matcher postcodeMatcher;

        while (true) {
            postcodeInput = arr.get(8).trim();
            postcodeMatcher = postcodePattern.matcher(postcodeInput);

            if (postcodeMatcher.matches()) {
                break;
            }
//            popupERROR();
            break;
        }

        String cityInput;
        String cityRegex = "[a-zA-Z]*";
        Pattern cityPattern = Pattern.compile(cityRegex, Pattern.CASE_INSENSITIVE);
        Matcher cityMatcher;

        while (true) {

            cityInput = arr.get(9).trim();
            cityMatcher = cityPattern.matcher(cityInput);

            if (cityMatcher.matches()) {
                break;
            }
            System.out.println("city");
            break;
        }


//        String newAccount = String.format("\n%s", String.join(" | ", "C", usernameInput, passwordInput, nameInput, gender, userIcInput, emailInput, phoneNumInput, unitNumInput, street1Input, street2Input, postcodeInput, cityInput, stateInput ));
//
//        try{
//            Files.write(Path.of(userFilePath), newAccount.getBytes(), StandardOpenOption.APPEND);
//        } catch (IOException e){
//            e.printStackTrace();
//        } finally {
//            login();
//        }
        System.out.println("END OF CHECKING!!!!");
    }


    public void popupERROR() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("INVALID CREDENTIALS");
        alert.setContentText("PLEASE TRY AGAIN!");
        alert.showAndWait();
    }

}
