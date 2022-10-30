package oodj.assignment.oopskylinecarrentalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class User {
    public void login() {

        AtomicBoolean pass = new AtomicBoolean(false);
        String filepath = "C:\\Users\\joeyi\\OneDrive - Asia Pacific University\\APU DEGREE DA\\Year 2 Sem 1\\OOP with Java\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\user.txt";

        try (Stream<String> stream = Files.lines(Path.of(filepath))) {
            Scanner input = new Scanner(System.in);

            while (!pass.get()) {
                System.out.println("Please Enter Your User ID : ");
                String usernameInput = input.nextLine().trim();

                System.out.println("Please Enter Your Password : ");
                String passwordInput = input.nextLine();

                //lamda expression
                stream.parallel().forEach(user -> {
                    String[] userData = user.split(" \\| ");
                    String userId = userData[0];
                    String username = userData[1];
                    String password = userData[2];
                    String userName = userData[3];

                    if (usernameInput.equalsIgnoreCase(username) && passwordInput.equals(password)) {
                        System.out.printf("Welcome back to Skyline Car Rental System, %s !", userName);
                        pass.set(true);
                    }
                });
                if (!pass.get()){
                    System.out.println("Invalid Input! Please Try Again!");
                    continue;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void signUp() {
        String username, userName, gender, userPin, email, phoneNum, unitNum, streetLine1, streetLine2, postcode, city, state;
        boolean genderPass = false;



        Scanner input = new Scanner(System.in);

//        while (true) {
//
//            System.out.println("========== SIGN UP PAGE ==========");
//            System.out.println("Please Enter Your Username:");
//            String usernameInput = input.nextLine();
//            String usernameRegex = "^[a-z][\\w\\-]{7,14}$";    //Condition: Must start with alphabet, length 8-15, can contain "_"/"-"
//            Pattern usernamePattern = Pattern.compile(usernameRegex, Pattern.CASE_INSENSITIVE); //To accept all alphabets input
//            Matcher usernameMatcher = usernamePattern.matcher(usernameInput);
//
//            if (usernameMatcher.matches()){
//                System.out.println("Correct Username Format! Your New Username Has Been Recorded");
//                break;
//            }
//
//        }
//
//        while (!genderPass) {
//            System.out.println("[ 1 ] - Male");
//            System.out.println("[ 2 ] - Female");
//            System.out.println("Please Select Your Gender:");
//            String genderInput = input.nextLine().trim();
//
//            switch (genderInput) {
//                case "1" -> {
//                    System.out.println("Correct Input! Your Gender Has Been Recorded!");
//                    gender = "Male";
//                    genderPass = true;
//                }
//                case "2" -> {
//                    System.out.println("Correct Input! Your Gender Has Been Recorded!");
//                    gender = "Female";
//                    genderPass = true;
//                }
//                default -> {
//                    System.out.println("Invalid Input! Please Try Again!");
//                }
//            }
//        }

//        while (true) {
//
//            System.out.println("Please Enter Your Personal Identification Number:");
//            String userPinInput = input.nextLine().trim();
//
//            String userPinRegex = "^[\\d]{3}-[\\d]{2}-[\\d]{4}$";
//            Pattern userPinPattern = Pattern.compile(userPinRegex);
//            Matcher userPinMatcher = userPinPattern.matcher(userPinInput);
//
//            if (userPinMatcher.matches()) {
//                System.out.println("Correct Personal Identification Number Format! Your PIN Has Been Recorded");
//                break;
//            }
//
//            System.out.println("Invalid Input! Please Try Again!");
//
//        }

//        while (true) {
//
//            System.out.println("Please Enter Your Email:");
//            String emailInput = input.nextLine().trim();
//
//            String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
//            //Condition: Allow: 0-9, upper & lowercase, "-", "_", max 64 characters
//            //Condition Not Allow: Dot at the start and end of local part, consecutive dots
//            Pattern emailPattern = Pattern.compile(emailRegex);
//            Matcher emailMatcher = emailPattern.matcher(emailInput);
//
//            if (emailMatcher.matches()) {
//                System.out.println("Correct Email Format! Your Email Has Been Recorded");
//                break;
//            }
//
//            System.out.println("Invalid Input! Please Try Again!");
//
//        }

//        while (true) {
//
//            System.out.println("Please Enter Your Phone Number:");
//            String phoneNumInput = input.nextLine().trim();
//
//            String phoneNumRegex = "^\"\\\\d{10}|(?:\\\\d{3}-){2}\\\\d{4}|\\\\(\\\\d{3}\\\\)\\\\d{3}-?\\\\d{4}\";";
//            //Accepted format: \d{10}: 1234567890 | (?:\d{3}-){2}\d{4} : 123-456-7890 | \(\d{3}\)\d{3}-?\d{4} : (123)456-7890 or (123)4567890
//            Pattern phoneNumPattern = Pattern.compile(phoneNumRegex);
//            Matcher phoneNumMatcher = phoneNumPattern.matcher(phoneNumInput);
//
//            if (phoneNumMatcher.matches()) {
//                System.out.println("Correct Phone Format! Your Email Has Been Recorded");
//                break;
//            }
//
//            System.out.println("Invalid Input! Please Try Again!");
//            System.out.println("Example of accepted input: 1234567890 / 123-456-7890 / (123)456-7890 / (123)4567890");
//
//        }

//        while (true) {
//
//            System.out.println("Please Enter Your Address Unit Number:");
//            String unitNumInput = input.nextLine().trim();
//
//            String unitNumRegex = "^[a-z\\d]{1,5}";
//
//            Pattern unitNumPattern = Pattern.compile(unitNumRegex);
//            Matcher unitNumMatcher = unitNumPattern.matcher(unitNumInput);
//
//            if (unitNumMatcher.matches()) {
//                System.out.println("Correct Unit Number Format! Your Unit Number Has Been Recorded");
//                break;
//            }
//
//            System.out.println("Invalid Input! Please Try Again!");
//            System.out.println("The Unit Number Must Between 1-5 Characters.");
//
//        }
      //#################################ERROR REGEX!
        while (true) {

            System.out.println("Please Enter Your Address Street Line 1:");
            String street1Input = input.nextLine().trim();

            String street1Regex = "^[a-z][\\s\\w\\-]*$";

            Pattern street1Pattern = Pattern.compile(street1Regex, Pattern.CASE_INSENSITIVE);
            Matcher street1Matcher = street1Pattern.matcher(street1Input);

            if (street1Matcher.matches()) {
                System.out.println("Correct Street Line 1 Format! Your Address Line 1 Has Been Recorded");
                break;
            }

            System.out.println("Invalid Input! Please Try Again!");
            System.out.println("The Unit Number Must Between 1-5 Characters.");

        }

//        while (true) {
//
//            System.out.println("Please Enter Your Postcode:");
//            String postcodeInput = input.nextLine().trim();
//
//            String postcodeRegex = "\\d{5}";
//
//            Pattern postcodePattern = Pattern.compile(postcodeRegex, Pattern.CASE_INSENSITIVE);
//            Matcher postcodeMatcher = postcodePattern.matcher(postcodeInput);
//
//            if (postcodeMatcher.matches()) {
//                System.out.println("Correct Postcode Format! Your Postcode Has Been Recorded");
//                break;
//            }
//
//            System.out.println("Invalid Input! Please Try Again!");
//            System.out.println("The Postcode Input Must Have 5 Digits.");
//
//        }
//
//        while (true) {
//
//            System.out.println("Please Enter Your City:");
//            String cityInput = input.nextLine().trim();
//
//            String cityRegex = "[a-zA-Z]*";
//
//            Pattern cityPattern = Pattern.compile(cityRegex, Pattern.CASE_INSENSITIVE);
//            Matcher cityMatcher = cityPattern.matcher(cityInput);
//
//            if (cityMatcher.matches()) {
//                System.out.println("Correct City Format! Your City Has Been Recorded");
//                break;
//            }
//
//            System.out.println("Invalid Input! Please Try Again!");
//            System.out.println("The City Input Must Only Contain Alphabets.");
//
//        }
//
//        while (true) {
//
//            System.out.println("Please Enter Your State:");
//            String stateInput = input.nextLine().trim();
//
//            String stateRegex = "[a-zA-Z]*";
//
//            Pattern statePattern = Pattern.compile(stateRegex, Pattern.CASE_INSENSITIVE);
//            Matcher stateMatcher = statePattern.matcher(stateInput);
//
//            if (stateMatcher.matches()) {
//                System.out.println("Correct State Format! Your State Has Been Recorded");
//                break;
//            }
//
//            System.out.println("Invalid Input! Please Try Again!");
//            System.out.println("The State Input Must Only Contain Alphabets.");
//
//        }

//        String[] account = {username, userName, gender, userPin, email, phoneNum, unitNum, streetLine1, streetLine2, postcode, city, state};

    }




}

