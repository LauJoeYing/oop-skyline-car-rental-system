package oodj.assignment.oopskylinecarrentalsystem.config;

import oodj.assignment.oopskylinecarrentalsystem.model.Booking;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BookingConfig {
    private static final String bookingFilePath;
    private static final List<Booking> bookingList;

    static {
        bookingList = new ArrayList<>();

        String bookingFilePathRegex = "(?<=IdeaProjects/oop-skyline-car-rental-system/)(target/classes)(?=oodj/assignment/oopskylinecarrentalsystem/textfiles/Booking\\.txt$)";
        Pattern bookingFilePathPattern = Pattern.compile(bookingFilePathRegex);
        Matcher bookingFilePathMatcher = bookingFilePathPattern.matcher(Objects.requireNonNull(BookingConfig.class.getResource("/oodj/assignment/oopskylinecarrentalsystem/textfiles/Booking.txt")).getPath());
        String pathReplacement = "src/main/resources";

        String incompleteUserFilePath = bookingFilePathMatcher.replaceFirst(pathReplacement);

        bookingFilePath = URLDecoder.decode((incompleteUserFilePath.substring(1)), StandardCharsets.UTF_8);

        try {
            List<String> bookingDatabase = new ArrayList<>(FileUtils.readLines(new File(bookingFilePath), Charset.defaultCharset()));

            for(String booking: bookingDatabase) {
                String[] bookingData = booking.split(" \\|\\| ");
                bookingList.add(new Booking(bookingData));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBookingFilePath() {
        return bookingFilePath;
    }

    public static List<Booking> getBookingList() {
        return bookingList;
    }

    public static List<Booking> getBookingListByStatus(String status) {
        return bookingList
                .stream()
                .filter(booking -> booking.getStatus().equals(status))
                .toList();
    }

    public static Booking getBookingFromUUID(UUID id) {

        for (Booking booking: bookingList) {
            if (booking.getId().equals(id)) {
                return booking;
            }
        }

        return null;
    }

    public static List<Booking> getBookingFromCustomerUsername(String customerUsername) {
        return bookingList.stream().filter(booking -> booking.getCustomerUsername().equals(customerUsername)).toList();
    }

    public static List<Booking> getBookingFromCarId(String carId) {
        return bookingList.stream().filter(booking -> booking.getCarId().equals(carId)).toList();
    }

    public static void addBooking(Booking booking) {
        bookingList.add(booking);
    }

    public static List<Booking> searchBooking(String searchKeys) {
        String[] searchKeyList = searchKeys.split(" ");
        List<Booking> matchingBookingList = new ArrayList<>(List.copyOf(bookingList));

        for (String searchKey: searchKeyList) {
            String searchKeyRegex = String.format("^.*%s.*$", searchKey);
            Pattern searchKeyPattern = Pattern.compile(searchKeyRegex, Pattern.CASE_INSENSITIVE);

            matchingBookingList.retainAll(
                    bookingList.stream()
                            .filter(booking ->
                                    booking.getSearchableProperties()
                                            .stream()
                                            .anyMatch(property ->
                                                    searchKeyPattern.matcher(property)
                                                            .matches()
                                            )
                            )
                            .toList()
            );
        }

        return matchingBookingList;
    }

    public static void updateBooking(Booking booking) {
        bookingList.replaceAll(oldBooking ->
                oldBooking.getId().equals(booking.getId()) ? booking : oldBooking
        );
    }

    //    #CRUD
    public static void updateFile() {
        List<String> bookingDatabase = new ArrayList<>();

        for(Booking booking: bookingList) {
            bookingDatabase.add(booking.fileFormat());
        }

        try {
            FileUtils.writeLines(new File(bookingFilePath), bookingDatabase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
