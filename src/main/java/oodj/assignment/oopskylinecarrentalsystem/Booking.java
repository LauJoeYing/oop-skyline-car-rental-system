package oodj.assignment.oopskylinecarrentalsystem;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Booking<carList> {
    private Car currentCar;
    private final DateRange currentDateRange = new DateRange();
    private LocalTime slot;


    List<Car> carList = new ArrayList<Car>();
    Scanner scanner = new Scanner(System.in);

    public void carMenu() {
        boolean choicePass = false;

        while (!choicePass) {

            String choice;
            System.out.println("\nWelcome to Skyline Rental Car Menu:");
            System.out.println("[ 1 ] : Book a Car");
            System.out.println("[ 2 ] : Sort the Cars");
            System.out.println("[ 3 ] : Search the Cars");
            System.out.println("[ 4 ] : Show Booking History");
            System.out.println("[ 5 ] : Change Password");
            System.out.println("[ 6 ] : Edit Personal Info");
            System.out.println("\nPlease Enter Your Choice:");

            switch (scanner.nextLine()) {

                case "1" -> {
                    displayCarList();
                    selectCar();
                    choicePass = true;
                }
                case "2" -> {
                    carSorting();
                    selectCar();
                    choicePass = true;
                }
                case "3" -> {
                    carSearching();
                    selectCar();
                    choicePass = true;
                }
                default -> {
                    System.out.println("\nInvalid Input! Please Try Again!");
                }
            }
        }
    }

    public Booking() {
        String carFilePathRegex = "(?<=oop-skyline-car-rental-system/)(target/classes)(?=/oodj/assignment/oopskylinecarrentalsystem/car\\.txt)";
        Pattern carFilePathPattern = Pattern.compile(carFilePathRegex);
        Matcher carFilePathMatcher = carFilePathPattern.matcher(Objects.requireNonNull(getClass().getResource("car.txt")).getPath());
        String pathReplacement = "src/main/resources";

        String incompleteCarFilePath = carFilePathMatcher.replaceFirst(pathReplacement);
        String carFilePath = URLDecoder.decode((incompleteCarFilePath.substring(1)), StandardCharsets.UTF_8);

        try (Stream<String> stream = Files.lines(Path.of(carFilePath))) {
            stream.parallel().forEach(car ->{
                String[] carData = car.split(" \\| ");
                carList.add(new Car(carData));
            });

        } catch (IOException e) {
            System.out.println("Error Reading File!");
        }
    }

    public void displayCarList(){
        carList.stream().parallel().forEach(System.out::println);
    }

    public void carSorting() {
        boolean choicePass = false;
        while (!choicePass) {
            System.out.println("\nWhich Element Do You Want to Sort:");
            System.out.println("\n[ 1 ] - Car Brand");
            System.out.println("[ 2 ] - Car Type");
            System.out.println("[ 3 ] - Car Transmission Type");
            System.out.println("[ 4 ] - Car Rental Price");
            System.out.println("[ 5 ] - Sort the Whole List in Ascending Order");
            System.out.println("[ 6 ] - Sort the Whole List in Descending Order");
            System.out.println("\nPlease Enter Your Choice Number:");

            switch (scanner.nextLine().trim()) {
                case "1" -> {
                    carList.sort(Comparator.comparing(Car::getCarBrand));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case "2" -> {
                    carList.sort(Comparator.comparing(Car::getCarType));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case "3" -> {
                    carList.sort(Comparator.comparing(Car::getCarTransmission));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case "4" -> {
                    carList.sort(Comparator.comparing(Car::getCarRentalPrice));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case "5" -> {
                    carList.sort(Comparator.comparing(Car::getCarId));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case "6" -> {
                    carList.sort(Comparator.comparing((Car::getCarId)).reversed());
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                default -> {
                    System.out.println("Invalid Input! Please Try Again!");
                }
            }
        }
    }

    public void carSearching() {
        List<Car> matches =  new ArrayList<Car>();

        System.out.println("\nPlease Enter The Word Of Character That You Want To Search:");
        String keyword = scanner.nextLine().trim();
        String searchKeyRegex = ".*" + keyword + ".*";
        Pattern searchKeyPattern = Pattern.compile(searchKeyRegex, Pattern.CASE_INSENSITIVE);
        Matcher searchKeyMatcher;
        System.out.printf("Searching %s ...", keyword);

        for (Car car : carList) {
            searchKeyMatcher = searchKeyPattern.matcher(car.toString());
            if (searchKeyMatcher.matches())
                matches.add(car);
        }

        matches.stream().parallel().forEach(System.out::println);
    }

    public void selectCar() {

        AtomicBoolean authenticated = new AtomicBoolean(false);

        while (!authenticated.get()) {

            System.out.println("\nPlease Enter Your Selected Car ID:");
            String carIdInput = scanner.nextLine().trim();

            carList.stream().parallel().forEach(car -> {
                if (carIdInput.equalsIgnoreCase(car.getCarId())) {
                    this.currentCar = car;
                    authenticated.set(true);
                }
            });

            if (!authenticated.get()) {
                System.out.println("\nInvalid Car Input. Please Try Again!");
                continue;
            }
            System.out.printf("\nYour Car Selection Has Been Saved: %s", carIdInput);
            break;
        }
    }

    public void bookingDetails() throws ParseException {
//
        String date1, date2;
        //to validate date input is in DD-MM-YYYY format
        //to check if day is <=31 and month <= 12
        String dateRegex = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([12][0-9]{3})$";
        Pattern datePattern = Pattern.compile(dateRegex);
        Matcher dateMatcher;
        long days;


        while (true) {
            System.out.println("\nPlease Enter Your Rental Start Date (DD-MM-YYYY):");
            date1 = scanner.nextLine().trim();
            dateMatcher = datePattern.matcher(date1);

            if (dateMatcher.matches()) {

                System.out.println("\nCorrect Date Format! Your Rental Start Date Input Has Been Recorded");
                System.out.println("\nPlease Enter Your Rental End Date (DD-MM-YYYY):");
                date2 = scanner.nextLine().trim();
                dateMatcher = datePattern.matcher(date2);

                if (dateMatcher.matches()) {
                    System.out.println("\nCorrect Date Format! Your Rental End Date Input Has Been Recorded");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    currentDateRange.setStartDate(LocalDate.parse(date1, formatter));
                    currentDateRange.setEndDate(LocalDate.parse(date2, formatter));
                    int result = date1.compareTo(date2);

                    if (result < 0) {
                        System.out.println("Correct Input, The Start Date and End Date Has Been Validated!");
                        days = ChronoUnit.DAYS.between(currentDateRange.getStartDate(), currentDateRange.getEndDate());
                        System.out.println("The Duration is:" + days + " Day(s)");
                        break;
                    } else if (result > 0) {
                        System.out.println("The Start Date is Later Than End Date!");
                        continue;
                    } else {
                        System.out.println("The Start Date and End Date Cannot Be the Same!");
                        continue;
                    }
                }
            }
            System.out.println("\nInvalid Input! Please Try Again!");
        }

        boolean slotPass = false;
        String slotInput;

        while (!slotPass) {
            System.out.println("Slot Selection:");
            System.out.println("\n[ 1 ] - 08:00 AM");
            System.out.println("[ 2 ] - 10:00 AM");
            System.out.println("[ 3 ] - 01:00 PM");
            System.out.println("[ 4 ] - 03:00 PM");
            System.out.println("[ 5 ] - 05:00 PM");

            System.out.println("\nPlease Select Your Slot to Collect the Car:");
            slotInput = scanner.nextLine().trim();

            switch (slotInput) {

                case "1" -> {
                    System.out.println("\nCorrect Input! Your Slot Has Been Recorded!");
                    slot = LocalTime.parse("08:00:00");
                    slotPass = true;
                }
                case "2" -> {
                    System.out.println("\nCorrect Input! Your Slot Has Been Recorded!");
                    slot = LocalTime.parse("10:00:00");
                    slotPass = true;
                }
                case "3" -> {
                    System.out.println("\nCorrect Input! Your Slot Has Been Recorded!");
                    slot = LocalTime.parse("13:00:00");
                    slotPass = true;
                }
                case "4" -> {
                    System.out.println("\nCorrect Input! Your Slot Has Been Recorded!");
                    slot = LocalTime.parse("15:00:00");
                    slotPass = true;
                }
                case "5" -> {
                    System.out.println("\nCorrect Input! Your Slot Has Been Recorded!");
                    slot = LocalTime.parse("17:00:00");
                    slotPass = true;
                }
                default -> {
                    System.out.println("\nInvalid Input! Please Try Again!");
                }
            }
        }
        System.out.println("Please Enter Your Rental End Date:");
        System.out.println("Please Upload Your License:");
    }

    public void calculatePrice() {

        float oneDayPrice = Float.parseFloat(currentCar.getCarRentalPrice());
        float duration = Float.parseFloat(String.valueOf(ChronoUnit.DAYS.between(currentDateRange.getStartDate(), currentDateRange.getEndDate())));
        float totalRentalPrice = oneDayPrice * duration;
        System.out.printf("\nTotal Rental Price: RM %.2f", totalRentalPrice);
    }


    public void bookingConfirmation() {
        System.out.println("\n=== Booking Confirmation (Car Details) ===");
        System.out.printf("\nCar ID: %s",currentCar.getCarId());
        System.out.printf("\nCar Brand: %s",currentCar.getCarBrand());
        System.out.printf("\nCar Model: %s",currentCar.getCarModel());
        System.out.printf("\nCar Type: %s",currentCar.getCarType());
        System.out.printf("\nCar Transmission: %s",currentCar.getCarTransmission());
        System.out.printf("\nCar Rental Price: RM %s",currentCar.getCarRentalPrice());
        System.out.printf("\nCar Status: %s",currentCar.getCarStatus());

        System.out.println("\n\n=== Booking Confirmation (Booking Details) ===");
        System.out.printf("\nStart Date: %s",currentDateRange.getStartDate().toString());
        System.out.printf("\nTime to Collect: %s", slot.format(DateTimeFormatter.ofPattern("hh:mm a")));
        System.out.printf("\nReturn Date: %s",currentDateRange.getEndDate().toString());
        System.out.printf("\nTime to Return: %s", slot.format(DateTimeFormatter.ofPattern("hh:mm a")));
        System.out.printf("\nDuration: %d day(s)", ChronoUnit.DAYS.between(currentDateRange.getStartDate(), currentDateRange.getEndDate()));

        boolean choicePass = false;
        while (!choicePass) {
            System.out.println("Are You Sure You Want To Book This Car?");
            System.out.println("[ 1 ] - Yes");
            System.out.println("[ 2 ] - No");

            switch (scanner.nextLine().trim()) {
                case "1" -> {
                    //transaction
                }
                case "2" -> {
                    System.out.println("Directing You Back To Customer Main Menu ...");
                    carMenu();
                }
                default -> {
                    System.out.println("Invalid Input! Please Try Again");
                    choicePass = true;
                }
            }
        }
    }
}














