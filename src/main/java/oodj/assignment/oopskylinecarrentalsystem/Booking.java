package oodj.assignment.oopskylinecarrentalsystem;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
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

    public void displayCar(){
        carList.stream().parallel().forEach(System.out::println);
    }

    public void carSorting() {

    }

    public void carSearching() {

    }

    public void carFiltering() {

    }

    public void showCarList() {

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


//    public void displayCar() throws IOException {
//
//        String carDataLine = null;
//        try {
//            FileReader fileReader = new FileReader("car.txt");
//            BufferedReader in = new BufferedReader(fileReader);
//
//            while ((carDataLine = in.readLine()) != null) {
//                System.out.println(carDataLine);
//            }
//
//            in.close();
//        }
//        catch (IOException ex)
//        {
//            System.out.println("Error Reading File!");
//        }
//    }



