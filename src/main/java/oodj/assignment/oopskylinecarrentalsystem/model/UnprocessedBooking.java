package oodj.assignment.oopskylinecarrentalsystem.model;

public class UnprocessedBooking {
    Car car;
    DateRange bookingDateRange;

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
}
