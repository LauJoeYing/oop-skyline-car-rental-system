package oodj.assignment.oopskylinecarrentalsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String signupUserTemp, signupPassTemp, signupNameTemp, signupConfirmPassTemp, signupPhoneTemp, signupGenderTemp, signupICTemp, signupEmailTemp, signupUnitNumTemp, signupAddLine1Temp, signupAddLine2Temp, signupPostTemp, signupCityTemp, signupStateTemp;

    @FXML
    private TextArea RequirementTextBox;

    @FXML
    private TextField signupAddLine1;

    @FXML
    private TextField signupAddLine2;

    @FXML
    private TextField signupCity;

    @FXML
    private TextField signupConfirmPass;

    @FXML
    private TextField signupEmail;

    @FXML
    private ComboBox<String> signupGender;

    @FXML
    private TextField signupIC;

    @FXML
    private TextField signupName;

    @FXML
    private TextField signupPassword;

    @FXML
    private TextField signupPhone;

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

    @FXML
    private TextField signupVerifyCode;

    static ArrayList<String> arrRegister = new ArrayList<>();

    private Boolean flag1, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9, flag10, flag11, flag12, flag13;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] genderChoice = {"Male", "Female"};
        String[] stateChoice = {"Johor", "Kedah", "Kelantan", "Melaka", "Negeri Sembilan", "Pahang", "Penang", "Perak", "Perlis", "Sabah", "Sarawak", "Selangor", "Terengganu"};
        signupGender.getItems().addAll(genderChoice);
        signupState.getItems().addAll(stateChoice);
    }


    @FXML
    void onSubmitButtonClicked(ActionEvent event) {
        signupUserTemp = signupUsername.getText();          /**Just Print Lines for Testing Input      */
        signupPassTemp = signupPassword.getText();
        signupNameTemp = signupName.getText();
        signupConfirmPassTemp = signupConfirmPass.getText();
        signupGenderTemp = signupGender.getSelectionModel().getSelectedItem().toString();
        signupEmailTemp = signupEmail.getText();
        signupICTemp = signupIC.getText();
        signupPhoneTemp = signupPhone.getText();
        signupUnitNumTemp = signupUnitNum.getText();
        signupAddLine1Temp = signupAddLine1.getText();
        signupAddLine2Temp = signupAddLine2.getText();
        signupPostTemp = signupPostcode.getText();
        signupCityTemp = signupCity.getText();
        signupStateTemp = signupState.getSelectionModel().getSelectedItem().toString();
        flag1 = validateUsername(signupUserTemp);
        flag2 = validateName(signupNameTemp);
        flag3 = validatePassword(signupPassTemp);
        flag4 = validateConfirmPassword(signupConfirmPassTemp);
        flag5 = validateEmail(signupEmailTemp);
        flag6 = validateIC(signupICTemp);
        flag7 = validatePhone(signupPhoneTemp);
        flag8 = validateUnitNum(signupUnitNumTemp);
        flag9 = validateAdd1(signupAddLine1Temp);
        flag10 = validateAdd2(signupAddLine2Temp);
        flag11 = validatePostcode(signupPostTemp);
        flag12 = validateCity(signupCityTemp);
        flag13 = verifyCode();

        if(!flag1||!flag2||!flag3||!flag4||!flag5||!flag6||!flag7||!flag8||!flag9||!flag10||!flag11||!flag12||!flag13){
            signUp();
        }
    }

    /**
     *
     * TIPS textbox not done
     * Email verify function not implemented
     *
     */


    @FXML
    void returnToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    boolean verifyCode(){
        if(signupVerifyCode.getText().equals("123456")){
            return true;
        }
        else{
            return false;
        }
    }


    @FXML
    public boolean validateUsername(String input) {
        String usernameInput = input;
        String usernameRegex = "^[a-z][\\w\\-]{7,14}$"; //Condition: Must start with alphabet, length 8-15, can contain "_"/"-"
        Pattern usernamePattern = Pattern.compile(usernameRegex, Pattern.CASE_INSENSITIVE);
        Matcher usernameMatcher;

        usernameMatcher = usernamePattern.matcher(usernameInput);
        if (usernameMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public boolean validatePassword(String input) {
        String passwordInput = input;
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,32}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher;

        passwordMatcher = passwordPattern.matcher(passwordInput);
        if (passwordMatcher.matches()) {
            return true;
        }
        else{
            return false;
        }
    }


    @FXML
    public boolean validateName(String input){
        return true;
    }


    @FXML
    public boolean validateConfirmPassword(String input) {
        if(input.equals(signupConfirmPass.getText())){
            return true;
        }
        else{
            return false;
        }
    }

    @FXML
    public boolean validateEmail(String input) {
        String emailInput = input;
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        //Condition: Allow: 0-9, upper & lowercase, "-", "_", max 64 characters
        //Condition Not Allow: Dot at the start and end of local part, consecutive dots
        Matcher emailMatcher;
        Pattern emailPattern = Pattern.compile(emailRegex);
        emailMatcher = emailPattern.matcher(emailInput);

        if (emailMatcher.matches()) {
            return true;
        }
        else{
            return false;
        }
    }

    @FXML
    public boolean validateIC(String input) {
        String userIcInput = input;
        String userIcRegex = "^[\\d]{12}$|^[\\d]{6}-[\\d]{2}-[\\d]{4}$";
        Pattern userIcPattern = Pattern.compile(userIcRegex);
        Matcher userIcMatcher;

        userIcMatcher = userIcPattern.matcher(userIcInput);

        if (userIcMatcher.matches()) {
            return true;
        }
        else {
            return false;
        }

    }

    @FXML
    public boolean validatePhone(String input) {
        String phoneNumInput = input;
        String phoneNumRegex = "^((\\+6|6)?01)([02-46-9]-?[\\d]{7}|1-?[\\d]{8})$";
        //Accepted format: \d{10}: 1234567890 | (?:\d{3}-){2}\d{4} : 123-456-7890 | \(\d{3}\)\d{3}-?\d{4} : (123)456-7890 or (123)4567890
        Pattern phoneNumPattern = Pattern.compile(phoneNumRegex);
        Matcher phoneNumMatcher;
        phoneNumMatcher = phoneNumPattern.matcher(phoneNumInput);

        if (phoneNumMatcher.matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    public boolean validateUnitNum(String input) {
        String unitNumInput = input;
        String unitNumRegex = "^[a-z\\d]{1,5}";
        Pattern unitNumPattern = Pattern.compile(unitNumRegex);
        Matcher unitNumMatcher;

        unitNumMatcher = unitNumPattern.matcher(unitNumInput);

        if (unitNumMatcher.matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    public boolean validateAdd1(String input) {
        String street1Input = input;
        String street1Regex = "^[a-z][\\s\\w\\-]*$";
        Pattern street1Pattern = Pattern.compile(street1Regex, Pattern.CASE_INSENSITIVE);
        Matcher street1Matcher;

        street1Matcher = street1Pattern.matcher(street1Input);
        if (street1Matcher.matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    public boolean validateAdd2(String input) {
        String street1Input = input;
        String street1Regex = "^[a-z][\\s\\w\\-]*$";
        Pattern street1Pattern = Pattern.compile(street1Regex, Pattern.CASE_INSENSITIVE);
        Matcher street1Matcher;

        street1Matcher = street1Pattern.matcher(street1Input);
        if (street1Matcher.matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    @FXML
    public boolean validatePostcode(String input) {
        String postcodeInput = input;
        String postcodeRegex = "\\d{5}";
        Pattern postcodePattern = Pattern.compile(postcodeRegex, Pattern.CASE_INSENSITIVE);
        Matcher postcodeMatcher;
        postcodeMatcher = postcodePattern.matcher(postcodeInput);

        if (postcodeMatcher.matches()) {
            return true;
        }
        return false;
    }

    @FXML
    public boolean validateCity(String input) {
        String cityInput = input;
        String cityRegex = "[a-zA-Z]*";
        Pattern cityPattern = Pattern.compile(cityRegex, Pattern.CASE_INSENSITIVE);
        Matcher cityMatcher;

        cityMatcher = cityPattern.matcher(cityInput);
        if (cityMatcher.matches()) {
            return true;
        }
        else{
            return false;
        }
    }

    public void signUp() {

        String newAccount = String.format("\n%s", String.join(" | ", "C", signupUserTemp, signupPassTemp, signupNameTemp, signupGenderTemp, signupEmailTemp, signupICTemp, signupPhoneTemp, signupUnitNumTemp, signupAddLine1Temp, signupAddLine2Temp, signupPostTemp, signupCityTemp, signupStateTemp));
        try{
            Files.write(Path.of(FilePath.USER.getDataFile()), newAccount.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    public void popupERROR() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("INVALID CREDENTIALS");
        alert.setContentText("PLEASE TRY AGAIN!");
        alert.showAndWait();
    }

}
