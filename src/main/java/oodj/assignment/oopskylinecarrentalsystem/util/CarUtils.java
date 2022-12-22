package oodj.assignment.oopskylinecarrentalsystem.util;

import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import oodj.assignment.oopskylinecarrentalsystem.model.Car;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oodj.assignment.oopskylinecarrentalsystem.util.CustomerUtils.isRegexMatchCaseInsensitive;

public class CarUtils {
    private static final String carFilePath;
    private static final List<Car> carList;

    static {
        carList = new ArrayList<>();

        String carFilePathRegex = "(?<=IdeaProjects/oop-skyline-car-rental-system/)(target/classes)(?=/oodj/assignment/oopskylinecarrentalsystem/textfiles/Car\\.txt$)";
        Pattern carFilePathPattern = Pattern.compile(carFilePathRegex);
        Matcher carFilePathMatcher = carFilePathPattern.matcher(Objects.requireNonNull(CarUtils.class.getResource("/oodj/assignment/oopskylinecarrentalsystem/textfiles/Car.txt")).getPath());
        String pathReplacement = "src/main/resources";

        String incompleteCarFilePath = carFilePathMatcher.replaceFirst(pathReplacement);

        carFilePath = URLDecoder.decode((incompleteCarFilePath.substring(1)), StandardCharsets.UTF_8);

        try {
            List<String> carDatabase = new ArrayList<>(FileUtils.readLines(new File(carFilePath), Charset.defaultCharset()));

            for(String car: carDatabase) {
                String[] carData = car.split(" \\|\\| ");
                carList.add(new Car(carData));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Car> getCarList() {
        return carList;
    }

    public static Car getCarFromId(String id) {
        for (Car car: carList) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public static float getCarDailyRateFromId(String id) {
        for (Car car: carList) {
            if (car.getId().equals(id)) {
                return car.getDailyRate();
            }
        }

        return 0.0F;
    }

    public static List<LocalDate> getCarUnavailableDatesFromId(String id) {
        List<LocalDate> unavailableDates = new ArrayList<>();

        for (Booking booking: BookingUtils.getBookingListByStatus("Accepted")) {
            unavailableDates.addAll(booking.getBookingDateRange().getDateList());
        }

        return unavailableDates;
    }

    public static void removeCar(Car car) {
        carList.removeIf(oldCar ->
                oldCar.getId().equals(car.getId())
        );
    }

    public static void updateCar(Car car) {
        carList.replaceAll(oldCar ->
                oldCar.getId().equals(car.getId()) ? car : oldCar
        );
    }

    public static void addCar(Car car) {
        carList.add(car);
    }

    public static boolean isValidCarIdToModify(Car targetCar, String carId) {
        if (isValidCarId(carId)) {
            for (Car car: carList) {
                if(car.getId().equals(carId) && !targetCar.getId().equals(carId)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidCarIdToCreate(String carId) {
        if (isValidCarId(carId)) {
            for (Car car: carList) {
                if(car.getId().equals(carId)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isValidCarId(String carId) {
        return isRegexMatchCaseInsensitive(carId, "^[A-Z]{3}\\s\\d{4}(?:\\s[A-Z])?$|^[A-Z]{2}\\s\\?d{4}\\s[A-Z]$");
    }

    public static boolean isValidDailyRate(String dailyRateInString) {
        return dailyRateInString.matches("^\\d*\\.?\\d+$");
    }

    public static String toStandardCarId(String carId) {
        return carId.replaceAll("(?<=[A-Za-z])(?=[0-9])|(?<=[0-9])(?=[A-Za-z])", " ").toUpperCase();
    }

    public static void updateFile() {
        List<String> carDatabase = new ArrayList<>();

        for(Car car: carList) {
            carDatabase.add(car.fileFormat());
        }

        try {
            FileUtils.writeLines(new File(carFilePath), carDatabase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
