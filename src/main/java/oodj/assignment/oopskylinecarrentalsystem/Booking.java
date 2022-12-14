package oodj.assignment.oopskylinecarrentalsystem;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Booking {
    private Car currentCar;
    public int carId;
    private Car car;
    private String carFilePath = "";

    ArrayList<Car> carList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Booking() {
        String carFilePathRegex = "(?<=oop-skyline-car-rental-system/)(target/classes)(?=/oodj/assignment/oopskylinecarrentalsystem/car\\.txt)";
        Pattern carFilePathPattern = Pattern.compile(carFilePathRegex);
        Matcher carFilePathMatcher = carFilePathPattern.matcher(Objects.requireNonNull(getClass().getResource("car.txt")).getPath());
        String pathReplacement = "src/main/resources";

        String incompleteCarFilePath = carFilePathMatcher.replaceFirst(pathReplacement);
        carFilePath = URLDecoder.decode((incompleteCarFilePath.substring(1)), StandardCharsets.UTF_8);

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
            System.out.println("Which Element Do You Want to Sort:");
            System.out.println("[ 1 ] - Car Brand");
            System.out.println("[ 2 ] - Car Type");
            System.out.println("[ 3 ] - Car Transmission Type");
            System.out.println("[ 4 ] - Car Rental Price");
            System.out.println("[ 5 ] - Sort the Whole List in Ascending Order");
            System.out.println("[ 6 ] - Sort the Whole List in Descending Order");
            System.out.println("\nPlease Enter Your Choice Number:");

            switch (scanner.nextInt()) {
                case 1 -> {
                    carList.sort(Comparator.comparing(Car::getCarBrand));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case 2 -> {
                    carList.sort(Comparator.comparing(Car::getCarType));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case 3 -> {
                    carList.sort(Comparator.comparing(Car::getCarTransmission));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case 4 -> {
                    carList.sort(Comparator.comparing(Car::getCarRentalPrice));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case 5 -> {
                    carList.sort(Comparator.comparing(Car::getCarId));
                    carList.forEach(System.out::println);
                    choicePass = true;
                }
                case 6 -> {
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
        int result = Arrays.binarySearch( )
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

//    public void carMenu() {
//
//        int choice;
//        System.out.println("\nWelcome to Skyline Rental Car Menu:");
//        System.out.println("\n[ 1 ] : Show All Cars");
//        System.out.println("\n[ 2 ] : Sort the Cars");
//        System.out.println("\n[ 2 ] : Search the Cars");
//        System.out.println("\nPlease Enter Your Choice:");
//
//         switch (choice) {
//             case 1:
//                 displayCarList();
//                 selectCar();
//             case 2:
//
//         }
//    }

    public void bookingDetail() {

        System.out.printf("\nCar ID:%s",currentCar.getCarId());
        System.out.printf("\nCar Brand:%s",currentCar.getCarBrand());
        System.out.printf("\nCar Model:%s",currentCar.getCarModel());
        System.out.printf("\nCar Type:%s",currentCar.getCarType());
        System.out.printf("\nCar Transmission:%s",currentCar.getCarTransmission());
        System.out.printf("\nCar Rental Price:%s",currentCar.getCarRentalPrice());
        System.out.printf("\nCar Status:%s",currentCar.getCarStatus());

    }
}






