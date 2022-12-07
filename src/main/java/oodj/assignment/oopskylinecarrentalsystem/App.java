package oodj.assignment.oopskylinecarrentalsystem;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class App {

    private User currentUser;
    private String userFilePath = "";

    public App() {
        String userFilePathRegex = "(?<=oop-skyline-car-rental-system/)(target/classes)(?=/oodj/assignment/oopskylinecarrentalsystem/user\\.txt)";
        Pattern userFilePathPattern = Pattern.compile(userFilePathRegex);
        Matcher userFilePathMatcher = userFilePathPattern.matcher(Objects.requireNonNull(getClass().getResource("user.txt")).getPath());
        String pathReplacement = "src/main/resources";

        String incompleteUserFilePath = userFilePathMatcher.replaceFirst(pathReplacement);

        userFilePath = URLDecoder.decode((incompleteUserFilePath.substring(1)), StandardCharsets.UTF_8);
    }

    public User login() {
        AtomicBoolean authenticated = new AtomicBoolean(false);
        List<User> userList = new ArrayList<User>();

        try (Stream<String> stream = Files.lines(Path.of(userFilePath))) {
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

                Scanner scanner = new Scanner(System.in);

                while (!authenticated.get()) {
                    System.out.println("\nUsername: ");
                    String usernameInput = scanner.nextLine().trim();
                    System.out.println("\nPassword: ");
                    String passwordInput = scanner.nextLine();

                    userList.stream().parallel().forEach(user -> {
                        if (usernameInput.equalsIgnoreCase(user.getUsername()) && passwordInput.equals(user.getPassword())) {
                            this.currentUser = user;
                            authenticated.set(true);
                        }
                    });

                    if (!authenticated.get()) {
                        System.out.println("\nWrong username or password. Please try again.");
                        continue;
                    }

                    System.out.printf("\nWelcome back to Skyline Car Rental System, %s!", this.currentUser.getName());

                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentUser;
    }

    public void signUp() {

        Scanner input = new Scanner(System.in);

        String usernameInput;
        String usernameRegex = "^[a-z][\\w\\-]{7,14}$"; //Condition: Must start with alphabet, length 8-15, can contain "_"/"-"
        Pattern usernamePattern = Pattern.compile(usernameRegex, Pattern.CASE_INSENSITIVE);
        Matcher usernameMatcher;

        while (true) {

            System.out.println("========== SIGN UP PAGE ==========");
            System.out.println("Please Enter Your Username:");
            usernameInput = input.nextLine();
            //To accept all alphabets input
            usernameMatcher= usernamePattern.matcher(usernameInput);

            if (usernameMatcher.matches()) {
                System.out.println("Correct Username Format! Your New Username Has Been Recorded");
                break;
            }

        }

        String passwordInput;
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,32}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher;

        while (true) {
            System.out.println("\nPlease make sure your password contains: ");
            System.out.println("1. At least one digit.");
            System.out.println("2. At least one lowercase character.");
            System.out.println("3. At least one uppercase character.");
            System.out.println("4. At least one special character.");
            System.out.println("5. Between 8 and 32 characters.");
            System.out.println("\nEnter password (8 - 32 characters): ");
            passwordInput = input.nextLine();
            passwordMatcher = passwordPattern.matcher(passwordInput);
            if (passwordMatcher.matches()) {
                System.out.println("\nConfirm password: ");
                if (input.nextLine().equals(passwordInput)) {
                    System.out.println("Correct Password Format! Your New Password Has Been Recorded");
                    break;
                }
                System.out.println("\nPasswords do not match! Please try again.");
                continue;
            }
        }

        String nameInput;

        System.out.println("Please Enter Your Name:");
        nameInput = input.nextLine();


        boolean genderPass = false;
        String genderInput;
        String gender = null;

        while (!genderPass) {
            System.out.println("\n[ 1 ] - Male");
            System.out.println("[ 2 ] - Female");
            System.out.println("\nPlease Select Your Gender:");
            genderInput = input.nextLine().trim();

            switch (genderInput) {
                case "1" -> {
                    System.out.println("\nCorrect Input! Your Gender Has Been Recorded!");
                    gender = "Male";
                    genderPass = true;
                }
                case "2" -> {
                    System.out.println("\nCorrect Input! Your Gender Has Been Recorded!");
                    gender = "Female";
                    genderPass = true;
                }
                default -> {
                    System.out.println("\nInvalid Input! Please Try Again!");
                }
            }
        }

        String userIcInput;
        String userIcRegex = "^[\\d]{12}$|^[\\d]{6}-[\\d]{2}-[\\d]{4}$";
        Pattern userIcPattern = Pattern.compile(userIcRegex);
        Matcher userIcMatcher;

        while (true) {

            System.out.println("\nPlease Enter Your Personal Identification Number:");
            userIcInput = input.nextLine().trim();
            userIcMatcher = userIcPattern.matcher(userIcInput);

            if (userIcMatcher.matches()) {
                System.out.println("\nCorrect Personal Identification Number Format! Your Identification Number Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");

        }


        String emailInput;
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        //Condition: Allow: 0-9, upper & lowercase, "-", "_", max 64 characters
        //Condition Not Allow: Dot at the start and end of local part, consecutive dots
        Matcher emailMatcher;
        Pattern emailPattern = Pattern.compile(emailRegex);

        while (true) {

            System.out.println("\nPlease Enter Your Email:");
            emailInput = input.nextLine().trim();
            emailMatcher = emailPattern.matcher(emailInput);

            if (emailMatcher.matches()) {
                System.out.println("\nCorrect Email Format! Your Email Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");

        }

        String phoneNumInput;
        String phoneNumRegex = "^((\\+6|6)?01)([02-46-9]-?[\\d]{7}|1-?[\\d]{8})$";
        //Accepted format: \d{10}: 1234567890 | (?:\d{3}-){2}\d{4} : 123-456-7890 | \(\d{3}\)\d{3}-?\d{4} : (123)456-7890 or (123)4567890
        Pattern phoneNumPattern = Pattern.compile(phoneNumRegex);
        Matcher phoneNumMatcher;

        while (true) {

            System.out.println("\nPlease Enter Your Phone Number:");
            phoneNumInput = input.nextLine().trim();
            phoneNumMatcher = phoneNumPattern.matcher(phoneNumInput);

            if (phoneNumMatcher.matches()) {
                System.out.println("\nCorrect Phone Format! Your Email Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");
            System.out.println("\nExample of accepted input: 1234567890 / 123-456-7890 / (123)456-7890 / (123)4567890");

        }

        String unitNumInput;
        String unitNumRegex = "^[a-z\\d]{1,5}";
        Pattern unitNumPattern = Pattern.compile(unitNumRegex);
        Matcher unitNumMatcher;

        while (true) {

            System.out.println("\nPlease Enter Your Address Unit Number:");
            unitNumInput = input.nextLine().trim();
            unitNumMatcher = unitNumPattern.matcher(unitNumInput);

            if (unitNumMatcher.matches()) {
                System.out.println("\nCorrect Unit Number Format! Your Unit Number Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");
            System.out.println("\nThe Unit Number Must Between 1-5 Characters.");

        }

        String street1Input;
        String street1Regex = "^[a-z][\\s\\w\\-]*$";
        Pattern street1Pattern = Pattern.compile(street1Regex, Pattern.CASE_INSENSITIVE);
        Matcher street1Matcher;

        while (true) {

            System.out.println("\nPlease Enter Your Address Street Line 1:");
            street1Input = input.nextLine().trim();
            street1Matcher = street1Pattern.matcher(street1Input);

            if (street1Matcher.matches()) {
                System.out.println("\nCorrect Street Line 1 Format! Your Address Line 1 Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");


        }

        String street2Input;
        String street2Regex = "^[a-z][\\s\\w\\-]*$";
        Pattern street2Pattern = Pattern.compile(street2Regex, Pattern.CASE_INSENSITIVE);
        Matcher street2Matcher;

        while (true) {

            System.out.println("\nPlease Enter Your Address Street Line 2:");
            street2Input = input.nextLine().trim();
            street2Matcher = street1Pattern.matcher(street2Input);

            if (street2Matcher.matches()) {
                System.out.println("\nCorrect Street Line 2 Format! Your Address Line 2 Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");


        }

        String postcodeInput;
        String postcodeRegex = "\\d{5}";
        Pattern postcodePattern = Pattern.compile(postcodeRegex, Pattern.CASE_INSENSITIVE);
        Matcher postcodeMatcher;

        while (true) {

            System.out.println("\nPlease Enter Your Postcode:");
            postcodeInput = input.nextLine().trim();
            postcodeMatcher = postcodePattern.matcher(postcodeInput);

            if (postcodeMatcher.matches()) {
                System.out.println("\nCorrect Postcode Format! Your Postcode Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");
            System.out.println("\nThe Postcode Input Must Have 5 Digits.");

        }

        String cityInput;
        String cityRegex = "[a-zA-Z]*";
        Pattern cityPattern = Pattern.compile(cityRegex, Pattern.CASE_INSENSITIVE);
        Matcher cityMatcher;

        while (true) {

            System.out.println("\nPlease Enter Your City:");
            cityInput = input.nextLine().trim();
            cityMatcher = cityPattern.matcher(cityInput);

            if (cityMatcher.matches()) {
                System.out.println("\nCorrect City Format! Your City Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");
            System.out.println("\nThe City Input Must Only Contain Alphabets.");

        }

        String stateInput;
        String stateRegex = "[a-zA-Z]*";
        Pattern statePattern = Pattern.compile(stateRegex, Pattern.CASE_INSENSITIVE);
        Matcher stateMatcher;

        while (true) {

            System.out.println("\nPlease Enter Your State:");
            stateInput = input.nextLine().trim();
            stateMatcher = statePattern.matcher(stateInput);

            if (stateMatcher.matches()) {
                System.out.println("\nCorrect State Format! Your State Has Been Recorded");
                break;
            }

            System.out.println("\nInvalid Input! Please Try Again!");
            System.out.println("\nThe State Input Must Only Contain Alphabets.");

        }

        String newAccount = String.format("\n%s", String.join(" | ", "C", usernameInput, passwordInput, nameInput, gender, userIcInput, emailInput, phoneNumInput, unitNumInput, street1Input, street2Input, postcodeInput, cityInput, stateInput ));

        try{
            Files.write(Path.of(userFilePath), newAccount.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            login();
        }
    }


}
