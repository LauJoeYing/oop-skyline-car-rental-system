package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.util.CarUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.TransactionUtils;
import oodj.assignment.oopskylinecarrentalsystem.util.UserUtils;
import oodj.assignment.oopskylinecarrentalsystem.interfaces.FileWrite;
import oodj.assignment.oopskylinecarrentalsystem.interfaces.Searchable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static oodj.assignment.oopskylinecarrentalsystem.util.CustomerUtils.getCustomerFromUsername;
import static oodj.assignment.oopskylinecarrentalsystem.util.MailUtils.sendBookingConfirmation;

public class Booking implements FileWrite, Searchable {
    private final UUID id;
    private final LocalDateTime bookingDateTime;
    private final String customerUsername;
    private float bookingAmount;
    private String carId;
    private DateRange bookingDateRange;
    private String licenseURL;
    private String status;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");         //Sets the format of date time string


    public Booking(String customerUsername, String carId, DateRange bookingDateRange, String licenseURL) {          //Constuctor of Booking
        this.id = UUID.randomUUID();
        this.customerUsername = customerUsername;
        this.bookingDateTime = LocalDateTime.now();
        this.bookingAmount = CarUtils.getCarDailyRateFromId(carId) * bookingDateRange.getDuration();
        this.carId = carId;
        this.bookingDateRange = bookingDateRange;
        this.licenseURL = licenseURL;
        this.setStatus("Pending");
    }

    public Booking(String[] registeredBooking) {                        //Overloaded Constructor of Booking
        this.id = UUID.fromString(registeredBooking[0]);
        this.bookingDateTime = LocalDateTime.parse(registeredBooking[1], formatter);
        this.customerUsername = registeredBooking[2];
        this.bookingAmount = Float.parseFloat(registeredBooking[3]);
        this.carId = registeredBooking[4];
        this.bookingDateRange = new DateRange(registeredBooking[5], registeredBooking[6]);
        this.licenseURL = registeredBooking[7];
        this.status = registeredBooking[8];
    }

    @Override
    public List<String> getSearchableProperties() {                     //Overriden method for getting searched values
        List<String> searchableProperties = new ArrayList<>();
        searchableProperties.add(String.valueOf(id));
        searchableProperties.add(customerUsername);
        searchableProperties.add(String.valueOf(bookingAmount));
        searchableProperties.add(carId);
        searchableProperties.add(status);

        return searchableProperties;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public float getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(float bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public DateRange getBookingDateRange() {
        return bookingDateRange;
    }

    public void setBookingDateRange(DateRange bookingDateRange) {
        this.bookingDateRange = bookingDateRange;
    }

    public String getLicenseURL() {
        return licenseURL;
    }

    public void setLicenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        Customer customer = Objects.requireNonNull(getCustomerFromUsername(customerUsername));
        if (status.equals("Rejected") || status.equals("Pending")) {
            if (status.equals("Rejected")) {
                customer.addAccountBalance(bookingAmount);
                TransactionUtils.addTransaction(new Transaction(
                        String.valueOf(id),
                        "Booking-Refund",
                        bookingAmount
                ));
                sendBookingConfirmation(customer, this, false);
            } else {
                customer.deductAccountBalance(bookingAmount);
                TransactionUtils.addTransaction(new Transaction(
                        String.valueOf(id),
                        "Booking-Payment",
                        bookingAmount
                ));
            }
            UserUtils.updateFile();
            TransactionUtils.updateFile();
        } else {
            sendBookingConfirmation(customer, this, true);
        }

        this.status = status;
    }

    public String emailFormat() {
        return String.format("<br>%s%s", Objects.requireNonNull(CarUtils.getCarFromId(carId)).emailFormat(), bookingDateRange.emailFormat());
    }

    @Override
    public String fileFormat() {
        return String.join(
                " || ",
                id.toString(),
                bookingDateTime.format(formatter),
                customerUsername,
                String.valueOf(bookingAmount),
                carId,
                bookingDateRange.fileFormat(),
                licenseURL,
                status
        );
    }
}
