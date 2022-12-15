package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.config.CarConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.TransactionConfig;
import oodj.assignment.oopskylinecarrentalsystem.config.UserConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static oodj.assignment.oopskylinecarrentalsystem.config.CustomerConfig.getCustomerFromUsername;
import static oodj.assignment.oopskylinecarrentalsystem.config.MailConfig.sendBookingConfirmation;

public class Booking implements FileWrite, Searchable {
    private final UUID id;
    private final String customerUsername;
    private final LocalDateTime bookingDate;
    private Map<String, List<BookingSlot>> carBookingList;
    private float bookingAmount;
    private String status;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public Booking(String customerUsername, String carId, DateRange bookingDateRange) {
        this.id = UUID.randomUUID();
        this.customerUsername = customerUsername;
        this.bookingDate = LocalDateTime.now();
        this.carBookingList = new HashMap<>();
        this.bookingAmount = CarConfig.getCarDailyRateFromId(carId) * bookingDateRange.getDuration();

        List<BookingSlot> bookingSlots = new ArrayList<>();
        bookingSlots.add(new BookingSlot(bookingDateRange, bookingAmount));

        this.carBookingList.put(carId, bookingSlots);
        this.setStatus("Pending");
    }

    public Booking(String customerUsername, String carId, List<DateRange> bookingDateRanges) {
        this.id = UUID.randomUUID();
        this.customerUsername = customerUsername;
        this.bookingDate = LocalDateTime.now();
        this.carBookingList = new HashMap<>();
        this.bookingAmount = 0.0F;
        float carDailyRate = CarConfig.getCarDailyRateFromId(carId);

        List<BookingSlot> bookingSlots = new ArrayList<>();

        for (DateRange bookingDateRange: bookingDateRanges) {
            float slotAmount = carDailyRate * bookingDateRange.getDuration();
            bookingSlots.add(new BookingSlot(bookingDateRange, slotAmount));
            this.bookingAmount += slotAmount;
        }

        this.carBookingList.put(carId, bookingSlots);

        this.setStatus("Pending");
    }

    public Booking(String customerUsername, List<String> carIdList, DateRange bookingDateRange) {
        this.id = UUID.randomUUID();
        this.customerUsername = customerUsername;
        this.bookingDate = LocalDateTime.now();
        this.carBookingList = new HashMap<>();
        this.bookingAmount = 0.0F;

        for(String carId: carIdList) {
            List<BookingSlot> bookingSlots = new ArrayList<>();
            float carDailyRate = CarConfig.getCarDailyRateFromId(carId);
            float slotAmount = carDailyRate * bookingDateRange.getDuration();
            bookingSlots.add(new BookingSlot(bookingDateRange, slotAmount));

            this.carBookingList.put(carId, bookingSlots);
            this.bookingAmount += CarConfig.getCarDailyRateFromId(carId) * bookingDateRange.getDuration();
        }

        this.setStatus("Pending");
    }

    public Booking(String customerUsername, Map<String, List<BookingSlot>> carBookingList) {
        this.id = UUID.randomUUID();
        this.customerUsername = customerUsername;
        this.bookingDate = LocalDateTime.now();
        this.carBookingList = carBookingList;
        this.bookingAmount = 0.0F;

        for (Map.Entry<String, List<BookingSlot>> carBooking : carBookingList.entrySet()) {
            String carId = carBooking.getKey();
            for (BookingSlot bookingSlot : carBooking.getValue()) {
                float slotAmount = bookingSlot.getSlotAmount();
                bookingAmount += slotAmount;
            }
        }

        this.setStatus("Pending");
    }

    public Booking(String[] registeredBooking) {
        this.id = UUID.fromString(registeredBooking[0]);
        this.bookingDate = LocalDateTime.parse(registeredBooking[1], formatter);
        this.bookingAmount = Float.parseFloat(registeredBooking[2]);
        this.customerUsername = registeredBooking[3];
        this.carBookingList = new HashMap<>();

        String[] carBookings = registeredBooking[4].split(" \\| ");

        for (String carBooking: carBookings) {
            String[] bookingData = carBooking.split(": ");
            String carId = bookingData[0];
            List<BookingSlot> bookingSlots = new ArrayList<>();

            String[] unprocessedBookingSlots = bookingData[1].split(", ");
            for (String unprocessedBookingSlot: unprocessedBookingSlots) {
                String[] bookingSlotData = unprocessedBookingSlot.split(" = ");
                float slotAmount = Float.parseFloat(bookingSlotData[1]);
                String[] stringDateRange = bookingSlotData[0].split(" - ");
                DateRange dateRange = new DateRange(stringDateRange);
                bookingSlots.add(new BookingSlot(dateRange, slotAmount));
            }

            carBookingList.put(carId, bookingSlots);
        }

        this.status = registeredBooking[5];
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public Map<String, List<BookingSlot>> getCarBookingList() {
        return carBookingList;
    }

    public void setCarBookingList(Map<String, List<BookingSlot>> carBookingList) {
        this.carBookingList = carBookingList;
    }

    public float getBookingAmount() {
        return bookingAmount;
    }

    public void recalculateBookingAmount(){
        this.bookingAmount = 0.0F;
        for (Map.Entry<String, List<BookingSlot>> carBooking : carBookingList.entrySet()) {
            for (BookingSlot bookingSlot : carBooking.getValue()) {
                this.bookingAmount += bookingSlot.getSlotAmount();
            }
        }
    }

    @Override
    public List<String> getSearchableProperties() {
        List<String> searchableProperties = new ArrayList<>();
        searchableProperties.add(String.valueOf(id));
        searchableProperties.add(customerUsername);
        searchableProperties.addAll(carBookingList.keySet());
        searchableProperties.add(status);

        return searchableProperties;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        Customer customer = Objects.requireNonNull(getCustomerFromUsername(customerUsername));
        if (status.equals("Rejected") || status.equals("Pending")) {
            if (status.equals("Rejected")) {
                customer.addAccountBalance(bookingAmount);
                TransactionConfig.addTransaction(new Transaction(
                        String.valueOf(id),
                        "Booking-Refund",
                        bookingAmount
                ));
                sendBookingConfirmation(customer, this, false);
            } else {
                customer.deductAccountBalance(bookingAmount);
                TransactionConfig.addTransaction(new Transaction(
                        String.valueOf(id),
                        "Booking-Payment",
                        bookingAmount
                ));
            }
            UserConfig.updateFile();
            TransactionConfig.updateFile();
        } else {
            sendBookingConfirmation(customer, this, true);
        }

        this.status = status;
    }

    public String emailFormat() {
        List<String> carBookingData = new ArrayList<>();

        for(Map.Entry<String, List<BookingSlot>> carBooking : carBookingList.entrySet()){
            List<String> dateRangeData = new ArrayList<>();
            Car car = Objects.requireNonNull(CarConfig.getCarFromId(carBooking.getKey()));
            int index = 1;
            for (BookingSlot bookingSlot: carBooking.getValue()) {
                dateRangeData.add(String.format("<li>Booking Slot %d:%s</li>", index, bookingSlot.getDateRange().emailFormat()));
                index++;
            }
            String listedDateRangeData = String.format("<ul style=\"padding-left: 1rem;\">%s</ul>", String.join("<br>", dateRangeData));
            carBookingData.add(String.format("<li>%s%s</li>", car.emailFormat(), listedDateRangeData));
        }

        return String.format("<ul style=\"padding-left: 1rem;\">%s</ul>", String.join("<br>", carBookingData));
    }

    @Override
    public String fileFormat() {
        List<String> carBookingData = new ArrayList<>();

        for(Map.Entry<String, List<BookingSlot>> carBooking : carBookingList.entrySet()){
            List<String> bookingSlots = new ArrayList<>();
            for (BookingSlot bookingSlot: carBooking.getValue()) {
                bookingSlots.add(bookingSlot.fileFormat());
            }
            String delimitedBookingSlotData = String.join(", ", bookingSlots);
            carBookingData.add(String.join(": ", carBooking.getKey(), delimitedBookingSlotData));
        }

        String delimitedCarBookingData = String.join(" | ", carBookingData);

        return String.join(
                " || ",
                id.toString(),
                bookingDate.format(formatter),
                String.valueOf(bookingAmount),
                customerUsername,
                delimitedCarBookingData,
                status);
    }
}
