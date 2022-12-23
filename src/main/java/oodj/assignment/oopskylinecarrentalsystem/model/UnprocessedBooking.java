package oodj.assignment.oopskylinecarrentalsystem.model;

// OOP Concept: Encapsulation
public class UnprocessedBooking {
    private Car car;
    private DateRange bookingDateRange;
    private String licenseURL;

    // Constructs a new UnprocessedBooking with the specified car and date range.
    public UnprocessedBooking(Car car, DateRange bookingDateRange) {
        this.car = car;
        this.bookingDateRange = bookingDateRange;
    }


    // Returns the car for this unprocessed booking.
    public Car getCar() {
        return car;
    }

    // Sets the car for this unprocessed booking.
    public void setCar(Car car) {
        this.car = car;
    }

    // Returns the date range for this unprocessed booking.
    public DateRange getBookingDateRange() {
        return bookingDateRange;
    }

    // Sets the date range for this unprocessed booking.
    public void setBookingDateRange(DateRange bookingDateRange) {
        this.bookingDateRange = bookingDateRange;
    }

    // Returns the URL for the license of the driver for this unprocessed booking.
    public String getLicenseURL() {
        return licenseURL;
    }

    // Sets the URL for the license of the driver for this unprocessed booking.
    public void setLicenseURL(String licenseURL) {
        this.licenseURL = licenseURL;
    }

    // Returns the total amount for this unprocessed booking
    public float getUnprocessedBookingAmount() {
        return car.getDailyRate() * bookingDateRange.getDuration();
    }
}
