package oodj.assignment.oopskylinecarrentalsystem.model;

public class
UnprocessedBooking {
    private Car car;
    private DateRange bookingDateRange;
    private String licenseURL;

    public UnprocessedBooking(Car car, DateRange bookingDateRange) {
        this.car = car;
        this.bookingDateRange = bookingDateRange;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public float getUnprocessedBookingAmount() {
        return car.getDailyRate() * bookingDateRange.getDuration();
    }
}
